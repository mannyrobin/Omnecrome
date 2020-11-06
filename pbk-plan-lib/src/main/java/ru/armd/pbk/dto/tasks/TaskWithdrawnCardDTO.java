package ru.armd.pbk.dto.tasks;

import org.hibernate.validator.constraints.Length;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * ДТО сущности "Карта к изъятию".
 */
public class TaskWithdrawnCardDTO
	extends BaseDTO {

   @NotNull(message = "\"Задача\" должна быть выбрана.")
   private Long taskId;

   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Номер карты\" не должно превышать " + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String cardNumber;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"ФИО владельца карты\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String violatorFio;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"ФИО нарушителя\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String ownerFio;

   private Long bsoId;

   private Long fileId;
   private Long cardId;
   private Long routeId;
   private Boolean legitimate;

    @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Номер чипа\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
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

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
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
