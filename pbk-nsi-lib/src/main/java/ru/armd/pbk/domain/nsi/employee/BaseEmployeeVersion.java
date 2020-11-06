package ru.armd.pbk.domain.nsi.employee;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Базовый версионный домен сотрудников.
 */
public abstract class BaseEmployeeVersion
	extends BaseVersionDomain {

   private Long employeeId;

   /**
	* Базовый конструктор.
	*/
   public BaseEmployeeVersion() {
	  super();
   }

   /**
	* Конструктор с инициализацией сотрудника.
	*
	* @param employeeId id сотрудника.
	*/
   public BaseEmployeeVersion(Long employeeId) {
	  super();
	  this.employeeId = employeeId;
   }

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("employeeId: ").append(StringUtils.nvl(getEmployeeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
