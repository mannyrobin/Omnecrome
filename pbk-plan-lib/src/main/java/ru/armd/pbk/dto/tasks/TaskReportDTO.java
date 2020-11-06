package ru.armd.pbk.dto.tasks;

import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * DTO для отчёта по заданию.
 */
public class TaskReportDTO
	extends BaseDTO {

   private Long taskId;

   @NotBlank(message = "Поле \"Количество проверенных единиц подвижного состава\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Количество проверенных единиц подвижного состава\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String tsCheckCount;

   @NotBlank(message = "Поле \"Количество полученных билетов для реализации\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Количество полученных билетов для реализации\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String ticketIssueCount;

   @NotBlank(message = "Поле \"Количество реализованных билетов\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Количество реализованных билетов\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String ticketSoldCount;

   @NotBlank(message = "Поле \"Изъятые социальные карты москвича\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Изъятые социальные карты москвича\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String exemptSkmCount;

   @NotBlank(message = "Поле \"Изъятые социальные карты жителя Московской области\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Изъятые социальные карты жителя Московской области\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String exemptSkmoCount;

   @NotBlank(message = "Поле \"Изъятые временные единые социальные билеты\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Изъятые временные единые социальные билеты\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String exemptVesbCount;

   @NotBlank(message = "Поле \"Изъятые прочие льготные персонифицированные карты\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Изъятые прочие льготные персонифицированные карты\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String exemptOtherLpkCount;

   @NotBlank(message = "Поле \"Нелегитимные\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Нелегитимные\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String exemptValidLessCount;

   @NotBlank(message = "Поле \"Высажено безбилетных пассажиров\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Высажено безбилетных пассажиров\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String plantStowawayCount;

   @NotBlank(message = "Поле \"Доставлено в ОВД\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Доставлено в ОВД\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String deliveryOvdCount;

   @NotBlank(message = "Поле \"Количество рапортов на работу водителей\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Количество рапортов на работу водителей\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String driverRaportCount;

   @NotBlank(message = "Поле \"Постановления 1000р\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Постановления 1000р\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String ordinance1000Count;

   @NotBlank(message = "Поле \"Постановления 2500р\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Постановления 2500р\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String ordinance2500Count;

   @NotBlank(message = "Поле \"Протокол 1000р\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Протокол 1000р\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String protocol1000Count;

   @NotBlank(message = "Поле \"Протокол 2500р\" должно быть заполнено.")
   @Pattern(regexp = StringUtils.DIGITS_INT_RANGE_REGEXP, message = "Поле \"Протокол 2500р\" должно быть введено неотрицательное число не больше " + Integer.MAX_VALUE + ".")
   private String protocol2500Count;

   private String routeCount;
   private String bskCount;

   private List<Long> gkuopEmployees;
   private List<Long> mgtEmployees;

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   public String getTsCheckCount() {
	  return tsCheckCount;
   }

   public void setTsCheckCount(String tsCheckCount) {
	  this.tsCheckCount = tsCheckCount;
   }

   public String getTicketIssueCount() {
	  return ticketIssueCount;
   }

   public void setTicketIssueCount(String ticketIssueCount) {
	  this.ticketIssueCount = ticketIssueCount;
   }

   public String getTicketSoldCount() {
	  return ticketSoldCount;
   }

   public void setTicketSoldCount(String ticketSoldCount) {
	  this.ticketSoldCount = ticketSoldCount;
   }

   public String getExemptSkmCount() {
	  return exemptSkmCount;
   }

   public void setExemptSkmCount(String exemptSkmCount) {
	  this.exemptSkmCount = exemptSkmCount;
   }

   public String getExemptSkmoCount() {
	  return exemptSkmoCount;
   }

   public void setExemptSkmoCount(String exemptSkmoCount) {
	  this.exemptSkmoCount = exemptSkmoCount;
   }

   public String getExemptVesbCount() {
	  return exemptVesbCount;
   }

   public void setExemptVesbCount(String exemptVesbCount) {
	  this.exemptVesbCount = exemptVesbCount;
   }

   public String getExemptOtherLpkCount() {
	  return exemptOtherLpkCount;
   }

   public void setExemptOtherLpkCount(String exemptOtherLpkCount) {
	  this.exemptOtherLpkCount = exemptOtherLpkCount;
   }

   public String getExemptValidLessCount() {
	  return exemptValidLessCount;
   }

   public void setExemptValidLessCount(String exemptValidLessCount) {
	  this.exemptValidLessCount = exemptValidLessCount;
   }

   public String getPlantStowawayCount() {
	  return plantStowawayCount;
   }

   public void setPlantStowawayCount(String plantStowawayCount) {
	  this.plantStowawayCount = plantStowawayCount;
   }

   public String getDeliveryOvdCount() {
	  return deliveryOvdCount;
   }

   public void setDeliveryOvdCount(String deliveryOvdCount) {
	  this.deliveryOvdCount = deliveryOvdCount;
   }

   public String getDriverRaportCount() {
	  return driverRaportCount;
   }

   public void setDriverRaportCount(String driverRaportCount) {
	  this.driverRaportCount = driverRaportCount;
   }

   public String getOrdinance1000Count() {
	  return ordinance1000Count;
   }

   public void setOrdinance1000Count(String ordinance1000Count) {
	  this.ordinance1000Count = ordinance1000Count;
   }

   public String getOrdinance2500Count() {
	  return ordinance2500Count;
   }

   public void setOrdinance2500Count(String ordinance2500Count) {
	  this.ordinance2500Count = ordinance2500Count;
   }

   public String getProtocol1000Count() {
	  return protocol1000Count;
   }

   public void setProtocol1000Count(String protocol1000Count) {
	  this.protocol1000Count = protocol1000Count;
   }

   public String getProtocol2500Count() {
	  return protocol2500Count;
   }

   public void setProtocol2500Count(String protocol2500Count) {
	  this.protocol2500Count = protocol2500Count;
   }

   public List<Long> getGkuopEmployees() {
	  return gkuopEmployees;
   }

   public void setGkuopEmployees(List<Long> gkuopEmployees) {
	  this.gkuopEmployees = gkuopEmployees;
   }

   public String getRouteCount() {
	  return routeCount;
   }

   public void setRouteCount(String routeCount) {
	  this.routeCount = routeCount;
   }

   public List<Long> getMgtEmployees() {
	  return mgtEmployees;
   }

   public void setMgtEmployees(List<Long> mgtEmployees) {
	  this.mgtEmployees = mgtEmployees;
   }

   public String getBskCount() {
	  return bskCount;
   }

   public void setBskCount(String bskCount) {
	  this.bskCount = bskCount;
   }

}