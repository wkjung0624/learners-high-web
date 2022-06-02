package com.mightyotter.learnershigh.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 빈으로 등록한건 어떤식으로 사용하지? 자동 주입만을 이용해야하나?
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// https://prod.velog.io/@seongwon97/Spring-Security-세션-관리

		// h2-console 을 위한 예외처리 부분
		http.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.and()
			.csrf().disable()
			.headers().frameOptions().disable();

		// 각 경로별 접근 권한 설정
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated()
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll();
//			.and()
//			.formLogin()
//			.loginPage("/mylogin")
//			.loginProcessingUrl("/mylogin")
//			.defaultSuccessUrl("/");

		// 세션 관리,생성 주체 정의
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS); // 스프링 시큐리티가 항상 세션을 생성합니다.

		// 동시 세션 관리, 새로운 세션 발급 관련
		http.sessionManagement()
			.maximumSessions(1)				// 최대 허용 가능 세션 수를 설정하는 api이다. (값으로 -1을 넣으면 무제한으로 세션 생성을 허용하게 된다.)
			.maxSessionsPreventsLogin(true) // 위에서 설정한 최대 허용 세션의 수가 되었을 때 추가적인 인증 요청(세션 생성)이 있을 경우 어떻게 처리를 할지 정하는 api이다.
											// (true면 현재 사용자 인증 실패, false(default값)면 기존 세션 만료하게 딘다.)
			//.invalidSessionUrl("/invalid")	// 세션이 유효하지 않을 때 이동 할 페이지를 설정하는 api이다.
			.expiredUrl("/expired"); 		// 세션이 만료된 경우 이동 할 페이지를 정하는 api이다.

		// 동일한 세션 쿠키로 둘 이상의 접근을 차단함 (공격자로부터 세션 고정 보호)
		http.sessionManagement()
			.sessionFixation()
			.changeSessionId();
	}
}
