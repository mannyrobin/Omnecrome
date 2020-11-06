package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IRouteValidator;

import javax.validation.constraints.NotNull;

/**
 * ДТО маршрута.
 */
@IRouteValidator
public class RouteDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"Номер маршрута\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Номер маршрута\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String routeNumber;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"ID Маршрута в ВИС ASDU\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String asduRouteId;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Код Маршрута в ВИС ASKP\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String askpRouteCode;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Код Маршрута в ВИС ASMPP\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String asmppRouteCode;

   private Integer routeNumberInt;

   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код Маршрута ЕАСУ ФХД\" не должно превышать " + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String easuFhdRouteCode;

   @NotNull(message = "\"ID Маршрута в ВИС ГИС МГТ\" должен быть выбран.")
   private Long routeId;

   @NotNull(message = "\"Вид транспорта\" должен быть выбран.")
   private Long routeTsKindId;

   @NumberFormat(pattern = "\\d+(\\.\\d{1,2})?")
   private Double profitRatio;

   @NotNull(message = "Поле \"Ночной маршрут\" должено быть выбрано.")
   private Boolean isNight;

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Integer getRouteNumberInt() {
	  return routeNumberInt;
   }

   public void setRouteNumberInt(Integer routeNumberInt) {
	  this.routeNumberInt = routeNumberInt;
   }

   public String getAsduRouteId() {
	  return asduRouteId;
   }

   public void setAsduRouteId(String asduRouteId) {
	  this.asduRouteId = asduRouteId;
   }

   public String getAskpRouteCode() {
	  return askpRouteCode;
   }

   public void setAskpRouteCode(String askpRouteCode) {
	  this.askpRouteCode = askpRouteCode;
   }

   public String getAsmppRouteCode() {
	  return asmppRouteCode;
   }

   public void setAsmppRouteCode(String asmppRouteCode) {
	  this.asmppRouteCode = asmppRouteCode;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Long getRouteTsKindId() {
	  return routeTsKindId;
   }

   public void setRouteTsKindId(Long routeTsKindId) {
	  this.routeTsKindId = routeTsKindId;
   }

   public Double getProfitRatio() {
	  return profitRatio;
   }

   public void setProfitRatio(Double profitRatio) {
	  this.profitRatio = profitRatio;
   }

   public String getEasuFhdRouteCode() {
	  return easuFhdRouteCode;
   }

   public void setEasuFhdRouteCode(String easuFhdRouteCode) {
	  this.easuFhdRouteCode = easuFhdRouteCode;
   }

   public Boolean getIsNight() {
	  return isNight;
   }

   public void setIsNight(Boolean isNight) {
	  this.isNight = isNight;
   }

}
