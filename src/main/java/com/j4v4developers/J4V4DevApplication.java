package com.j4v4developers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class J4V4DevApplication {


    public static void main(String[] args) {
        SpringApplication.run(J4V4DevApplication.class, args);
    }

}
