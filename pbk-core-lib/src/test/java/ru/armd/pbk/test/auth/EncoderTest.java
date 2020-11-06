package ru.armd.pbk.test.auth;

import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 *
 */
public class EncoderTest {

   @Test
   public void testSpecialCharactersInPass() {
	  org.springframework.security.crypto.password.StandardPasswordEncoder encoder = new StandardPasswordEncoder("PBK");
	  String encoded = encoder.encode("SYSTEM");
	  System.out.println(encoded);
	  encoder.matches("SYSTEM", encoded);
   }
}

