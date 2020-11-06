package ru.armd.pbk.domain.nsi.employee;

import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Домен сотрудника.
 */
public class Employee
	extends BaseVersionDomain
	implements IHasName {

   private String easuFhdId;
   private String name;
   private String patronumic;
   private String surname;
   private String positionName;
   private String personalNumber;
   private String licenceDetails;
   private String phone;
   private String description;
   private Date fireDate;
   private Integer sexId;

   private Long departmentId;
   private Long userId;
   private Long contCardId;
   private Long puskId;
   private Long offCardId;
   private Long dvrId;

   private Date periodStartDate;
   private Date periodEndDate;

   private Boolean forPlanUse = false;
   private Long countyId;
   private Date licenseEndDate;
   private byte[] photo;

   /**
	* Конструктор.
	*/
   public Employee() {
	  super();
   }

   /**
	* Конструктор.
	*
	* @param id     ИД.
	* @param deptId ИД подразделения.
	*/
   public Employee(Long id, Long deptId) {
	  super();
	  setId(id);
	  setDepartmentId(deptId);
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

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
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

   public Long getContCardId() {
	  return contCardId;
   }

   public void setContCardId(Long contCardId) {
	  this.contCardId = contCardId;
   }

   public Long getPuskId() {
	  return puskId;
   }

   public void setPuskId(Long puskId) {
	  this.puskId = puskId;
   }

   public Long getOffCardId() {
	  return offCardId;
   }

   public Boolean getForPlanUse() {
	  return forPlanUse;
   }

   public void setForPlanUse(Boolean forPlanUse) {
	  this.forPlanUse = forPlanUse;
   }

   public Date getPeriodStartDate() {
	  return periodStartDate;
   }

   public void setPeriodStartDate(Date periodStartDate) {
	  this.periodStartDate = periodStartDate;
   }

   public Date getPeriodEndDate() {
	  return periodEndDate;
   }

   public void setPeriodEndDate(Date periodEndDate) {
	  this.periodEndDate = periodEndDate;
   }

   public void setOffCardId(Long offCardId) {
	  this.offCardId = offCardId;
   }

   public Long getDvrId() {
	  return dvrId;
   }

   public void setDvrId(Long dvrId) {
	  this.dvrId = dvrId;
   }

   public String getEasuFhdId() {
	  return easuFhdId;
   }

   public void setEasuFhdId(String easuFhdId) {
	  this.easuFhdId = easuFhdId;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getCountyId() {
	  return countyId;
   }

   public void setCountyId(Long countyId) {
	  this.countyId = countyId;
   }

   public Date getLicenseEndDate() {
	  return licenseEndDate;
   }

   public void setLicenseEndDate(Date licenseEndDate) {
	  this.licenseEndDate = licenseEndDate;
   }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("patronumic: ").append(StringUtils.nvl(getPatronumic(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("surname: ").append(StringUtils.nvl(getSurname(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("easuFhdId: ").append(StringUtils.nvl(getEasuFhdId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("personalNumber: ").append(StringUtils.nvl(getPersonalNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("positionName: ").append(StringUtils.nvl(getPositionName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("licenceDetails: ").append(StringUtils.nvl(getLicenceDetails(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("phone: ").append(StringUtils.nvl(getPhone(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("fireDate: ").append(StringUtils.nvl(getFireDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("sexId: ").append(StringUtils.nvl(getSexId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("departmentId: ").append(StringUtils.nvl(getDepartmentId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("userId: ").append(StringUtils.nvl(getUserId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("contCardId: ").append(StringUtils.nvl(getContCardId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("puskId: ").append(StringUtils.nvl(getPuskId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("offCardId: ").append(StringUtils.nvl(getOffCardId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("dvrId: ").append(StringUtils.nvl(getDvrId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("periodStartDate: ").append(StringUtils.nvl(getPeriodStartDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("periodEndDate: ").append(StringUtils.nvl(getPeriodEndDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("forPlanUse: ").append(StringUtils.nvl(getForPlanUse(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("countyId: ").append(StringUtils.nvl(getCountyId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("licenseEndDate: ").append(StringUtils.nvl(getLicenseEndDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("photo: ").append(StringUtils.nvl(getPhoto(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

}
