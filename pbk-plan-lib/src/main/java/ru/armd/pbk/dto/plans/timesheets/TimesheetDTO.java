package ru.armd.pbk.dto.plans.timesheets;

import ru.armd.pbk.core.dto.BaseDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * ДТО табеля сотрудника на день.
 */
public class TimesheetDTO
	extends BaseDTO {

   @NotNull(message = "Фактическое количество часов должно быть введено.")
   @Min(value = 0, message = "Фактическое количество часов не должно быть отрицательным.")
   @Max(value = 24, message = "Фактическое количество часов не должно превышать 24 часа.")
   private BigDecimal factHours;

   public BigDecimal getFactHours() {
	  return factHours;
   }

   public void setFactHours(BigDecimal factHours) {
	  this.factHours = factHours;
   }

}