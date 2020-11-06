package ru.armd.pbk.dto.nsi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;
import ru.armd.pbk.utils.validation.CalendarCheck;

import java.util.Date;

/**
 * DTO календаря.
 */
@CalendarCheck
public class CalendarDTO
	extends BaseDTO {

   private String code;
   private String name;
   private String description;

   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date workDate;
   private Integer workDayTypeId;

   public String getCode() {
	  return code;
   }

   public void setCode(String code) {
	  this.code = code;
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

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getWorkDayTypeId() {
	  return workDayTypeId;
   }

   public void setWorkDayTypeId(Integer workDayTypeId) {
	  this.workDayTypeId = workDayTypeId;
   }
}