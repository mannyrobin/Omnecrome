package ru.armd.pbk.dto.viss;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * DTO конфигурации взаимодействия с ВИС.
 */
public class VisExchangeStartDTO {

   @NotNull(message = "\"id\" не задан")
   private Long id;

   @NotNull(message = "\"Дата начала\" должена быть выбрана.")
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date start;

   @NotNull(message = "\"Дата окончания\" должена быть выбрана.")
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date end;

   @Length(max = StringUtils.MAX_INPUT_SIZE, message = "Число символов в поле \"Параметр\" не должно превышать " + StringUtils.MAX_INPUT_SIZE + " символов.")
   private String parameter;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getStart() {
	  return start;
   }

   public void setStart(Date start) {
	  this.start = start;
   }

   public Date getEnd() {
	  return end;
   }

   public void setEnd(Date end) {
	  this.end = end;
   }

   public String getParameter() {
	  return parameter;
   }

   public void setParameter(String parameter) {
	  this.parameter = parameter;
   }
}
