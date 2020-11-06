package ru.armd.pbk.dto.nsi.askppuskcheck;

import org.hibernate.validator.constraints.Length;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * DTO для данных от АСКП.
 */
public class AskpPuskCheckDTO
	extends BaseDTO {

   @NotNull(message = "Поле \"Билет\" должно быть заполнено.")
   private Long ticketId;

   @NotNull(message = "Поле \"Маршрут\" должно быть заполнено.")
   private Long routeId;

   @NotNull(message = "Поле \"Выход ТС\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Выход ТС\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String moveCode;

   @NotNull(message = "Поле \"Время начала проверки\" должно быть заполнено.")
   private Date checkStartTime;

   @NotNull(message = "Поле \"Время конца проверки\" должно быть заполнено.")
   private Date checkEndTime;

   @NotNull(message = "Поле \"Кол-во с результатом \"1\"(нет проходов)\" должно быть заполнено.")
   private Integer checkResult1Count;

   @NotNull(message = "Поле \"Кол-во с результатом \"2\"(один проход)\" должно быть заполнено.")
   private Integer checkResult2Count;

   @NotNull(message = "Поле \"Кол-во с результатом \"3\"( >1 прохода)\" должно быть заполнено.")
   private Integer checkResult3Count;

   public Long getTicketId() {
	  return ticketId;
   }

   public void setTicketId(Long ticketId) {
	  this.ticketId = ticketId;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public Date getCheckStartTime() {
	  return checkStartTime;
   }

   public void setCheckStartTime(Date checkStartTime) {
	  this.checkStartTime = checkStartTime;
   }

   public Date getCheckEndTime() {
	  return checkEndTime;
   }

   public void setCheckEndTime(Date checkEndTime) {
	  this.checkEndTime = checkEndTime;
   }

   public Integer getCheckResult1Count() {
	  return checkResult1Count;
   }

   public void setCheckResult1Count(Integer checkResult1Count) {
	  this.checkResult1Count = checkResult1Count;
   }

   public Integer getCheckResult2Count() {
	  return checkResult2Count;
   }

   public void setCheckResult2Count(Integer checkResult2Count) {
	  this.checkResult2Count = checkResult2Count;
   }

   public Integer getCheckResult3Count() {
	  return checkResult3Count;
   }

   public void setCheckResult3Count(Integer checkResult3Count) {
	  this.checkResult3Count = checkResult3Count;
   }
}
