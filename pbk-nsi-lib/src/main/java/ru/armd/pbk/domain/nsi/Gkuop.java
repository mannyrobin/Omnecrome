package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен ГКУ ОП.
 */
public class Gkuop
	extends BaseVersionDomain {

   private String gkuopDeptName;
   private String surname;
   private String name;
   private String patronumic;
   private String positionName;
   private String personalNumber;
   private String visGkuopId;
   private String description;
   private Boolean forPlanUse = true;

   public String getGkuopDeptName() {
	  return gkuopDeptName;
   }

   public void setGkuopDeptName(String gkuopDeptName) {
	  this.gkuopDeptName = gkuopDeptName;
   }

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getPatronumic() {
	  return patronumic;
   }

   public void setPatronumic(String patronumic) {
	  this.patronumic = patronumic;
   }

   public String getPositionName() {
	  return positionName;
   }

   public void setPositionName(String positionName) {
	  this.positionName = positionName;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public String getVisGkuopId() {
	  return visGkuopId;
   }

   public void setVisGkuopId(String visGkuopId) {
	  this.visGkuopId = visGkuopId;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Boolean getForPlanUse() {
	  return forPlanUse;
   }

   public void setForPlanUse(Boolean forPlanUse) {
	  this.forPlanUse = forPlanUse;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("gkuopDeptName: ").append(StringUtils.nvl(getGkuopDeptName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("surname: ").append(StringUtils.nvl(getSurname(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("patronumic: ").append(StringUtils.nvl(getPatronumic(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("positionName: ").append(StringUtils.nvl(getPositionName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("personalNumber: ").append(StringUtils.nvl(getPersonalNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("visGkuopId: ").append(StringUtils.nvl(getVisGkuopId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
