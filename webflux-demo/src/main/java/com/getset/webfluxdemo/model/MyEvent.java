package com.getset.webfluxdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event")//指定collection(MongoDB中的表)名为event；
public class MyEvent {
    @Id
    private Long id;// 2 这次我们使用表示时间的long型数据作为ID
    private String message;
}
