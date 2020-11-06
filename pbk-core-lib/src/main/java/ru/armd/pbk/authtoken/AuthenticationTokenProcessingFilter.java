package ru.armd.pbk.authtoken;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;
import ru.armd.pbk.core.controllers.auth.AuthUserController;
import ru.armd.pbk.services.core.AuditService;
import ru.armd.pbk.services.core.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Фильтр для аутентификации по токену.
 */
public class AuthenticationTokenProcessingFilter
	extends GenericFilterBean {

   @Autowired
   AuthenticationTokenProcessingFilterProxy proxy;

   @Autowired
   private AuditService auditService;

   @Autowired
   private UserService service;

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	   throws IOException, ServletException {
	  HttpServletRequest httpRequest = this.getAsHttpRequest(request);
	  if (httpRequest.getMethod().equals("GET") && httpRequest.getRequestURI().endsWith("check-connection")) {
		 //Запрос на проверку соединения с базой.
		 checkDBConnection(response);
	  } else if (httpRequest.getMethod().equals("POST")
		  && httpRequest.getRequestURI().endsWith("/api" + AuthUserController.RM_CONTROLLER_PATH)) {
		 // тут не до токена.
		 proxy.doNextFilter(request, response, chain);
	  } else {
		 try {
			if (proxy.doAuth(httpRequest, response)) {
			   proxy.doNextFilter(request, response, chain);
			}
		 } catch (SignatureException ex) {
			proxy.fillUnautorizedCode(response);
			// хаккеры не иначе
			auditService.logLoginFail(ru.armd.pbk.authtoken.AuthenticationManager.getIpAddr(httpRequest), ex);
		 } catch (JwtException | UnsupportedEncodingException ex) {
			proxy.fillUnautorizedCode(response);
			// токен внутри ни о чём.
			auditService.logLoginFail(ru.armd.pbk.authtoken.AuthenticationManager.getIpAddr(httpRequest), ex);
		 } catch (Exception e) {
			auditService.logLoginFail(ru.armd.pbk.authtoken.AuthenticationManager.getIpAddr(httpRequest), e);
			proxy.fillUnautorizedCode(response);
		 }
	  }
   }

   private void checkDBConnection(ServletResponse response) {
	  if (response instanceof HttpServletResponse) {
		 HttpServletResponse resp = (HttpServletResponse) response;
		 if (service.checkDBConnection()) {
			resp.setStatus(HttpServletResponse.SC_OK);
		 } else {
			resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
		 }
	  }
   }

   private HttpServletRequest getAsHttpRequest(ServletRequest request) {
	  if (!(request instanceof HttpServletRequest)) {
		 throw new RuntimeException("Expecting an HTTP request");
	  }
	  return (HttpServletRequest) request;
   }
}