package ru.armd.pbk.utils;

/**
 * Точка с широтой и долготой.
 */
public class DoubleGeomPoint {

   private Double latitude;
   private Double longitude;

   /**
	* Конструктор.
	*
	* @param latitude  широта
	* @param longitude долгота
	*/
   public DoubleGeomPoint(Double latitude, Double longitude) {
	  this.latitude = latitude;
	  this.longitude = longitude;
   }

   public Double getLatitude() {
	  return latitude;
   }

   public void setLatitude(Double latitude) {
	  this.latitude = latitude;
   }

   public Double getLongitude() {
	  return longitude;
   }

   public void setLongitude(Double longitude) {
	  this.longitude = longitude;
   }
}
