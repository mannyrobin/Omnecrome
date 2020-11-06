package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен водителя.
 */
public class Driver
	extends BaseVersionDomain {

   private String asduDriverId;
   private String asduDepotId;
   private String surname;
   private String name;
   private String patronumic;
   private String personalNumber;

   public String getAsduDriverId() {
	  return asduDriverId;
   }

   public void setAsduDriverId(String asduDriverId) {
	  this.asduDriverId = asduDriverId;
   }

   public String getAsduDepotId() {
	  return asduDepotId;
   }

   public void setAsduDepotId(String asduDepotId) {
	  this.asduDepotId = asduDepotId;
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

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("asduDriverId: ").append(StringUtils.nvl(getAsduDriverId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("asduDepotId: ").append(StringUtils.nvl(getAsduDepotId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("surname: ").append(StringUtils.nvl(getSurname(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("patronumic: ").append(StringUtils.nvl(getPatronumic(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("personalNumber: ").append(StringUtils.nvl(getPersonalNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}

