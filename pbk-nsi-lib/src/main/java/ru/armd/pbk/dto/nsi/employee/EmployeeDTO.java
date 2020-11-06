package ru.armd.pbk.dto.nsi.employee;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * DTO для редактирования пользователя.
 */
public class EmployeeDTO
	extends BaseVersionDTO
	implements IHasName {

   private Long userId;

   @NotBlank(message = "Поле \"Имя\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Имя\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Отчество\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String patronumic;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Фамилия\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String surname;

   @NotBlank(message = "Поле \"ID в ЕАСУ ФХД\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ID в ЕАСУ ФХД\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String easuFhdId;

   private Long departmentId;

   @Length(max = StringUtils.XX_SHORT_INPUT_SIZE, message = "Число символов в поле \"Должность\" не должно превышать "
	   + StringUtils.XX_SHORT_INPUT_SIZE + " символов.")
   private String positionName;

   @NotBlank(message = "Поле \"Табельный номер\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Табельный номер\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String personalNumber;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Реквизиты служебного удостоверения\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String licenceDetails;

   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Телефон\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String phone;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date fireDate;

   @NotNull(message = "\"Пол\" должен быть выбран")
   private Integer sexId;

   private Long contCardId;
   private Long puskId;
   private Long offCardId;
   private Long dvrId;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date periodStartDate;
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date periodEndDate;

   @NotNull(message = "Поле \"Участвует в планировании\" должно быть заполнено.")
   private Boolean forPlanUse;

   private Long countyId;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date licenseEndDate;

   public Long getUserId() {
	  return userId;
   }

   public void setUserId(Long userId) {
	  this.userId = userId;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
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

   public void setOffCardId(Long offCardId) {
	  this.offCardId = offCardId;
   }

   public Long getDvrId() {
	  return dvrId;
   }

   public void setDvrId(Long dvrId) {
	  this.dvrId = dvrId;
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

   public Boolean getForPlanUse() {
	  return forPlanUse;
   }

   public void setForPlanUse(Boolean forPlanUse) {
	  this.forPlanUse = forPlanUse;
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
}