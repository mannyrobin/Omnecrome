package ru.armd.pbk.utils;

/**
 * Точка с определением широты и долготы.
 */
public class WktGeomPoint {

   private String latitude;
   private String longitude;

   /**
	* Конструктор.
	*
	* @param latitude  широта
	* @param longitude долгота
	*/
   public WktGeomPoint(String latitude, String longitude) {
	  this.latitude = latitude;
	  this.longitude = longitude;
   }

   public String getLatitude() {
	  return latitude;
   }

   public void setLatitude(String latitude) {
	  this.latitude = latitude;
   }

   public String getLongitude() {
	  return longitude;
   }

   public void setLongitude(String longitude) {
	  this.longitude = longitude;
   }

}
