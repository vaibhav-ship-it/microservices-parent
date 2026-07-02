/**
 * 
 */
package com.api.gateway;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

/**
 * 
 */
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>	{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Environment env;
	
	public AuthorizationHeaderFilter() {
		super(Config.class);
	}
	
	public static class Config	{
		// Put configuration properties here
		// Example: allow setting error status code
        private org.springframework.http.HttpStatus errorStatus = org.springframework.http.HttpStatus.UNAUTHORIZED;

        public org.springframework.http.HttpStatus getErrorStatus() {
            return errorStatus;
        }

        public void setErrorStatus(org.springframework.http.HttpStatus errorStatus) {
            this.errorStatus = errorStatus;
        }
	}
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) ->	{
			HttpHeaders headers = exchange.getRequest().getHeaders();
			
			if(!headers.containsHeader(HttpHeaders.AUTHORIZATION))	{
				return onError(exchange, "No Authorization Header", config.getErrorStatus());
			}
			
			String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                // Reject request if header missing or malformed
            	return onError(exchange, "Authorization header doesn't start with Bearer", config.getErrorStatus());
            }
			
			String jwt = authHeader.replace("Bearer", "").trim();
			
			if(!isJwtValid(jwt)) {
				return onError(exchange, "JWT token is not valid", config.getErrorStatus());
			}
			// Pass user info downstream
			String username = extractUsername(jwt);
			ServerHttpRequest mutatedRequest = exchange.getRequest().mutate().header("username", username).build();
			ServerWebExchange mutatedExchange = exchange.mutate()
	                .request(mutatedRequest)
	                .build();
			return chain.filter(mutatedExchange);
		};
	}

	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus)	{
		logger.info(err);
		logger.info(httpStatus.getReasonPhrase().toString());
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		return response.setComplete();
		
	}
	// validate jwt
	private boolean isJwtValid(String jwt)	{
		boolean returnValue = true;

		String subject = null;
		SecretKey secretKey = getSecretKey();

        JwtParser parser = Jwts.parser()
                .verifyWith(secretKey)
                .build();

		try {

	        Claims claims = parser.parseSignedClaims(jwt).getPayload();      
	        subject = (String) claims.get("sub");

		} catch (Exception ex) {
			returnValue = false;
		}

		if (subject == null || subject.isEmpty()) {
			returnValue = false;
		}

		return returnValue;
	}

	private SecretKey getSecretKey() {
		String tokenSecret = env.getProperty("token.secret");
		byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecret.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
		return secretKey;
	}
	// Extract username
    public String extractUsername(String token) {
    	SecretKey secretKey = getSecretKey();
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}
