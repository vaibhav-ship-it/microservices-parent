package com.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AuthorizationHeaderFilter authFilter) {
	    return builder.routes()
    		.route("registration-service", r -> r.path("/register/users")
	    	            .and().method("POST")
	    	            .uri("lb://registration-service"))
	    	.route("login-service", r -> r.path("/login")
	            .and().method("POST")
	            .uri("lb://login-service"))
	    	.route("login-service", r -> r.path("/getBalance")
		            .and().method("GET")
		            .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
		            .uri("lb://login-service"))
	    	.route("login-service", r -> r.path("/getStatement")
		            .and().method("GET")
		            .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
		            .uri("lb://login-service"))
	    	.route("login-service", r -> r.path("/loggedInUserAccountNo")
		            .and().method("GET")
		            .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
		            .uri("lb://login-service"))
	        .route("transfer-service", r -> r.path("/transfer")
	        		.and().method("POST")
	                .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
	                .uri("lb://transfer-service"))
	        .route("loan-service", r -> r.path("/loan")
	        		.and().method("POST")
	                .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
	                .uri("lb://loan-service"))
	        .route("accounts-service", r -> r.path("/account/deposit")
	        		.and().method("POST")
	                .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
	                .uri("lb://accounts-service"))
	        .route("accounts-service", r -> r.path("/account/withdraw")
	        		.and().method("POST")
	                .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
	                .uri("lb://accounts-service"))	        
	        .route("accounts-service", r -> r.path("/account/currentBalance")
	        		.and().method("GET")
	                .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
	                .uri("lb://accounts-service"))
	        .route("accounts-service", r -> r.path("/account/transactionHistory/**")
	        		.and().method("GET")
	                .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
	                .uri("lb://accounts-service"))
	        .route("statement-service", r -> r.path("/statement/**")
	        		.and().method("GET")
	                .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
	                .uri("lb://statement-service"))
	        .route("profile-service", r -> r.path("/profile/user")
	        		.and().method("GET")
	                .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
	                .uri("lb://profile-service"))
	        .route("profile-service", r -> r.path("/profile/user/**")
	        		.and().method("PUT")
	                .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
	                .uri("lb://profile-service"))
	        .build();
	}

}
