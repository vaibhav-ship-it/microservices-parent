/**
 * 
 */
package com.register.netbanking.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

/**
 * 
 */
@Configuration
//@EnableWebSecurity
public class WebSecurity {

	private Environment environment;
	
	public WebSecurity(Environment environment)	{
		this.environment = environment;
	}
	
	/*@Value("${gateway-ip}")
	private String getewayIP;*/

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http)	{
		http.csrf(csrf->csrf.disable());
		http.authorizeHttpRequests(auth->auth.requestMatchers(
				PathPatternRequestMatcher.pathPattern("/register/users"),
				PathPatternRequestMatcher.pathPattern("/h2-console/**"))/*.access(
						new WebExpressionAuthorizationManager("hasIpAddress('"+environment.getProperty("gateway-ip")+"')")))*/
				.permitAll())
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.headers(headers->headers.frameOptions(frameOptions->frameOptions.sameOrigin()));
		return http.build();
	}
}
