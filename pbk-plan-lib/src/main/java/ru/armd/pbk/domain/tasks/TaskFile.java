package ru.armd.pbk.domain.tasks;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Домен файла заданий.
 */
public class TaskFile
	extends BaseDomain {

   private String name;
   private Long taskId;
   private Long size;
   private String description;
   private String streamId;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   public Long getSize() {
	  return size;
   }

   public void setSize(Long size) {
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
}
