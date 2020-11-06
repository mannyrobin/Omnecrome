package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSeparatedListDateTimeSerializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yakov Volkov.
 * <p>
 * Данные для заполнения отчета "Статистика проверок маршрута"
 * с группировкой по fio+scm+scmo и т.д.
 */
public class So20MergedCheckResultView
	extends SoView {

   private List<Integer> numbers = new ArrayList<>();
   private String routeNumber;
   private List<String> moveCodes = new ArrayList<>();

   @JsonSerialize(using = DotSeparatedListDateTimeSerializer.class)
   private List<Date> checkTimes = new ArrayList<>();

   private String fio;
   private Integer scm;
   private Integer scmo;
   private Integer vesb;
   private Integer other;
   private List<String> mocs = new ArrayList<>();
   private List<String> shifts = new ArrayList<>();

   public String getType() {
	  return "So20MergedCheckResult";
   }

   public List<Integer> getNumbers() {
	  return numbers;
   }

   public void setNumbers(List<Integer> numbers) {
	  this.numbers = numbers;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public List<String> getMoveCodes() {
	  return moveCodes;
   }

   public void setMoveCodes(List<String> moveCodeLists) {
	  this.moveCodes = moveCodeLists;
   }

   public List<Date> getCheckTimes() {
	  return checkTimes;
   }

   public void setCheckTimes(List<Date> checkTimes) {
	  this.checkTimes = checkTimes;
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

   public List<String> getMocs() {
	  return mocs;
   }

   public void setMocs(List<String> mocs) {
	  this.mocs = mocs;
   }

   public List<String> getShifts() {
	  return shifts;
   }

   public void setShifts(List<String> shifts) {
	  this.shifts = shifts;
   }
}
