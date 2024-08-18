package com.sx.userserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.sx.userserver.*.mapper")
@SpringBootApplication
//其中@ComponentScan注解：默认读取项目的根package下的所有文件，如果自定义的java文件没有放在根package目录下，Springboot不会加载
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

}
