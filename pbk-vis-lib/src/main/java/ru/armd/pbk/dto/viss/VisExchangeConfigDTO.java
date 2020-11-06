package ru.armd.pbk.dto.viss;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDictionaryDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IVisExchangeConfigValidator;

import javax.validation.constraints.NotNull;

/**
 * DTO конфигурации взаимодействия с ВИС.
 */
@IVisExchangeConfigValidator
public class VisExchangeConfigDTO
	extends BaseDictionaryDTO {

   @NotNull(message = "\"Тип операции\" должен быть выбран.")
   private Long exchangeOperationId;

   @NotNull(message = "\"Объект взаимодействия\" должен быть выбран.")
   private Long exchangeObjectId;

   @NotNull(message = "\"Вид транспорта\" должен быть выбран.")
   private Long transportTypeId;

   @NotNull(message = "\"Активность\" должена быть выбрана.")
   private Integer isActive;

   @NotBlank(message = "Поле \"Адрес подключения\" должно быть заполнено.")
   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Адрес подключения\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String uri;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Логин\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String login;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Пароль\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String password;

   private String confirmPassword;

   public Long getExchangeOperationId() {
	  return exchangeOperationId;
   }

   public void setExchangeOperationId(Long exchangeOperationId) {
	  this.exchangeOperationId = exchangeOperationId;
   }

   public Long getExchangeObjectId() {
	  return exchangeObjectId;
   }

   public void setExchangeObjectId(Long exchangeObjectId) {
	  this.exchangeObjectId = exchangeObjectId;
   }

   public Long getTransportTypeId() {
	  return transportTypeId;
   }

   public void setTransportTypeId(Long transportTypeId) {
	  this.transportTypeId = transportTypeId;
   }

   public Integer getIsActive() {
	  return isActive;
   }

   public void setIsActive(Integer isActive) {
	  this.isActive = isActive;
   }

   public String getUri() {
	  return uri;
   }

   public void setUri(String uri) {
	  this.uri = uri;
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

   public String getConfirmPassword() {
	  return confirmPassword;
   }

   public void setConfirmPassword(String confirmPassword) {
	  this.confirmPassword = confirmPassword;
   }
}
