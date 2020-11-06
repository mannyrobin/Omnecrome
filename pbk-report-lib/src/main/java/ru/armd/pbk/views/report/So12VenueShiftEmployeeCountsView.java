package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;
import java.util.List;

/**
 * View для связки места встречи, часов работы и количеств контролёров по датам (для использования в отчёте
 * "Совместный график по местам встреч").
 */
public class So12VenueShiftEmployeeCountsView
	extends SoView {

   private So12VenueShiftView venue;
   private List<So12EmployeeCountView> employeeCounts;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date dateStart;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date dateEnd;

   /**
	* Конструктор по месту встречи-часам работы и количествам контролёров по датам + даты начала и конца периода.
	*
	* @param venue          место встречи-часы работы
	* @param employeeCounts количество контролёров по датам
	* @param dateStart      дата начала периода
	* @param dateEnd        дата конца периода
	*/
   public So12VenueShiftEmployeeCountsView(So12VenueShiftView venue, List<So12EmployeeCountView> employeeCounts, Date dateStart, Date dateEnd) {
	  if (venue != null) {
		 this.setCnt(venue.getCnt());
		 this.setId(venue.getId());
	  }
	  this.venue = venue;
	  this.employeeCounts = employeeCounts;
	  this.dateStart = dateStart;
	  this.dateEnd = dateEnd;
   }

   public So12VenueShiftView getVenue() {
	  return venue;
   }

   public void setVenue(So12VenueShiftView venue) {
	  this.venue = venue;
   }

   public List<So12EmployeeCountView> getEmployeeCounts() {
	  return employeeCounts;
   }

   public void setEmployeeCounts(List<So12EmployeeCountView> employeeCounts) {
	  this.employeeCounts = employeeCounts;
   }

   public Date getDateStart() {
	  return dateStart;
   }

   public void setDateStart(Date dateStart) {
	  this.dateStart = dateStart;
   }

   public Date getDateEnd() {
	  return dateEnd;
   }

   public void setDateEnd(Date dateEnd) {
	  this.dateEnd = dateEnd;
   }
}
