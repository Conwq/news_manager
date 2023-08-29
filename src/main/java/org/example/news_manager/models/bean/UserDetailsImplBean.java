package org.example.news_manager.models.bean;

import org.example.news_manager.models.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;

public class UserDetailsImplBean implements UserDetails {
	private final UserEntity userEntity;
	private Locale locale;

	public UserDetailsImplBean(UserEntity userEntity){
		this.userEntity = userEntity;
		this.locale = localeUserInit(userEntity);
	}

	public Locale localeUserInit(UserEntity userEntity){
		String language = userEntity.getLocaleEntity().getLanguage();
		String country = userEntity.getLocaleEntity().getCountry();
		return new Locale(language, country);
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale){
		this.locale = locale;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDetailsImplBean that = (UserDetailsImplBean) o;
		return Objects.equals(userEntity, that.userEntity) && Objects.equals(locale, that.locale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userEntity, locale);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"userEntity=" + userEntity +
				", locale=" + locale +
				'}';
	}
}
