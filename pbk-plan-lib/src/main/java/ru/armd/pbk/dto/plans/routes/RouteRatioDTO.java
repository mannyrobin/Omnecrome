package ru.armd.pbk.dto.plans.routes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ДТО рейтинга маршрута.
 */
public class RouteRatioDTO
	extends BaseDTO {

   public boolean manual;

   @NotNull(message = "Дата рейтинга маршрута должна быть выбрана.")
   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date workDate;

   @NotNull(message = "Маршрут должен быть выбран.")
   private Long routeId;

   @NotNull(message = "Поле \"Рейтинг маршрута\" должно быть заполнено.")
   private Integer routeRatio;
   private String descr;


   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Integer getRouteRatio() {
	  return routeRatio;
   }

   public void setRouteRatio(Integer routeRatio) {
	  this.routeRatio = routeRatio;
   }

   public String getDescr() {
	  return descr;
   }

   public void setDescr(String descr) {
	  this.descr = descr;
   }

   public boolean isManual() {
	  return manual;
   }

   public void setManual(boolean manual) {
	  this.manual = manual;
   }
}