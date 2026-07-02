package com.loan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.loan.security.JwtUtil;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@SpringBootApplication
@EnableFeignClients
public class LoanApplication {

	@Value("${token.secret}")
	private String secretKey;
	
	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}
	
	@Bean
	public MessageSource messageSource()	{
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("application");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
		
	}
	
	@Bean
	public LocalValidatorFactoryBean getValidator(MessageSource messageSource)	{
		LocalValidatorFactoryBean validatorBean = new LocalValidatorFactoryBean();
		validatorBean.setValidationMessageSource(messageSource);
		return validatorBean;
	}

	@Bean
	public JwtUtil jwtUtil() {
	    // 64+ char secret recommended
	    return new JwtUtil(secretKey, 3600000); // 1h expiry
	}
	
}
