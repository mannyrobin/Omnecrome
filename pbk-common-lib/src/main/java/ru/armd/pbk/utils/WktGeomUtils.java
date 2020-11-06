package ru.armd.pbk.utils;

/**
 * Утилита для работы с широтой и долготой.
 */
public class WktGeomUtils {

   /**
	* Преобразование строки в точку со строковыми параметрами.
	*
	* @param str строка
	* @return
	*/
   public static WktGeomPoint stringToWktGeomPoint(String str) {
	  if (str != null) {
		 String[] location = str.split(" ");
		 if (location.length == 2) {
			return new WktGeomPoint(location[1].substring(0, location[1].length() - 1), location[0].substring(6));
		 }
	  }
	  return null;
   }

   /**
	* Преобразование данных точки в строку.
	*
	* @param wktGeom точка.
	* @return
	*/
   public static String wktGeomPointToString(WktGeomPoint wktGeom) {
	  if (wktGeom.getLatitude() != null && wktGeom.getLongitude() != null) {
		 return "POINT(".concat(wktGeom.getLongitude()).concat(" ").concat(wktGeom.getLatitude().concat(")"));
	  }
	  return null;
   }

   /**
	* Преобразование строки в точку с double параметрами.
	*
	* @param str строка
	* @return
	*/
   public static DoubleGeomPoint stringToDoubleGeomPoint(String str) {
	  WktGeomPoint wkt = WktGeomUtils.stringToWktGeomPoint(str);
	  if (wkt != null) {
		 DoubleGeomPoint point = new DoubleGeomPoint(Double.parseDouble(wkt.getLatitude()), Double.parseDouble(wkt.getLongitude()));
		 return point;
	  }
	  return null;
   }

   /**
	* Рассчет дистанции.
	*
	* @param lat1 широта
	* @param lon1 долгота
	* @param lat2 широта
	* @param lon2 долгота
	* @return
	*/
   public static double distance(double lat1, double lon1, double lat2, double lon2) {
	  double earthRadius = 6371000; //meters
	  double dLat = Math.toRadians(lat2 - lat1);
	  double dLng = Math.toRadians(lon2 - lon1);
	  double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
		  + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
		  * Math.sin(dLng / 2) * Math.sin(dLng / 2);
	  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	  return earthRadius * c;
   }
}
