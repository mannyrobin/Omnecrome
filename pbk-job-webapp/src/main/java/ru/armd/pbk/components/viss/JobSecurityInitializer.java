package ru.armd.pbk.components.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Инициализирует контекст безопасности, логинится системный пользователь.
 */
@Component
public class JobSecurityInitializer
	implements ApplicationListener<ContextRefreshedEvent> {

   protected final String AUTH_USER_NAME = "SYSTEM";
   protected final String AUTH_USER_PASS = "SYSTEM";

   @Autowired
   protected AuthenticationManager authenticationManager;

   @Override
   public void onApplicationEvent(ContextRefreshedEvent event) {
	  Authentication authenticationToken = new UsernamePasswordAuthenticationToken(AUTH_USER_NAME, AUTH_USER_PASS);
	  Authentication authentication = authenticationManager.authenticate(authenticationToken);
	  SecurityContextHolder.getContext().setAuthentication(authentication);
   }
}
