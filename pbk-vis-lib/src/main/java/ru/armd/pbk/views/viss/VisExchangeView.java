package ru.armd.pbk.views.viss;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

/**
 * View журнала взаимодействия с ВИС.
 */
public class VisExchangeView
	extends BaseGridView {

   private Long id;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date workDate;
   private String exchangeState;
   private String vis;
   private String transportType;
   private String exchangeOperation;
   private String exchangeObject;
   private String errorContent;
   private String parameter;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public String getExchangeState() {
	  return exchangeState;
   }

   public void setExchangeState(String exchangeState) {
	  this.exchangeState = exchangeState;
   }

   public String getVis() {
	  return vis;
   }

   public void setVis(String vis) {
	  this.vis = vis;
   }

   public String getTransportType() {
	  return transportType;
   }

   public void setTransportType(String transportType) {
	  this.transportType = transportType;
   }

   public String getExchangeOperation() {
	  return exchangeOperation;
   }

   public void setExchangeOperation(String exchangeOperation) {
	  this.exchangeOperation = exchangeOperation;
   }

   public String getExchangeObject() {
	  return exchangeObject;
   }

   public void setExchangeObject(String exchangeObject) {
	  this.exchangeObject = exchangeObject;
   }

   public String getErrorContent() {
	  return errorContent;
   }

   public void setErrorContent(String errorContent) {
	  this.errorContent = errorContent;
   }

   public String getParameter() {
	  return parameter;
   }

   public void setParameter(String parameter) {
	  this.parameter = parameter;
   }
}
