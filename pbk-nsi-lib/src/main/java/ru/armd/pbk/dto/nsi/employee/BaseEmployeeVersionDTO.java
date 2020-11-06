package ru.armd.pbk.dto.nsi.employee;

import ru.armd.pbk.core.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * Базовый версионный дто сотрудников.
 */
public abstract class BaseEmployeeVersionDTO
	extends BaseDTO {

   @NotNull(message = "\"Сотрудник\" должен быть выбран.")
   private Long employeeId;

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }
}
