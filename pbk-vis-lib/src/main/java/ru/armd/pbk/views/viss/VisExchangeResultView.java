package ru.armd.pbk.views.viss;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateTimeSerializer;

import java.util.Date;

/**
 * View записи журнала взаимодействия с ВИС.
 */
public class VisExchangeResultView
	extends BaseGridView {

   private Long id;
   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date resultDate;
   private String exchangeState;
   private String comment;
   private String fileName;
   private String fileStreamId;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getResultDate() {
	  return resultDate;
   }

   public void setResultDate(Date resultDate) {
	  this.resultDate = resultDate;
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

   public String getFileName() {
	  return fileName;
   }

   public void setFileName(String fileName) {
	  this.fileName = fileName;
   }

   public String getFileStreamId() {
	  return fileStreamId;
   }

   public void setFileStreamId(String fileStreamId) {
	  this.fileStreamId = fileStreamId;
   }
}
