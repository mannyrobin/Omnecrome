package ru.armd.pbk.views.nsi.employee;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseVersionView;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

/**
 * View истории сотрудника.
 */
public class EmployeeHistoryView
	extends BaseVersionView {

   private String name;
   private String patronumic;
   private String surname;

   private String easuFhdId;
   private String positionName;
   private String personalNumber;
   private String licenceDetails;
   private String phone;
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date fireDate;
   private Integer sexId;
   private Long departmentId;
   private Long userId;
   private Long dvrId;
   private Long puskId;
   private Long contCardId;
   private Long offCardId;
   private String description;
   private Boolean forPlanUse;
   private Long cityCountyId;
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date licenseEndDate;

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

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
   }

   public String getEasuFhdId() {
	  return easuFhdId;
   }

   public void setEasuFhdId(String easuFhdId) {
	  this.easuFhdId = easuFhdId;
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

   public String getLicenceDetails() {
	  return licenceDetails;
   }

   public void setLicenceDetails(String licenceDetails) {
	  this.licenceDetails = licenceDetails;
   }

   public String getPhone() {
	  return phone;
   }

   public void setPhone(String phone) {
	  this.phone = phone;
   }

   public Date getFireDate() {
	  return fireDate;
   }

   public void setFireDate(Date fireDate) {
	  this.fireDate = fireDate;
   }

   public Integer getSexId() {
	  return sexId;
   }

   public void setSexId(Integer sexId) {
	  this.sexId = sexId;
   }

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
   }

   public Long getUserId() {
	  return userId;
   }

   public void setUserId(Long userId) {
	  this.userId = userId;
   }

   public Long getDvrId() {
	  return dvrId;
   }

   public void setDvrId(Long dvrId) {
	  this.dvrId = dvrId;
   }

   public Long getPuskId() {
	  return puskId;
   }

   public void setPuskId(Long puskId) {
	  this.puskId = puskId;
   }

   public Long getContCardId() {
	  return contCardId;
   }

   public void setContCardId(Long contCardId) {
	  this.contCardId = contCardId;
   }

   public Long getOffCardId() {
	  return offCardId;
   }

   public void setOffCardId(Long offCardId) {
	  this.offCardId = offCardId;
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

   public Long getCityCountyId() {
	  return cityCountyId;
   }

   public void setCityCountyId(Long cityCountyId) {
	  this.cityCountyId = cityCountyId;
   }

   public Date getLicenseEndDate() {
	  return licenseEndDate;
   }

   public void setLicenseEndDate(Date licenseEndDate) {
	  this.licenseEndDate = licenseEndDate;
   }

}