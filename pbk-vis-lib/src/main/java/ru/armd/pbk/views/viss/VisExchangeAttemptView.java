package ru.armd.pbk.views.viss;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateTimeSerializer;

import java.util.Date;

/**
 * View записи журнала взаимодействия с ВИС.
 */
public class VisExchangeAttemptView
	extends BaseGridView {

   private Long id;
   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date attemptDate;
   private String exchangeState;
   private String comment;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getAttemptDate() {
	  return attemptDate;
   }

   public void setAttemptDate(Date attemptDate) {
	  this.attemptDate = attemptDate;
   }

   public String getExchangeState() {
	  return exchangeState;
   }

   public void setExchangeState(String exchangeState) {
	  this.exchangeState = exchangeState;
   }

   public String getComment() {
	  return comment;
   }

   public void setComment(String comment) {
	  this.comment = comment;
   }
}
