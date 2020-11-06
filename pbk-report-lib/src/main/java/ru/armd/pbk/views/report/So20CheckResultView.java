package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSepratedDateTimeSerializer;

import java.util.Date;

/**
 * Created by Yakov Volkov.
 * <p>
 * Данные для заполнения отчета "Статистика проверок маршрута"
 */
public class So20CheckResultView
	extends SoView {

   private String routeNumber;
   private String moveCode;

   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date checkTime;

   private String fio;
   private Integer scm;
   private Integer scmo;
   private Integer vesb;
   private Integer other;
   private String moc;
   private String shift;

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public Date getCheckTime() {
	  return checkTime;
   }

   public void setCheckTime(Date checkTime) {
	  this.checkTime = checkTime;
   }

   public String getFio() {
	  return fio;
   }

   public void setFio(String fio) {
	  this.fio = fio;
   }

   public Integer getScm() {
	  return scm;
   }

   public void setScm(Integer scm) {
	  this.scm = scm;
   }

   public Integer getScmo() {
	  return scmo;
   }

   public void setScmo(Integer scmo) {
	  this.scmo = scmo;
   }

   public Integer getVesb() {
	  return vesb;
   }

   public void setVesb(Integer vesb) {
	  this.vesb = vesb;
   }

   public Integer getOther() {
	  return other;
   }

   public void setOther(Integer other) {
	  this.other = other;
   }

   public String getMoc() {
	  return moc;
   }

   public void setMoc(String moc) {
	  this.moc = moc;
   }

   public String getShift() {
	  return shift;
   }

   public void setShift(String shift) {
	  this.shift = shift;
   }
}
