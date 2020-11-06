package ru.armd.pbk.dto.viss;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

/**
 * DTO журнала взаимодействий с ВИС.
 */
public class VisExchangeDTO
	extends BaseDTO {

   private Long configId;
   private Long stateId;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date workDate;
   private Integer isDayFail;
   private String errorContent;

   private String stateName;
   private String configName;
   private String transportTypeName;
   private String exchangeOperationName;
   private String exchangeObjectName;
   private String visName;
   private String dayFail;

   public String getStateName() {
	  return stateName;
   }

   public void setStateName(String stateName) {
	  this.stateName = stateName;
   }

   public String getConfigName() {
	  return configName;
   }

   public void setConfigName(String configName) {
	  this.configName = configName;
   }

   public String getTransportTypeName() {
	  return transportTypeName;
   }

   public void setTransportTypeName(String transportTypeName) {
	  this.transportTypeName = transportTypeName;
   }

   public String getExchangeOperationName() {
	  return exchangeOperationName;
   }

   public void setExchangeOperationName(String exchangeOperationName) {
	  this.exchangeOperationName = exchangeOperationName;
   }

   public String getExchangeObjectName() {
	  return exchangeObjectName;
   }

   public void setExchangeObjectName(String exchangeObjectName) {
	  this.exchangeObjectName = exchangeObjectName;
   }

   public Long getConfigId() {
	  return configId;
   }

   public void setConfigId(Long configId) {
	  this.configId = configId;
   }

   public Long getStateId() {
	  return stateId;
   }

   public void setStateId(Long stateId) {
	  this.stateId = stateId;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getIsDayFail() {
	  return isDayFail;
   }

   public void setIsDayFail(Integer isDayFail) {
	  this.isDayFail = isDayFail;
   }

   public String getErrorContent() {
	  return errorContent;
   }

   public void setErrorContent(String errorContent) {
	  this.errorContent = errorContent;
   }

   public String getVisName() {
	  return visName;
   }

   public void setVisName(String visName) {
	  this.visName = visName;
   }

   public String getDayFail() {
	  return dayFail;
   }

   public void setDayFail(String dayFail) {
	  this.dayFail = dayFail;
   }

}