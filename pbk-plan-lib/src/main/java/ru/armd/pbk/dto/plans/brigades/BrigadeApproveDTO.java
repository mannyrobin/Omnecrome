package ru.armd.pbk.dto.plans.brigades;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class BrigadeApproveDTO {
   @NotNull(message = "Поле \"Дата начала\" должно быть заполнено.")
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date startDate;

   @NotNull(message = "Поле \"Дата окончания\" должно быть заполнено.")
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date endDate;

   private Long deptId;

   private boolean resetManualData = false;

   public BrigadeApproveDTO() {
   }

   public BrigadeApproveDTO(Long deptId, Date startDate, Date endDate) {
	  this.startDate = startDate;
	  this.endDate = endDate;
	  this.deptId = deptId;
   }

   public Date getStartDate() {
	  return startDate;
   }

   public void setStartDate(Date startDate) {
	  this.startDate = startDate;
   }

   public Date getEndDate() {
	  return endDate;
   }

   public void setEndDate(Date endDate) {
	  this.endDate = endDate;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public boolean isResetManualData() {
	  return resetManualData;
   }

   public void setResetManualData(boolean resetManualData) {
	  this.resetManualData = resetManualData;
   }
}