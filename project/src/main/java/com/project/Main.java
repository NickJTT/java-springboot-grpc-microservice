package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.project.projects.repositories")
@EntityScan("com.project.projects.entities")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
