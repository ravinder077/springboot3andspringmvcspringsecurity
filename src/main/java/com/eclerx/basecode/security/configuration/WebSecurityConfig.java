package com.eclerx.basecode.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

	@Autowired
	AuthEntryPointJwt authEntryPointJwt;

	@Autowired
	AuthTokenFilter authTokenFilter;

	private static final String[] AUTH_BYPASS_ROUTES = { "/signin", "/user/**"};

	private static final String[] AUTH_WHITELIST_JWT = { "/auth/signin", "/signin","/swagger-ui/**", "/v3/api-docs/**", "/webjars/**","/auth/checkuser", "/WEB-INF/**" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.toString())
				.authorizeHttpRequests(auth -> auth.requestMatchers("api").authenticated().requestMatchers(AUTH_WHITELIST_JWT)
						.permitAll().requestMatchers(AUTH_BYPASS_ROUTES).permitAll().anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPointJwt))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

}
