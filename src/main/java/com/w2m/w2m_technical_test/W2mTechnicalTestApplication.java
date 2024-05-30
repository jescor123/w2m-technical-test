package com.w2m.w2m_technical_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy
public class W2mTechnicalTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(W2mTechnicalTestApplication.class, args);
	}

}
