package com.transfer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.transfer.service.security.JwtUtil;

@SpringBootApplication
@EnableFeignClients
public class TransferApplication {

	@Value("${token.secret}")
	private String secretKey;
	
	public static void main(String[] args) {
		SpringApplication.run(TransferApplication.class, args);
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
