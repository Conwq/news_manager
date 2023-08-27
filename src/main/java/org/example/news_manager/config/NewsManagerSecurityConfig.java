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
				.antMatchers("/news/goToBasePage", "/user/goToRegistrationPage", "/error")
				.permitAll()
				.antMatchers("/news").authenticated()
//				.anyRequest().authenticated() Почему то некорректно отображается, а вот с матчером выше - все работает
				.and()
				.formLogin()
				.loginPage("/news/goToBasePage")
				.loginProcessingUrl("/process-authorisation")
//				.permitAll()
				.successHandler(eventListener)
				.failureUrl("/news?error")
				.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/news/goToBasePage")
				.and()
				.csrf().disable();

//		http.csrf().disable()
//				.authorizeRequests().antMatchers("/news/goToBasePage", "/error").permitAll()
//				.anyRequest().authenticated()
//				.and()
//				.formLogin()
//				.defaultSuccessUrl("/news/goToNewsList", true)
//				.failureUrl("/news?error")
//				.and()
//				.logout().logoutSuccessUrl("/news/goToBasePage");
	}
}
