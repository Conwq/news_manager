package org.example.newsmanager.config.security;

import lombok.RequiredArgsConstructor;
import org.example.newsmanager.config.listeners.AuthenticationSuccessEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class NewsManagerSecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService userDetailsService;
	private final AuthenticationSuccessEventListener eventListener;

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/news/admin/**").hasRole("ADMIN")
				.antMatchers("/news/goToBasePage", "/user/**", "/error", "/news/changeLocale").permitAll()
				.antMatchers("/news/**").authenticated()

				.and()
				.exceptionHandling()
				.accessDeniedPage("/user/access-denied")

				.and()
				.formLogin()
				.loginPage("/news/goToBasePage")
				.loginProcessingUrl("/process-authorisation")
				.successHandler(eventListener)
				.failureUrl("/news/goToBasePage?error")

				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/news/goToBasePage")

				.and()
				.csrf().disable();
	}
}
