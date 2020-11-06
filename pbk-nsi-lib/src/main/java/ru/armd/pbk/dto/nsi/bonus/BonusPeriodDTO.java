package ru.armd.pbk.dto.nsi.bonus;

import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.validation.IBonusPeriodValidator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * ДТО коэфициента премирования.
 */
@IBonusPeriodValidator
public class BonusPeriodDTO
	extends BaseDTO {

   @NotNull(message = "Поле \"Премирование\" должно быть заполнено")
   private Long bonusId;
   @NotNull(message = "Поле \"От\" должно быть заполнено")
   @Min(value = 0, message = "Значение поля \"От\" не должно быть отрицательным")
   private int countFrom;

   @Min(value = 0, message = "Значение поля \"До\" не должно быть отрицательным")
   private Integer countTo;

   @NotNull(message = "Поле \"Процент\" должно быть заполнено")
   @Min(value = 0, message = "Значение поля \"Процент\" не должно быть отрицательным")
   private int percent;

   public int getCountFrom() {
	  return countFrom;
   }

   public void setCountFrom(int countFrom) {
	  this.countFrom = countFrom;
   }

   public Integer getCountTo() {
	  return countTo;
   }

   public void setCountTo(Integer countTo) {
	  this.countTo = countTo;
   }

   public int getPercent() {
	  return percent;
   }

   public void setPercent(int percent) {
	  this.percent = percent;
   }

   public Long getBonusId() {
	  return bonusId;
   }

   public void setBonusId(Long bonusId) {
	  this.bonusId = bonusId;
   }
}
