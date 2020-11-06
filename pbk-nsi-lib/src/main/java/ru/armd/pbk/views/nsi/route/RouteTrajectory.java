package ru.armd.pbk.views.nsi.route;

import ru.armd.pbk.core.views.IView;

/**
 * Траектория маршрута.
 */
public class RouteTrajectory
	implements IView {

   private long id;
   private String routeNumber;
   private String wktLineStr;
   private Long trajectoryTypeId;
   private String trajectoryTypeName;
   private String routeType;

   public long getId() {
	  return id;
   }

   public void setId(long id) {
	  this.id = id;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public String getWktLineStr() {
	  return wktLineStr;
   }

   public void setWktLineStr(String wktLineStr) {
	  this.wktLineStr = wktLineStr;
   }

   public String getTrajectoryTypeName() {
	  return trajectoryTypeName;
   }

   public void setTrajectoryTypeName(String trajectoryTypeName) {
	  this.trajectoryTypeName = trajectoryTypeName;
   }

   public Long getTrajectoryTypeId() {
	  return trajectoryTypeId;
   }

   public void setTrajectoryTypeId(Long trajectoryTypeId) {
	  this.trajectoryTypeId = trajectoryTypeId;
   }

   public String getRouteType() {
	  return routeType;
   }

   public void setRouteType(String routeType) {
	  this.routeType = routeType;
   }
}
