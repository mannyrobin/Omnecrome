package ru.armd.pbk.authtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ru.armd.pbk.dto.core.UserDTO;
import ru.armd.pbk.enums.core.EmbeddedUser;
import ru.armd.pbk.services.core.UserService;

/**
 * Базовый провайдер аутентификации для пользователей заведённых в нашей
 * системе.(Информация о пользователях хранится в нашей БД).
 */
public class PbkSystemAuthProvider
	extends DaoAuthenticationProvider {

   @Autowired
   private UserService userService;

   /**
	* Аутентификация.
	*/
   public Authentication authenticate(Authentication authentication)
	   throws AuthenticationException {
	  UserDTO user = userService.getByLogin(authentication.getName());
	  if (user == null || (user.getIsLdap() && !user.getId().equals(EmbeddedUser.ADMIN.getId()))) {
		 return null;
	  }
	  try {
		 userService.checkAndUpdateUserActiveStatus(user);
		 return super.authenticate(authentication);
	  } catch (BadCredentialsException e) {
		 if (!user.getId().equals(EmbeddedUser.ADMIN.getId())) {
			userService.processBadLoginAttempt(user);
		 }
		 throw e;
	  }
   }
}
