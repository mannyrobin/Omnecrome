package ru.armd.pbk.dto.nsi.bsonumberrule;

import org.hibernate.validator.constraints.Length;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.BsoNumberRuleUnique;

import javax.validation.constraints.NotNull;

/**
 * DTO для правил формирования номеров БСО.
 */
@BsoNumberRuleUnique
public class BsoNumberRuleDTO
	extends BaseDTO {

   @NotNull(message = "Поле \"Тип БСО\" не должно быть пустым.")
   private Long bsoTypeId;

   //@NotBlank(message = "Поле \"Тип БСО\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Тип БСО\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String bsoTypeCode;

   //@NotNull(message = "Поле \"Филиал\" не должно быть пустым.")
   private Long branchId;

   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код филиала\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String branchCode;

   @NotNull(message = "Поле \"Подразделение\" не должно быть пустым.")
   private Long departmentId;

   //@NotBlank(message = "Поле \"Код подразделения\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код подразделения\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String departmentCode;

   @NotNull(message = "Поле \"Текущее значение\" не должно быть пустым.")
   private Long increment;

   public Long getBsoTypeId() {
	  return bsoTypeId;
   }

   public void setBsoTypeId(Long bsoTypeId) {
	  this.bsoTypeId = bsoTypeId;
   }

   public String getBsoTypeCode() {
	  return bsoTypeCode;
   }

   public void setBsoTypeCode(String bsoTypeCode) {
	  this.bsoTypeCode = bsoTypeCode;
   }

   public Long getBranchId() {
	  return branchId;
   }

   public void setBranchId(Long branchId) {
	  this.branchId = branchId;
   }

   public String getBranchCode() {
	  return branchCode;
   }

   public void setBranchCode(String branchCode) {
	  this.branchCode = branchCode;
   }

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
   }

   public String getDepartmentCode() {
	  return departmentCode;
   }

   public void setDepartmentCode(String departmentCode) {
	  this.departmentCode = departmentCode;
   }

   public Long getIncrement() {
	  return increment;
   }

   public void setIncrement(Long increment) {
	  this.increment = increment;
   }
}
