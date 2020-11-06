package ru.armd.pbk.views.nsi.route;

/**
 * Остановка на маршруте.
 */
public class RouteStop {

   private Long id;
   private Long routeId;
   private String name;
   private String wktPointStr;
   private String routeNames;
   private String nameWithoutDistrict;
   private Long trajectoryType;

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getWktPointStr() {
	  return wktPointStr;
   }

   public void setWktPointStr(String wktPointStr) {
	  this.wktPointStr = wktPointStr;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getRouteNames() {
	  return routeNames;
   }

   public void setRouteNames(String routeNames) {
	  this.routeNames = routeNames;
   }

   public String getNameWithoutDistrict() {
	  return nameWithoutDistrict;
   }

   public void setNameWithoutDistrict(String nameWithoutDistrict) {
	  this.nameWithoutDistrict = nameWithoutDistrict;
   }

   public Long getTrajectoryType() {
	  return trajectoryType;
   }

   public void setTrajectoryType(Long trajectoryType) {
	  this.trajectoryType = trajectoryType;
   }

}
