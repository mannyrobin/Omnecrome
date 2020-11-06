package ru.armd.pbk.dto.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;
import ru.armd.pbk.utils.validation.UserLoginCheckUnique;
import ru.armd.pbk.utils.validation.UserPasswordCheck;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Объект для редактирования пользователя.
 */
@UserLoginCheckUnique
@UserPasswordCheck
public class UserDTO
	extends BaseDTO {

   @NotBlank(message = "Поле \"Логин\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Логин\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String login;

   @NotBlank(message = "Поле \"Пароль\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, min = 6, message = "Число символов в поле \"Пароль\" не должно быть меньше " + 6 + " и превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   @Pattern(regexp = "((?=.*\\d)(?=.*[a-z|A-Z|а-я|А-я]).{6,128})", message = "Пароль должен содержать буквы и цифры")
   private String password;

   @NotBlank(message = "Поле \"Повторение пароля\" должно быть заполнено.")
   private String passwordRepeat;

   @NotBlank(message = "Поле \"Имя\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Имя\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date expirationDate;

   private Date ldapCreateDate;

   private Date loginAttemptDate;
   private Integer loginAttemptCount;

   @NotNull(message = "Поле \"Внешний пользователь\" должно быть заполнено.")
   private Boolean isLdap;

   @NotNull(message = "Поле \"Флаг активизации\" должно быть заполнено.")
   private Boolean isActive;

   private String reportsAuth;

   @Override
   public boolean equals(Object o) {
	  if (this == o) {
		 return true;
	  }

	  if (o == null || getClass() != o.getClass()) {
		 return false;
	  }

	  UserDTO userDTO = (UserDTO) o;

	  return new EqualsBuilder().append(login, userDTO.login).append(name, userDTO.name).append(expirationDate, userDTO.expirationDate)
		  .append(ldapCreateDate, userDTO.ldapCreateDate).isEquals();
   }

   @Override
   public int hashCode() {
	  return new HashCodeBuilder(17, 37).append(login).append(name).append(expirationDate).append(ldapCreateDate).toHashCode();
   }

   public String getLogin() {
	  return login;
   }

   public void setLogin(String login) {
	  this.login = login;
   }

   public String getPassword() {
	  return password;
   }

   public void setPassword(String password) {
	  this.password = password;
   }

   public String getPasswordRepeat() {
	  return passwordRepeat;
   }

   public void setPasswordRepeat(String passwordRepeat) {
	  this.passwordRepeat = passwordRepeat;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Date getExpirationDate() {
	  return expirationDate;
   }

   public void setExpirationDate(Date expirationDate) {
	  this.expirationDate = expirationDate;
   }

   public Date getLdapCreateDate() {
	  return ldapCreateDate;
   }

   public void setLdapCreateDate(Date ldapCreateDate) {
	  this.ldapCreateDate = ldapCreateDate;
   }

   public Boolean getIsLdap() {
	  return isLdap;
   }

   public void setIsLdap(Boolean isLdap) {
	  this.isLdap = isLdap;
   }

   public Boolean getIsActive() {
	  return isActive;
   }

   public void setIsActive(Boolean isActive) {
	  this.isActive = isActive;
   }

   public Date getLoginAttemptDate() {
	  return loginAttemptDate;
   }

   public void setLoginAttemptDate(Date loginAttemptDate) {
	  this.loginAttemptDate = loginAttemptDate;
   }

   public Integer getLoginAttemptCount() {
	  return loginAttemptCount;
   }

   public void setLoginAttemptCount(Integer loginAttemptCount) {
	  this.loginAttemptCount = loginAttemptCount;
   }

   public String getReportsAuth() {
	  return reportsAuth;
   }

   public void setReportsAuth(String reportsAuth) {
	  this.reportsAuth = reportsAuth;
   }
}
