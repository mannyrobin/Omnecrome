package ru.armd.pbk.core.test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.armd.pbk.core.IHasLogger;

/**
 * Базовый класс всех тестов.
 */
public abstract class BaseTest
	implements IHasLogger {

   private static final Logger LOGGER = Logger.getLogger(BaseTest.class);

   protected final String AUTH_USER_NAME = "ADMIN";
   protected final String AUTH_USER_PASS = "ADMIN";

   @Autowired
   protected AuthenticationManager authenticationManager;

   @Autowired
   protected ApplicationContext applicationContext;


   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Метод логинит пользователя, чтобы использовать его данные для аудита.
	* Обычно, вызывается в блоке @Before.
	*
	* @param login    логин.
	* @param password пароль.
	* @throws Exception
	*/
   protected void loginUser(String login, String password)
	   throws Exception {
	  Authentication authenticationToken = new UsernamePasswordAuthenticationToken(login, password);
	  Authentication authentication = authenticationManager.authenticate(authenticationToken);
	  SecurityContextHolder.getContext().setAuthentication(authentication);
   }


   /**
	* Директория, где хранятся ресурсы для тестов.
	*
	* @return
	*/
   protected String getTestsDataDir() {
	  String dir = System.getProperty("user.dir") + "\\src\\test\\resources\\";
	  return dir;// .replace("\\", "/");
   }
}
