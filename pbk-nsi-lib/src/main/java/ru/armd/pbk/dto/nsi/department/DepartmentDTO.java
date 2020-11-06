package ru.armd.pbk.dto.nsi.department;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IDepartmentValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * ДТО департамента.
 */
@IDepartmentValidator
public class DepartmentDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"ID в ЕАСУ ФХД\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ID в ЕАСУ ФХД\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String easuFhdId;

   @NotBlank(message = "Поле \"Название\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Название\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String name;

   private Long parentDeptId;

   @NotBlank(message = "Поле \"Полное наименование\" должно быть заполнено.")
   @Length(max = StringUtils.X_LONG_INPUT_SIZE, message = "Число символов в поле \"Полное наименование\" не должно превышать "
	   + StringUtils.X_LONG_INPUT_SIZE + " символов.")
   private String fullName;
   
   @NotBlank(message = "Поле \"Плановое количество КПТ\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Плановое количество КПТ\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String planCount;

   private Boolean forPlanUse;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   private String latitude;
   private String longitude;

   private Long employeeSignId;

   private List<Long> countyIds = new ArrayList<Long>();

   public String getLatitude() {
	  return latitude;
   }

   public void setLatitude(String latitude) {
	  this.latitude = latitude;
   }

   public String getLongitude() {
	  return longitude;
   }

   public void setLongitude(String longitude) {
	  this.longitude = longitude;
   }

   public Long getEmployeeSignId() {
	  return employeeSignId;
   }

   public void setEmployeeSignId(Long employeeSignId) {
	  this.employeeSignId = employeeSignId;
   }

   public String getEasuFhdId() {
	  return easuFhdId;
   }

   public void setEasuFhdId(String easuFhdId) {
	  this.easuFhdId = easuFhdId;
   }
   
   public String getPlanCount() {
	  return planCount;
   }

   public void setPlanCount(String planCount) {
	  this.planCount = planCount;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Long getParentDeptId() {
	  return parentDeptId;
   }

   public void setParentDeptId(Long parentDeptId) {
	  this.parentDeptId = parentDeptId;
   }

   public String getFullName() {
	  return fullName;
   }

   public void setFullName(String fullName) {
	  this.fullName = fullName;
   }

   public Boolean getForPlanUse() {
	  return forPlanUse;
   }

   public void setForPlanUse(Boolean forPlanUse) {
	  this.forPlanUse = forPlanUse;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public List<Long> getCountyIds() {
	  return countyIds;
   }

   public void setCountyIds(List<Long> countyIds) {
	  this.countyIds = countyIds;
   }
}