package com.pcy.dayonetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories // SpringBoot에서는 꼭 추가를 하지 않아도 괜찮다
public class DayonetestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DayonetestApplication.class, args);
	}

}
