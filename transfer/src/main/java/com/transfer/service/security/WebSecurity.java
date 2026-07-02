package com.transfer.service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

//import com.login.netbanking.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	//private Environment environment;
	//private UserService userService;
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	private JwtUtil jwtUtil;
	
	public WebSecurity(/*Environment environment, UserService userService, 
			BCryptPasswordEncoder bCryptPasswordEncoder,*/ JwtUtil jwtUtil) {
		//this.environment = environment;
		//this.userService = userService;
		//this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtUtil = jwtUtil;
	}
    
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    	
    	// Configure AuthenticationManagerBuilder
    	AuthenticationManagerBuilder authenticationManagerBuilder = 
    			http.getSharedObject(AuthenticationManagerBuilder.class);
    	
    	
    	//authenticationManagerBuilder.userDetailsService(userService)
    	//.passwordEncoder(bCryptPasswordEncoder);
    	
    	/*AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
    	
    	// Create AuthenticationFilter
    	AuthenticationFilter authenticationFilter = 
    			new AuthenticationFilter(userService, environment, authenticationManager);
    	authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
    	*/
    	http.csrf((csrf) -> csrf.disable());
    	//
    	http.authorizeHttpRequests(authz -> authz
    	        .requestMatchers("/login", "/register/users", "/h2-console/**").permitAll()
    	       
    	        .anyRequest().authenticated()
    	    )
    	
        //.addFilter(authenticationFilter)
        .addFilterBefore(new JwtAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class) // NEW
        //.authenticationManager(authenticationManager)
    	//.addFilter(new JwtAuthorizationFilter(jwtUtil)) // NEW
    	.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
 		
    	http.headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()));
        return http.build();

    }
}
