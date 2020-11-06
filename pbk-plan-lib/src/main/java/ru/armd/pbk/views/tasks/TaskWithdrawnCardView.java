package ru.armd.pbk.views.tasks;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;

import java.util.Date;

/**
 * Представление для смущности "Карта к изъятию".
 */
public class TaskWithdrawnCardView
	extends BaseGridView {

   @JsonSerialize(using = TaskDateSerializer.class)
   private Date taskDate;

   private Long id;
   private String taskName;
   private String cardName;
   private String cardNumber;
   private String violatorFio;
   private String ownerFio;
   private String actNumber;
   private String routeNumber;
   private String employeeName;
   private String fileName;
   private String streamId;
   private String legitimate;
   private String chipNum;

   public String getLegitimate() {
	  return legitimate;
   }

   public void setLegitimate(String legitimate) {
	  this.legitimate = legitimate;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getTaskName() {
	  return taskName;
   }

   public void setTaskName(String taskName) {
	  this.taskName = taskName;
   }

   public String getCardName() {
	  return cardName;
   }

   public void setCardName(String cardName) {
	  this.cardName = cardName;
   }

   public String getCardNumber() {
	  return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
	  this.cardNumber = cardNumber;
   }

   public String getViolatorFio() {
	  return violatorFio;
   }

   public void setViolatorFio(String violatorFio) {
	  this.violatorFio = violatorFio;
   }

   public String getOwnerFio() {
	  return ownerFio;
   }

   public void setOwnerFio(String ownerFio) {
	  this.ownerFio = ownerFio;
   }

   public String getActNumber() {
	  return actNumber;
   }

   public void setActNumber(String actNumber) {
	  this.actNumber = actNumber;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Date getTaskDate() {
	  return taskDate;
   }

   public void setTaskDate(Date taskDate) {
	  this.taskDate = taskDate;
   }

   public String getEmployeeName() {
	  return employeeName;
   }

   public void setEmployeeName(String employeeName) {
	  this.employeeName = employeeName;
   }

   public String getFileName() {
	  return fileName;
   }

   public void setFileName(String fileName) {
	  this.fileName = fileName;
   }

   public String getStreamId() {
	  return streamId;
   }

   public void setStreamId(String streamId) {
	  this.streamId = streamId;
   }

    public String getChipNum() {
        return chipNum;
    }

    public void setChipNum(String chipNum) {
        this.chipNum = chipNum;
    }
}
