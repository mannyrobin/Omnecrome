package ru.armd.pbk.dto.nsi.district;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * ДТО района.
 */
public class DistrictDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"Название\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Название\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать " + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   @NotBlank(message = "Поле \"Код\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код\" не должно превышать " + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String cod;

   @NotNull(message = "\"Округ\" должен быть выбран.")
   private Long countyId;

   @NotNull(message = "\"ГИС МГТ Район\" должен быть выбран.")
   private Long gmRegionId;

   @NotNull(message = "\"Округ для планирования\" должен быть выбран.")
   private Long planCountyId;

   public Long getGmRegionId() {
	  return gmRegionId;
   }

   public void setGmRegionId(Long gmRegionId) {
	  this.gmRegionId = gmRegionId;
   }

   public Long getCountyId() {
	  return countyId;
   }

   public void setCountyId(Long countyId) {
	  this.countyId = countyId;
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

   public String getCod() {
	  return cod;
   }

   public void setCod(String cod) {
	  this.cod = cod;
   }

   public Long getPlanCountyId() {
	  return planCountyId;
   }

   public void setPlanCountyId(Long planCountyId) {
	  this.planCountyId = planCountyId;
   }

}
