package ru.armd.pbk.authtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.services.authtoken.TokenSessionService;
import ru.armd.pbk.services.authtoken.TokenUtilsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * В этот класс вынесены методы из AuthenticationTokenProcessingFilter,
 * т.к. @Transactional не работает не на @Override методах в фильтре
 */
@Component
public class AuthenticationTokenProcessingFilterProxy {

   @Autowired
   private UserDetailsService userService;

   @Autowired
   private TokenSessionService tokenSessionService;

   @Autowired
   private TokenUtilsService tokenUtilsService;

   /**
	* В методе всего два запроса к БД. Тесты показывают, что быстрее без транзакции
	*
	* @param httpRequest request.
	* @param response    response.
	* @return
	* @throws UnsupportedEncodingException
	*/
   //@Transactional(isolation = Isolation.READ_UNCOMMITTED)
   public boolean doAuth(HttpServletRequest httpRequest, ServletResponse response)
	   throws UnsupportedEncodingException {
	  String authToken = this.extractAuthTokenFromRequest(httpRequest);
	  String userName = tokenUtilsService.getUserNameFromToken(authToken);
	  String tokenUUID = tokenUtilsService.getTokenId(authToken);
	  if (userName != null) {
		 UserDetails userDetails = this.userService.loadUserByUsername(userName);
		 if (userDetails == null) {
			fillUnautorizedCode(response);
			// пользователя удалили, сессию удаляем тоже.
			tokenSessionService.deleteSession(tokenUUID);
		 } else if (tokenSessionService.checkAndProlongToken(tokenUUID)) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
			authentication.setDetails(new PbkWebAuthenticationDetails(httpRequest));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return true;
		 } else {
			fillUnautorizedCode(response);
		 }
	  } else {
		 // тут мутно
		 fillUnautorizedCode(response);
	  }

	  return false;
   }

   @Transactional(isolation = Isolation.READ_UNCOMMITTED)
   public void doNextFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	   throws IOException, ServletException {
	  chain.doFilter(request, response);
   }

   void fillUnautorizedCode(ServletResponse response) {
	  if (response instanceof HttpServletResponse) {
		 HttpServletResponse resp = (HttpServletResponse) response;
		 resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	  }
   }

   private String extractAuthTokenFromRequest(HttpServletRequest httpRequest)
	   throws UnsupportedEncodingException {
	  Cookie[] cookies = httpRequest.getCookies();
	  if (cookies != null) {
		 for (Cookie cookie : cookies) {
			if ("authToken".equals(cookie.getName())) {
			   String token = URLDecoder.decode(cookie.getValue(), "UTF-8");
			   return token.replace('\"', ' ').trim();
			}
		 }
	  }
	  String authToken = httpRequest.getHeader("X-Auth-Token");
	  if (authToken == null) {
		 authToken = httpRequest.getParameter("token");
	  }
	  return authToken;
   }
}