package org.example.news_manager.service;

import org.example.news_manager.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class UserDetailsImpl implements UserDetails {
	private final UserEntity userEntity;

	public UserDetailsImpl(UserEntity userEntity){
		this.userEntity = userEntity;
	}
	
	public Locale getLocale() {
		String language = userEntity.getLocaleEntity().getLanguage();
		String country = userEntity.getLocaleEntity().getCountry();
		Locale locale = new Locale(language, country);
		return locale;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(userEntity.getAuthority().getAuthority()));
	}

	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
