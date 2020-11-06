package ru.armd.pbk.authtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.armd.pbk.domain.core.User;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.repositories.core.UserRepository;
import ru.armd.pbk.services.authtoken.LdapService;

/**
 * Провайдер аутенфикации для ldap пользоватей. В текущей реализации второй в
 * цепочке.
 */
public class LdapAuthenticationProvider
	implements AuthenticationProvider {

   @Autowired
   private UserRepository userRepo;

   @Autowired
   private UserDetailsService userDetailsService;

   @Autowired
   private LdapService ldapService;

   private PasswordEncoder passwordEncoder;

   private static class StubService {
	  public static void login(String userName, String pass) {

	  }
   }

   @Override
   public Authentication authenticate(Authentication authentication)
	   throws AuthenticationException {
	  User user = userRepo.getByLogin(authentication.getName());
	  if (user != null && !user.isLdap()) {
		 // значит пользователь из нашей системы. но залогинится не смог
		 return null;
	  }
	  UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
	  ldapService.login(authentication.getName(), authentication.getCredentials().toString());
	  UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
	  if (userDetails == null) {
		 throw new PBKException(
			 "Информация о ldap пользователе отсутвует в базе системы PBK. Не удалось загрузить роли пользователя.");
	  }
	  return createSuccessAuthentication(userDetails, authentication, userDetails);
   }

   protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
														UserDetails user) {
	  UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal,
		  authentication.getCredentials(), user.getAuthorities());
	  result.setDetails(authentication.getDetails());
	  return result;
   }

   protected void additionalAuthenticationChecks(UserDetails userDetails,
												 UsernamePasswordAuthenticationToken authentication)
	   throws AuthenticationException {
	  if (authentication.getCredentials() == null) {
		 throw new BadCredentialsException("Bad credentials");
	  }
	  String presentedPassword = authentication.getCredentials().toString();
	  if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
		 throw new BadCredentialsException("Bad credentials");
	  }
   }

   @Override
   public boolean supports(Class<?> authentication) {
	  return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
   }

   public PasswordEncoder getPasswordEncoder() {
	  return passwordEncoder;
   }

   public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
	  this.passwordEncoder = passwordEncoder;
   }
}
