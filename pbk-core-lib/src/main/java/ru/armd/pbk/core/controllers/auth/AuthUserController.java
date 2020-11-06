package ru.armd.pbk.core.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.authtoken.PbkWebAuthenticationDetails;
import ru.armd.pbk.authtoken.SecurityAdapterUser;
import ru.armd.pbk.authtoken.transfers.LoginFormParams;
import ru.armd.pbk.authtoken.transfers.TokenTransfer;
import ru.armd.pbk.authtoken.transfers.UserTransfer;
import ru.armd.pbk.core.controllers.BaseControllerApi;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.authtoken.TokenSessionService;
import ru.armd.pbk.services.authtoken.TokenUtilsService;
import ru.armd.pbk.services.core.AuditService;
import ru.armd.pbk.utils.json.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Контроллер аутентификации.
 */
@Controller
@RequestMapping(AuthUserController.RM_CONTROLLER_PATH)
public class AuthUserController
	extends BaseControllerApi {

   public static final String RM_CONTROLLER_PATH = RM_API_PATH + "/auth";

   public static final String LOGOUT_PATH = "/logout";

   @Autowired
   private AuditService auditService;

   @Autowired
   private UserDetailsService userService;

   @Autowired
   private TokenSessionService sessionService;

   @Autowired
   private TokenUtilsService tokenUtilsService;

   @Autowired
   @Qualifier("authenticationManager")
   private AuthenticationManager authManager;

   /**
	* Отдаёт информацию о текущем пользователе.
	*
	* @return Json с информацией о пользователе.
	*/
   @RequestMapping(method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getUserDetails()
	   throws Exception {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  Object principal = authentication.getPrincipal();
	  if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
		 return new ResponseEntity<UserTransfer>(HttpStatus.UNAUTHORIZED);
	  }
	  UserDetails userDetails = (UserDetails) principal;
	  Long userId = null;
	  Boolean isLdapUser = false;
	  String name = userDetails.getUsername();
	  Set<String> roleNames = null;
	  String reportsAuth = null;
	  if (userDetails instanceof SecurityAdapterUser) {
		 userId = ((SecurityAdapterUser) userDetails).getUserId();
		 isLdapUser = ((SecurityAdapterUser) userDetails).isLdapUser();
		 name = ((SecurityAdapterUser) userDetails).getFullName();
		 roleNames = ((SecurityAdapterUser) userDetails).getRoleNames();
		 reportsAuth = ((SecurityAdapterUser) userDetails).getReportsAuth();
	  }
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(new UserTransfer(userId, userDetails.getUsername(), name, isLdapUser, this.createRoleMap(userDetails), roleNames, reportsAuth));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Метод проверки правильности введённого пароля. Используется при смене пароля.
	*
	* @param loginForm форма такая же как при логине.
	* @return
	*/
   @RequestMapping(value = "checkpass", method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<?> checkPass(@Valid @RequestBody LoginFormParams loginForm) {
	  JsonResult jsonResult = new JsonResult();
	  try {
		 jsonResult.setResult(true);
		 checkUserAuth(loginForm, null);
		 return new ResponseEntity<>(jsonResult, HttpStatus.OK);
	  } catch (AuthenticationException e) {
		 jsonResult.setResult(false);
		 return new ResponseEntity<>(jsonResult, HttpStatus.OK);
	  }
   }

   /**
	* Аутентификация пользователя. Если всё благополучно возвращает новый токен.
	*
	* @return A transfer containing the authentication token.
	*/
   @RequestMapping(method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<?> authenticate(@Valid @RequestBody LoginFormParams loginForm, HttpServletRequest request) {
	  try {
		 Authentication authentication = checkUserAuth(loginForm, request);

		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 UserDetails userDetails = this.userService.loadUserByUsername(loginForm.getUsername());
		 String token = tokenUtilsService.createToken(userDetails);
		 sessionService.createNewSession(userDetails, tokenUtilsService.getTokenId(token), request.getRemoteAddr(), token);
		 JsonResult jsonResult = new JsonResult();
		 jsonResult.setResult(new TokenTransfer(token));
		 auditService.logLoginSuccess(userDetails.getUsername(), ru.armd.pbk.authtoken.AuthenticationManager.getIpAddr(request));
		 return new ResponseEntity<>(jsonResult, HttpStatus.OK);
	  } catch (BadCredentialsException | LockedException | DisabledException | AccountExpiredException e) {
		 auditService.logLoginFail(ru.armd.pbk.authtoken.AuthenticationManager.getIpAddr(request), e);
		 throw e;
	  } catch (PBKException e) {
		 auditService.logLoginFail(ru.armd.pbk.authtoken.AuthenticationManager.getIpAddr(request), e);
		 throw e;
	  } catch (Exception e) {
		 auditService.logLoginFail(ru.armd.pbk.authtoken.AuthenticationManager.getIpAddr(request), e);
		 throw new PBKException("Ошбка при входе в систему. Ошибка: " + e.getMessage(), e);
	  }
   }

   private Authentication checkUserAuth(LoginFormParams loginForm, HttpServletRequest request) {
	  UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
	  if (request != null) {
		 authenticationToken.setDetails(new PbkWebAuthenticationDetails(request));
	  }
	  return this.authManager.authenticate(authenticationToken);
   }

   /**
	* Метод выхода пользователя из системы.
	*
	* @param token токен сессии.
	* @return
	*/
   @RequestMapping(value = LOGOUT_PATH, method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<?> logout(@RequestHeader(value = "X-Auth-Token") String token) {
	  if (token != null) {
		 String id = tokenUtilsService.getTokenId(token);
		 sessionService.deleteSession(id);
	  }
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
	  Map<String, Boolean> roles = new HashMap<String, Boolean>();
	  for (GrantedAuthority authority : userDetails.getAuthorities()) {
		 roles.put(authority.getAuthority(), Boolean.TRUE);
	  }
	  return roles;
   }

}