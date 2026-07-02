package com.register.netbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableDiscoveryClient
public class RegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
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
	public BCryptPasswordEncoder bCryptPasswordEncoder()	{
		return new BCryptPasswordEncoder();
	}
}
