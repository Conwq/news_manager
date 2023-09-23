package org.example.newsmanager.config.listeners;

import org.example.newsmanager.models.bean.UserDetailsImplBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessEventListener implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
										HttpServletResponse httpServletResponse,
										Authentication authentication) throws IOException, ServletException {
		if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImplBean user) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() +
					"/user/authentication?localization=" + user.getLocale().getLanguage());
		}
	}
}
