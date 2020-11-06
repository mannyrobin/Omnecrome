package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * View отчетов по сотрудникам.
 */
public class EmployeeReportView
	implements IReportView {

   private Long id;
   private String easuFhdId;
   private String name;
   private String patronumic;
   private String surname;
   private String emplPost;
   private String personalNumber;
   private String licenceDetails;
   private String phone;
   private Date fireDate;
   private Date periodStartDate;
   private String departmentId;
   private String contCardId;
   private String puskId;
   private String offCardId;

   @Override
   public List<Object> getReportList() {
	  StringBuilder bfio = new StringBuilder();
	  bfio.append(surname).append(' ').append(name).append(' ')
		  .append(patronumic);
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(bfio.toString());
	  result.add(easuFhdId);
	  result.add(emplPost);
	  result.add(personalNumber);
	  result.add(licenceDetails);
	  result.add(phone);
	  result.add(departmentId);
	  result.add(contCardId);
	  result.add(puskId);
	  result.add(offCardId);
	  result.add(periodStartDate == null ? null : DateUtils.makeGeneralDateFormat().format(periodStartDate));
	  result.add(fireDate == null ? null : DateUtils.makeGeneralDateFormat().format(fireDate));
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "ФИО", "ID ЕАСУ ФХД", "Должность",
		  "Табельный номер", "Служебное удостоверение", "Телефон",
		  "Подразделение", "БСК", "ПУсК", "СКК", "Дата приема на работу", "Дата увольнения");
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getEasuFhdId() {
	  return easuFhdId;
   }

   public void setEasuFhdId(String easuFhdId) {
	  this.easuFhdId = easuFhdId;
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

    public String getEmplPost() {
        return emplPost;
    }

    public void setEmplPost(String emplPost) {
        this.emplPost = emplPost;
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
   
   public Date getPeriodStartDate() {
		  return periodStartDate;
	   }

   public void setPeriodStartDate(Date periodStartDate) {
		  this.periodStartDate = periodStartDate;
	   }

   public Date getFireDate() {
	  return fireDate;
   }

   public void setFireDate(Date fireDate) {
	  this.fireDate = fireDate;
   }

   public String getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(String departmentId) {
	  this.departmentId = departmentId;
   }

   public String getContCardId() {
	  return contCardId;
   }

   public void setContCardId(String contCardId) {
	  this.contCardId = contCardId;
   }

   public String getPuskId() {
	  return puskId;
   }

   public void setPuskId(String puskId) {
	  this.puskId = puskId;
   }

   public String getOffCardId() {
	  return offCardId;
   }

   public void setOffCardId(String offCardId) {
	  this.offCardId = offCardId;
   }

}
