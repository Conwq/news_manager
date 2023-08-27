package org.example.news_manager.config.listeners;

import org.example.news_manager.bean.UserDetailsImplBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class AuthenticationSuccessEventListener implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
										HttpServletResponse httpServletResponse,
										Authentication authentication) throws IOException, ServletException {
		if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImplBean user) {
			String formHtml = "<html><body>"
					+ "<form id=\"redirectForm\" method=\"post\" action=\"/user/authentication\">"
					+ 	"<input type=\"hidden\" name=\"localization\" value=\"" + user.getLocale().getLanguage() + "\">"
					+ "</form>"
					+ "<script>"
					+ 	"document.getElementById('redirectForm').submit();"
					+ "</script>"
					+ "</body></html>";

			httpServletResponse.setContentType("text/html;charset=UTF-8");
			httpServletResponse.getWriter().write(formHtml);
		}
	}
}
