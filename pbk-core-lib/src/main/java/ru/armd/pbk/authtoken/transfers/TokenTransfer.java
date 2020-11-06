package ru.armd.pbk.authtoken.transfers;

/**
 * Транспортный объект для передачи токена аутентификации.
 */
public class TokenTransfer {

   private final String token;

   /**
	* Конструктор принимает строку токена.
	*
	* @param token токен.
	*/
   public TokenTransfer(String token) {
	  this.token = token;
   }

   public String getToken() {
	  return this.token;
   }

}