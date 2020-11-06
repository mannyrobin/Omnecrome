package ru.armd.pbk.domain.tasks;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Домен сущности "Карта к изъятию".
 */
public class TaskWithdrawnCard
	extends BaseDomain {

   private Long taskId;
   private Long cardId;
   private String cardNumber;
   private String violatorFio;
   private String ownerFio;
   private String actNumber;
   private Long routeId;
   private Long fileId;
   private Long bsoId;
   private Boolean legitimate;
   private String chipNum;

   public Boolean getLegitimate() {
	  return legitimate;
   }

   public void setLegitimate(Boolean legitimate) {
	  this.legitimate = legitimate;
   }

   public Long getBsoId() {
	  return bsoId;
   }

   public void setBsoId(Long bsoId) {
	  this.bsoId = bsoId;
   }

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   public Long getCardId() {
	  return cardId;
   }

   public void setCardId(Long cardId) {
	  this.cardId = cardId;
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

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Long getFileId() {
	  return fileId;
   }

   public void setFileId(Long fileId) {
	  this.fileId = fileId;
   }

    public String getChipNum() {
        return chipNum;
    }

    public void setChipNum(String chipNum) {
        this.chipNum = chipNum;
    }
}
