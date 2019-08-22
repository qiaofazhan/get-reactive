package com.getset.webfluxdemo.controller;

import com.getset.webfluxdemo.model.MyEvent;
import com.getset.webfluxdemo.repository.MyEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
public class MyEventController {
    @Autowired
    private MyEventRepository myEventRepository;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)//1
    public Mono<Void> loadEvents(@RequestBody Flux<MyEvent> events) {//2
        return this.myEventRepository.insert(events).then();//3
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)// 4
    public Flux<MyEvent> getEvents() {
        return this.myEventRepository.findBy();
    }
}
//1 指定传入的数据是application/stream+json，与getEvents方法的区别在于这个方法是consume（消费）这个数据流(参数是数据流)
//2 POST方法的接收数据流的Endpoint，所以传入的参数是一个Flux，返回结果其实就看需要了，我们用一个Mono<Void>作为方法返回值，表示如果传输完的话只给一个“完成信号”就OK了；
//3 insert返回的是保存成功的记录的Flux，但我们不需要，使用then方法表示“忽略数据元素，只返回一个完成信号”
// 4 GET方法的无限发出数据流的Endpoint，所以返回结果是一个Flux<MyEvent>，不要忘了注解上produces = MediaType.APPLICATION_STREAM_JSON_VALUE。

