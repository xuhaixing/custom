package com.xhx.java;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.xhx.java.feign.annotation.EnableFeignClient;

@SpringBootApplication
@EnableFeignClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
