package ru.armd.pbk.authtoken;

import org.springframework.security.core.SpringSecurityCoreVersion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Информация о пользователе(стандартный класс не устроил методом получения ip).
 */
public class PbkWebAuthenticationDetails
	implements Serializable {

   private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

   private static final String UNKNOWN = "unknown";

   // ~ Instance fields
   // ================================================================================================

   private final String remoteAddress;
   private final String sessionId;

   // ~ Constructors
   // ===================================================================================================

   /**
	* Records the remote address and will also set the session Id if a session already
	* exists (it won't create one).
	*
	* @param request that the authentication request was received from
	*/
   public PbkWebAuthenticationDetails(HttpServletRequest request) {
	  this.remoteAddress = getIpAddr(request);
	  HttpSession session = request.getSession(false);
	  this.sessionId = (session != null) ? session.getId() : null;
   }

   // ~ Methods
   // ========================================================================================================

   public boolean equals(Object obj) {
	  if (obj instanceof PbkWebAuthenticationDetails) {
		 PbkWebAuthenticationDetails rhs = (PbkWebAuthenticationDetails) obj;

		 if ((remoteAddress == null) && (rhs.getRemoteAddress() != null)) {
			return false;
		 }

		 if ((remoteAddress != null) && (rhs.getRemoteAddress() == null)) {
			return false;
		 }

		 if (remoteAddress != null) {
			if (!remoteAddress.equals(rhs.getRemoteAddress())) {
			   return false;
			}
		 }

		 if ((sessionId == null) && (rhs.getSessionId() != null)) {
			return false;
		 }

		 if ((sessionId != null) && (rhs.getSessionId() == null)) {
			return false;
		 }

		 if (sessionId != null) {
			if (!sessionId.equals(rhs.getSessionId())) {
			   return false;
			}
		 }

		 return true;
	  }

	  return false;
   }

   /**
	* Indicates the TCP/IP address the authentication request was received from.
	*
	* @return the address
	*/
   public String getRemoteAddress() {
	  return remoteAddress;
   }

   /**
	* Indicates the <code>HttpSession</code> id the authentication request was received
	* from.
	*
	* @return the session ID
	*/
   public String getSessionId() {
	  return sessionId;
   }

   public int hashCode() {
	  int code = 7654;

	  if (this.remoteAddress != null) {
		 code = code * (this.remoteAddress.hashCode() % 7);
	  }

	  if (this.sessionId != null) {
		 code = code * (this.sessionId.hashCode() % 7);
	  }

	  return code;
   }

   public String toString() {
	  StringBuilder sb = new StringBuilder();
	  sb.append(super.toString()).append(": ");
	  sb.append("RemoteIpAddress: ").append(this.getRemoteAddress()).append("; ");
	  sb.append("SessionId: ").append(this.getSessionId());

	  return sb.toString();
   }

   private String getIpAddr(HttpServletRequest request) {
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
