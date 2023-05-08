package com.solmaz.keycloak.config;

import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(keycloakAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);

		http.cors().and().csrf().disable().authorizeHttpRequests((requests) -> requests

				.antMatchers(HttpMethod.GET, "/user").hasAuthority("user").antMatchers(HttpMethod.GET, "/admin")
				.hasAuthority("admin").antMatchers(HttpMethod.GET, "/hello").hasAuthority("final-admin-role")
				.antMatchers(HttpMethod.GET, "/hello1").hasAuthority("final-user-role").anyRequest().authenticated())

				.logout((logout) -> logout.permitAll());
	}

	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}

}
