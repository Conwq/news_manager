package org.example.newsmanager.models.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.newsmanager.models.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDetailsImplBean implements UserDetails {
	private final UserEntity userEntity;
	private Locale locale;

	public UserDetailsImplBean(UserEntity userEntity){
		this.userEntity = userEntity;
		this.locale = localeUserInit(userEntity);
	}

	private Locale localeUserInit(UserEntity userEntity){
		String language = userEntity.getLocaleEntity().getLanguage();
		String country = userEntity.getLocaleEntity().getCountry();
		return new Locale(language, country);
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
