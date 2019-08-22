package com.getset.webfluxdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document//MongoDB是文档型的NoSQL数据库，因此，我们使用@Document注解User类
public class User {
    @Id
    private String id;// 注解属性id为ID
    @Indexed(unique = true)
    private String username;// 注解属性username为索引，并且不能重复
    private String name;
    private String phone;
    private Date birthday;
}
