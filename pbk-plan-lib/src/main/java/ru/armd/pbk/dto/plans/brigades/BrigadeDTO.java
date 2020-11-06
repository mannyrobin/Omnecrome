package ru.armd.pbk.dto.plans.brigades;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;
import ru.armd.pbk.utils.validation.IBrigadeValidator;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ДТО бригады.
 */
@IBrigadeValidator
public class BrigadeDTO
	extends BaseDTO {

   @NotNull(message = "Подразделение должно быть выбрано.")
   private Long deptId;

   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date planDate;

   @NotNull(message = "Смена должна быть выбрана.")
   private Long shiftId;

   @NotNull(message = "Место встречи должно быть выбрано.")
   private Long cityVenueId;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date dateFrom;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date dateTo;

   private Integer mgtCount;
   private Integer gkuopCount;

   private boolean manual;

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public Date getPlanDate() {
	  return planDate;
   }

   public void setPlanDate(Date planDate) {
	  this.planDate = planDate;
   }

   public Long getCityVenueId() {
	  return cityVenueId;
   }

   public void setCityVenueId(Long cityVenueId) {
	  this.cityVenueId = cityVenueId;
   }

   public Integer getMgtCount() {
	  return mgtCount;
   }

   public void setMgtCount(Integer mgtCount) {
	  this.mgtCount = mgtCount;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public Integer getGkuopCount() {
	  return gkuopCount;
   }

   public void setGkuopCount(Integer gkuopCount) {
	  this.gkuopCount = gkuopCount;
   }

   public Date getDateFrom() {
	  return dateFrom;
   }

   public void setDateFrom(Date dateFrom) {
	  this.dateFrom = dateFrom;
   }

   public Date getDateTo() {
	  return dateTo;
   }

   public void setDateTo(Date dateTo) {
	  this.dateTo = dateTo;
   }

   public boolean isManual() {
	  return manual;
   }

   public void setManual(boolean manual) {
	  this.manual = manual;
   }
}