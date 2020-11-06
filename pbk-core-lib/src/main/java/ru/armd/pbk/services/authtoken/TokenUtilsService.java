package ru.armd.pbk.services.authtoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Утилитный сервис для токенов. Позволяет создавать и расшифровывать токены.
 */
@Service
public class TokenUtilsService {

   @Autowired
   private SecurityKeyService service;

   /**
	* Создаёт новый токен.
	*
	* @param userDetails информация о пользователе.
	* @return
	*/
   public String createToken(UserDetails userDetails) {
	  String s = Jwts.builder().setSubject(userDetails.getUsername()).setId(UUID.randomUUID().toString())
		  .signWith(SignatureAlgorithm.HS512, service.retriveKey()).compact();
	  return s;
   }

   /**
	* Извлекает имя пользователя из строки токена.
	*
	* @param authToken токен.
	* @return
	*/
   public String getUserNameFromToken(String authToken) {
	  if (null == authToken) {
		 return null;
	  }
	  Jws<Claims> claimsJws = Jwts.parser().setSigningKey(service.retriveKey()).parseClaimsJws(authToken);
	  String user = claimsJws.getBody().getSubject();
	  return user;
   }

   /**
	* Извлекает идентификатор из строки токена.
	*
	* @param authToken токен.
	* @return
	*/
   public String getTokenId(String authToken) {
	  if (null == authToken) {
		 return null;
	  }
	  Jws<Claims> claimsJws = Jwts.parser().setSigningKey(service.retriveKey()).parseClaimsJws(authToken);
	  String id = claimsJws.getBody().getId();
	  return id;
   }

}
