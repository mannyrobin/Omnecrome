package ru.armd.pbk.views.nsi.district;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * Представление района.
 */
public class DistrictView
	extends BaseGridView {

   private Long id;

   private String cod;

   private String name;

   private String countyName;

   private Long countRoute;

   private Long countVenues;

   private boolean isDelete;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getCod() {
	  return cod;
   }

   public void setCod(String cod) {
	  this.cod = cod;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getCountyName() {
	  return countyName;
   }

   public void setCountyName(String countyName) {
	  this.countyName = countyName;
   }

   public Long getCountRoute() {
	  return countRoute;
   }

   public void setCountRoute(Long countRoute) {
	  this.countRoute = countRoute;
   }

   public Long getCountVenues() {
	  return countVenues;
   }

   public void setCountVenues(Long countVenues) {
	  this.countVenues = countVenues;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }
}
