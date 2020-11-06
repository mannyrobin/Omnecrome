package ru.armd.pbk.authtoken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Менеджер информирует текщем пользователи системы.
 */
public class AuthenticationManager {

   private static final String UNKNOWN = "unknown";

   /**
	* Возвращает информацию о залогиненом пользователе. Всегда возвращает
	* объект.
	*
	* @return
	*/
   public static SecurityAdapterUser getUserInfo() {
	  SecurityContext context = SecurityContextHolder.getContext();
	  if (context == null) {
		 return null;
	  }
	  Authentication authentication = context.getAuthentication();
	  if (authentication == null) {
		 return null;
	  }
	  Object object = authentication.getPrincipal();
	  if (!(object instanceof SecurityAdapterUser)) {
		 return null;
	  }
	  return (SecurityAdapterUser) object;
   }

   /**
	* Метод проверят, назначена ли привелегия пользовател.
	*
	* @param permission Строковое представление привилегии.
	* @return
	*/
   public static boolean hasPermission(String permission) {
	  if (permission == null) {
		 return false;
	  }
	  // get security context from thread local
	  SecurityContext context = SecurityContextHolder.getContext();
	  if (context == null) {
		 return false;
	  }

	  Authentication authentication = context.getAuthentication();
	  if (authentication == null) {
		 return false;
	  }

	  for (GrantedAuthority auth : authentication.getAuthorities()) {
		 if (permission.equals(auth.getAuthority())) {
			return true;
		 }
	  }
	  return false;
   }

   /**
	* Возвращает IP адрес пользователя.
	*
	* @return
	*/
   public static String getUserIPAddress() {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  String userIPAddress = "";
	  if (auth == null) {
		 return "";
	  }
	  if (!(auth.getDetails() instanceof PbkWebAuthenticationDetails)) {
		 return "";
	  }
	  PbkWebAuthenticationDetails wad = (PbkWebAuthenticationDetails) auth.getDetails();
	  if (wad != null) {
		 userIPAddress = wad.getRemoteAddress();
	  }
	  return userIPAddress;
   }

   /**
	* Получить ip адрес по запросу.
	*
	* @param request запрос
	* @return ip адрес
	*/
   public static String getIpAddr(HttpServletRequest request) {
	  if (request != null) {
		 String ip = request.getHeader("X-Forwarded-For");
		 if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		 }
		 if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		 }
		 if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		 }
		 if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		 }
		 if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		 }
		 return ip;
	  } else {
		 return "";
	  }
   }
}
