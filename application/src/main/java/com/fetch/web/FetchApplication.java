package com.fetch.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jiang chen
 * @ClassName FetchApplication
 * @date 2024/02/29 16:05
 * @description: TODO
 */
@SpringBootApplication
@ComponentScan("com.fetch.**")
public class FetchApplication {

    public static void main(String[] args) {
        SpringApplication.run(FetchApplication.class, args);
    }

}
