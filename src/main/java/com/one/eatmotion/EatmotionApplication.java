package com.one.eatmotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
// @EnableScheduling
// @EnableBatchProcessing
@SpringBootApplication
public class EatmotionApplication {

  public static void main(String[] args) {
    SpringApplication.run(EatmotionApplication.class, args);
  }
}
