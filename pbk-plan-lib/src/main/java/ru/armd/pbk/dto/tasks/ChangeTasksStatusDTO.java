package ru.armd.pbk.dto.tasks;

import ru.armd.pbk.core.IHasUpdater;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Дто с информацией для массовой смены статусов заданий.
 */
public class ChangeTasksStatusDTO
	implements IHasUpdater {

   @NotNull(message = "Новый статус должен быть заполнен")
   private Long stateId;

   @NotNull(message = "Список зданий необходим")
   private List<Long> taskIds;

   private Date updateDate;

   private Long updateUserId;

   public Long getStateId() {
	  return stateId;
   }

   public void setStateId(Long stateId) {
	  this.stateId = stateId;
   }

   public List<Long> getTaskIds() {
	  return taskIds;
   }

   public void setTaskIds(List<Long> taskIds) {
	  this.taskIds = taskIds;
   }

   @Override
   public Date getUpdateDate() {
	  return updateDate;
   }

   @Override
   public void setUpdateDate(Date updateDate) {
	  this.updateDate = updateDate;
   }

   @Override
   public Long getUpdateUserId() {
	  return updateUserId;
   }

   @Override
   public void setUpdateUserId(Long updateUserId) {
	  this.updateUserId = updateUserId;
   }
}
