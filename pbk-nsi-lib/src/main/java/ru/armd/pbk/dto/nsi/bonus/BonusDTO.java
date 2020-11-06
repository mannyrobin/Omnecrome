package ru.armd.pbk.dto.nsi.bonus;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.domain.nsi.bonus.BonusPeriod;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;
import ru.armd.pbk.utils.validation.IBonusValidator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ДТО для сущности "Премирование".
 */
@IBonusValidator
public class BonusDTO
	extends BaseDTO {

   @NotNull(message = "Поле \"Количество карт\" должно быть заполнено.")
   private Long count;

   @NotNull(message = "Поле \"Дата начала периуда премирования\" должно быть заполнено.")
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date periodStartDate;

   @NotNull(message = "Поле \"Дата конца периуда премирования\" должно быть заполнено.")
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date periodEndDate;

   @NotNull(message = "\"Билет\" должен быть выбран.")
   @Size(min = 1, message = "\"Билет\" должен быть выбран.")
   private List<Long> tickets = new ArrayList<Long>();

   @NotBlank(message = "Поле \"Название периода премирования\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Название периода премирования\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;

   private List<BonusPeriod> periods;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Long getCount() {
	  return count;
   }

   public void setCount(Long count) {
	  this.count = count;
   }

   public Date getPeriodStartDate() {
	  return periodStartDate;
   }

   public void setPeriodStartDate(Date periodStartDate) {
	  this.periodStartDate = periodStartDate;
   }

   public Date getPeriodEndDate() {
	  return periodEndDate;
   }

   public void setPeriodEndDate(Date periodEndDate) {
	  this.periodEndDate = periodEndDate;
   }

   public List<Long> getTickets() {
	  return tickets;
   }

   public void setTickets(List<Long> tickets) {
	  this.tickets = tickets;
   }

   public List<BonusPeriod> getPeriods() {
	  return periods;
   }

   public void setPeriods(List<BonusPeriod> periods) {
	  this.periods = periods;
   }
}
