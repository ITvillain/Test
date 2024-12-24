package com.itwillbs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	암호화 설정
	
	@Bean
	public PasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder : 스프링 시큐리티에서 제공하는 클래스 중 하나
//							  : 비밀번호를 암호화하는 데 사용할 수 있는 메서드를 가진 클래스
//		BCrypt 해싱 함수를 사용해서 비밀번호를 인코딩해주는 메서드 제공
//		저장소에 저장된 비밀번호의 일치 여부를 확인해주는 메서드 제공
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filerChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().requestMatchers(null).hashCode().and().formLogin();
		return http
				.authorizeHttpRequests(authorizeHttpRequestsCustomizer
						-> authorizeHttpRequestsCustomizer
						.requestMatchers("/","/login/**","/insert/**","/boardlist/**","/boardcontent/**").permitAll()
						.requestMatchers("/info/**","/update/**","/main/**","/delete/**",
								"/boardwrite/**","/boardupdate/**","/boarddelete/**").hasAnyRole("USER","ADMIN")
						.requestMatchers("/list/**","/admin/**").hasRole("ADMIN")
						.anyRequest()
						.authenticated()
						)
				.build();
	}
	
	

}
