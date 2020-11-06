package ru.armd.pbk.views.tasks;

import ru.armd.pbk.core.views.IReportView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Представление изъятых карт.
 */
public class TaskWithdrawCardReportView
	implements IReportView {

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
   private String ticketName;
   private String chipNum;

   public Date getTaskDate() {
	  return taskDate;
   }

   public void setTaskDate(Date taskDate) {
	  this.taskDate = taskDate;
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

   public String getEmployeeName() {
	  return employeeName;
   }

   public void setEmployeeName(String employeeName) {
	  this.employeeName = employeeName;
   }

   public String getTicketName() {
	  return ticketName;
   }

   public void setTicketName(String ticketName) {
	  this.ticketName = ticketName;
   }

    public String getChipNum() {
        return chipNum;
    }

    public void setChipNum(String chipNum) {
        this.chipNum = chipNum;
    }

    @Override
   public List<Object> getReportList() {
	  List<Object> res = new ArrayList<>();
	  res.add(id);
	  res.add(new SimpleDateFormat("dd.MM.yyyy").format(taskDate));
	  res.add(cardNumber);
	  res.add(actNumber);
	  res.add(routeNumber);
	  res.add(ownerFio);
	  res.add(violatorFio);
	  res.add(employeeName);
	  res.add(ticketName);
	  res.add(chipNum);
	  return res;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("ID", "Дата изъятия", "Номер карты", "Номер акта об изъятии", "Номер маршрута", "Фио владельца", "Фио нарушителя", "Фио контролера", "Тип карты", "Номер чипа");
   }

}
