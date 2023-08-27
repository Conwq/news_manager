package org.example.news_manager.config;

import org.example.news_manager.config.listeners.AuthenticationSuccessEventListener;
import org.springframework.beans.factory.annotation.Autowired;
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
public class NewsManagerSecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService userDetailsService;
	private final AuthenticationSuccessEventListener eventListener;

	@Autowired
	public NewsManagerSecurityConfig (UserDetailsService userDetailsService,
									  AuthenticationSuccessEventListener eventListener){
		this.userDetailsService = userDetailsService;
		this.eventListener = eventListener;
	}

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
				.antMatchers("/news/goToBasePage", "/user/**", "/error").permitAll()
				.antMatchers("/news/**").authenticated()

				.and()
				.exceptionHandling().accessDeniedPage("/user/access-denied")

				.and()
				.formLogin()
				.loginPage("/news/goToBasePage")
				.loginProcessingUrl("/process-authorisation")
				.successHandler(eventListener)
				.failureUrl("/news?error")

				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/news/goToBasePage")

				.and()
				.csrf().disable();
	}
}
