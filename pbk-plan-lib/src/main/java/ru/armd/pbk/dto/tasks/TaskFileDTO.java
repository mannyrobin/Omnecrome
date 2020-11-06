package ru.armd.pbk.dto.tasks;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;

/**
 * DTO для файла задания.
 */
public class TaskFileDTO
	extends BaseDTO {

   private Long id;
   private Long taskId;
   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;
   //нужны для конвертации в домен
   private String name;
   private Long size;
   private String streamId;
   //файл
   private MultipartFile file;

   private Integer isScan;

   public String getStreamId() {
	  return streamId;
   }

   public void setStreamId(String streamId) {
	  this.streamId = streamId;
   }

   public Long getSize() {
	  return size;
   }

   public void setSize(Long size) {
	  this.size = size;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public MultipartFile getFile() {
	  return file;
   }

   public void setFile(MultipartFile file) {
	  this.file = file;
   }

   public Integer getIsScan() {
	  return isScan;
   }

   public void setIsScan(Integer isScan) {
	  this.isScan = isScan;
   }

}
