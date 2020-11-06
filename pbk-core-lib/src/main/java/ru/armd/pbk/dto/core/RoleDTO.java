package ru.armd.pbk.dto.core;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IRoleValidator;

import javax.validation.constraints.NotNull;

/**
 * Объект dto для роли пользователя.
 */
@IRoleValidator
public class RoleDTO
	extends BaseDTO {

   @NotBlank(message = "Поле \"Имя\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Имя\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String name;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Имя группы в базе Active Directory\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String ldapRole;

   @NotNull(message = "Доступ ко всем подразделениям должен быть определен.")
   private Boolean isAllDepartments;

   /**
	* Конструктор по умолчанию.
	*/
   public RoleDTO() {
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getLdapRole() {
	  return ldapRole;
   }

   public void setLdapRole(String ldapRole) {
	  this.ldapRole = ldapRole;
   }

   public Boolean getIsAllDepartments() {
	  return isAllDepartments;
   }

   public void setIsAllDepartments(Boolean isAllDepartments) {
	  this.isAllDepartments = isAllDepartments;
   }
}
