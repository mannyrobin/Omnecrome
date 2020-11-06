package ru.armd.pbk.views.plans.brigades;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedTimeSerializer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * View бригад планов отдела.
 */
public class BrigadeVenueView
	extends BaseGridView {

   private Long id;
   private String venueName;
   private String countyName;
   private String districtName;
   private Long shiftId;
   @JsonSerialize(using = DotSepratedTimeSerializer.class)
   private Date shiftWorkStart;
   @JsonSerialize(using = DotSepratedTimeSerializer.class)
   private Date shiftWorkEnd;
   private Map<String, BrigadeView> brigades;
   private Map<String, String> reserveCounts = new HashMap<String, String>();
   private Boolean tpu;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getVenueName() {
	  return venueName;
   }

   public void setVenueName(String venueName) {
	  this.venueName = venueName;
   }

   public String getCountyName() {
	  return countyName;
   }

   public void setCountyName(String countyName) {
	  this.countyName = countyName;
   }

   public String getDistrictName() {
	  return districtName;
   }

   public void setDistrictName(String districtName) {
	  this.districtName = districtName;
   }

   public Date getShiftWorkStart() {
	  return shiftWorkStart;
   }

   public void setShiftWorkStart(Date shiftWorkStart) {
	  this.shiftWorkStart = shiftWorkStart;
   }

   public Date getShiftWorkEnd() {
	  return shiftWorkEnd;
   }

   public void setShiftWorkEnd(Date shiftWorkEnd) {
	  this.shiftWorkEnd = shiftWorkEnd;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public Map<String, BrigadeView> getBrigades() {
	  return brigades;
   }

   public void setBrigades(Map<String, BrigadeView> brigades) {
	  this.brigades = brigades;
   }

   public Map<String, String> getReserveCounts() {
	  return reserveCounts;
   }

   public void setReserveCounts(Map<String, String> reserveCounts) {
	  this.reserveCounts = reserveCounts;
   }

   public Boolean getTpu() {
	  return tpu;
   }

   public void setTpu(Boolean tpu) {
	  this.tpu = tpu;
   }

}