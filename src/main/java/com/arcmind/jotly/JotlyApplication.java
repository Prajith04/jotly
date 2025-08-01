package com.arcmind.jotly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JotlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JotlyApplication.class, args);
    }

}
