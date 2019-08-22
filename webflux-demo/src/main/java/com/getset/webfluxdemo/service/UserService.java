package com.getset.webfluxdemo.service;

import com.getset.webfluxdemo.model.User;
import com.getset.webfluxdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll().log();
    }

    /**
     * 保存或更新。
     * 如果传入的user没有id属性，由于username是unique的，在重复的情况下有可能报错，
     * 这时找到以保存的user记录用传入的user更新它。
     */
    public Mono<User> save(User user) {
        return userRepository.save(user)
                .doOnError(System.out::println)
                .onErrorResume(e ->//1 onErrorResume进行错误处理
                        userRepository.findByUsername(user.getUsername())//2 找到username重复的记录
                                .flatMap(originalUser -> {// 4 由于函数式为User -> Publisher，所以用flatMap
                                    user.setId(originalUser.getId());
                                    return userRepository.save(user);//3 拿到ID从而进行更新而不是创建
                                }));
    }

    public Mono<Long> deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
