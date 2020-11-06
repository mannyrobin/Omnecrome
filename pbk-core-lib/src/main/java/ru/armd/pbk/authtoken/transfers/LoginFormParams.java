package ru.armd.pbk.authtoken.transfers;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.utils.StringUtils;

/**
 * Транспортный объект для получения формы логина. Логин и пароль передаются
 * здесь.
 */
public class LoginFormParams {
   @NotBlank(message = "Поле \"Пользователь\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Пользователь\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   String username;

   @NotBlank(message = "Поле \"Пароль\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Пароль\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   String password;

   public String getUsername() {
	  return username;
   }

   public void setUsername(String username) {
	  this.username = username;
   }

   public String getPassword() {
	  return password;
   }

   public void setPassword(String password) {
	  this.password = password;
   }
}
