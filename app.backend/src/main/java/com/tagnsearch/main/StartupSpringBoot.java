package com.tagnsearch.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.tagnsearch")
@EnableAutoConfiguration
public class StartupSpringBoot {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartupSpringBoot.class, args);
    }
}