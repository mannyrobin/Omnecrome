package ru.armd.pbk.utils;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Утилита для генерации пароля для Админа при обновлении БД.
 */
public class AdminPasswordGenerator {

   private static final Logger LOGGER = Logger.getLogger(AdminPasswordGenerator.class);

   /**
	* Точка входа.
	*
	* @param args Аргументы.
	*/
   public static void main(String[] args) {
	  StandardPasswordEncoder encoder = new StandardPasswordEncoder("PBK");
	  String encodePass = encoder.encode("ADMIN");
	  LOGGER.debug("encodePass.length() = " + encodePass.length());
	  LOGGER.debug("encodePass = " + encodePass);
   }

}
