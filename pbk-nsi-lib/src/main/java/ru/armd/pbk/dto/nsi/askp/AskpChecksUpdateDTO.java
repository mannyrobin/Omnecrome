package ru.armd.pbk.dto.nsi.askp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ДТО обновлений проверок АСКП.
 */
public class AskpChecksUpdateDTO {
   @NotNull(message = "Поле \"Дата начала\" должно быть заполнено.")
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date startDate;

   @NotNull(message = "Поле \"Дата окончания\" должно быть заполнено.")
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date endDate;

   /**
	* Конструктор по умолчанию.
	*/
   public AskpChecksUpdateDTO() {
   }

   /**
	* Конструктор с параметрами.
	*
	* @param startDate начало периода.
	* @param endDate   окончание периода.
	*/
   public AskpChecksUpdateDTO(Date startDate, Date endDate) {
	  this.startDate = startDate;
	  this.endDate = endDate;
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
}