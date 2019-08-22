package com.getset.webfluxdemo.repository;

import com.getset.webfluxdemo.model.MyEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface MyEventRepository extends ReactiveMongoRepository<MyEvent, Long> {//1 下边用到了可以保存Flux的insert(Flux)方法，这个方法是在ReactiveMongoRepository中定义的
    @Tailable//2
    Flux<MyEvent> findBy();//3
    //2 @Tailable注解的作用类似于linux的tail命令，被注解的方法将发送无限流，需要注解在返回值为Flux这样的多个元素的Publisher的方法上；
    //3 findAll()是想要的方法，但是在ReactiveMongoRepository中我们够不着，所以使用findBy()代替
}
