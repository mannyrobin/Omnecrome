package ru.armd.pbk.test.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JwtTokenTest {

   @Test
   public void createToketTest() {

	  Key key = MacProvider.generateKey();
	  String s = Jwts.builder().setSubject("admin").setId("adsdsfufu").signWith(SignatureAlgorithm.HS512, key)
		  .compact();
	  String s2 = Jwts.builder().setSubject("admin2").setId("adsdsfufu").signWith(SignatureAlgorithm.HS512, key)
		  .compact();
	  System.out.println(s);
	  System.out.println(s2);
	  Assert.assertTrue(true);

	  try {
		 Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(s);
		 String id = claimsJws.getBody().getId();
		 String user = claimsJws.getBody().getSubject();
		 System.out.println(id);
		 System.out.println(user);
		 // OK, we can trust this JWT

	  } catch (SignatureException e) {
		 // don't trust the JWT!
	  }
   }

   @Test
   public void test() {
	  String pattern = "((?=.*\\d)(?=.*[a-z|A-Z|а-я|А-я]).{6,20})";
	  Pattern p = Pattern.compile(pattern);
	  Matcher m = p.matcher("123456я");
	  System.out.println(m.matches());
   }

}
