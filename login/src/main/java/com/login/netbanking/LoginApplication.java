package com.login.netbanking;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.login.netbanking.security.JwtUtil;

@SpringBootApplication
@EnableFeignClients
public class LoginApplication {

	@Value("${token.secret}")
	private String secretKey;
	
	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtUtil jwtUtil() {
	    // 64+ char secret recommended
	    return new JwtUtil(secretKey, 3600000); // 1h expiry
	}

}
