package com.learnershigh.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableJpaAuditing
@EnableRedisHttpSession
@EnableGlobalMethodSecurity(securedEnabled = true) // 도메인 별 일부 혹은 모든 Api가 특정 권한(ROLE_*)을 가진 유저만 사용하게끔 옵션 설정
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
