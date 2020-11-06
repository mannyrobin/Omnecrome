package ru.armd.pbk.views.tasks;

import ru.armd.pbk.core.views.BaseGridView;

import java.math.BigDecimal;

/**
 * View для файлов заданий.
 */
public class TaskFileView
	extends BaseGridView {

   private Long id;

   private String name;
   private Long taskID;
   private BigDecimal size;
   private String description;
   private String streamId;
   private String url;
   private String duration;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Long getTaskID() {
	  return taskID;
   }

   public void setTaskID(Long taskID) {
	  this.taskID = taskID;
   }

   public BigDecimal getSize() {
	  return size;
   }

   public void setSize(BigDecimal size) {
	  this.size = size;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getStreamId() {
	  return streamId;
   }

   public void setStreamId(String streamId) {
	  this.streamId = streamId;
   }

   public String getUrl() {
	  return url;
   }

   public void setUrl(String url) {
	  this.url = url;
   }

   public String getDuration() {
	  return duration;
   }

   public void setDuration(String duration) {
	  this.duration = duration;
   }

}
