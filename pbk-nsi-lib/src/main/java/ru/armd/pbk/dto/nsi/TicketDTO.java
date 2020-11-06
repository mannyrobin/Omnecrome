package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.ITicketValidator;

import javax.validation.constraints.NotNull;

/**
 * DTO билета.
 *
 * @author nikita_lobanov
 */
@ITicketValidator
public class TicketDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"Код\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String code;
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Название\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;
   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;
   @NotNull(message = "\"Тип билета\" должен быть выбран.")
   private Integer ticketTypeId;
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код приложения\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String applicationCode;

   @NotNull(message = "Признак использования в анализе ПП должен быть выбран.")
   private Boolean useInAnalysis;

   public String getCode() {
	  return code;
   }

   public void setCode(String code) {
	  this.code = code;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Integer getTicketTypeId() {
	  return ticketTypeId;
   }

   public void setTicketTypeId(Integer ticketTypeId) {
	  this.ticketTypeId = ticketTypeId;
   }

   public String getApplicationCode() {
	  return applicationCode;
   }

   public void setApplicationCode(String applicationCode) {
	  this.applicationCode = applicationCode;
   }

   public Boolean getUseInAnalysis() {
	  return useInAnalysis;
   }

   public void setUseInAnalysis(Boolean useInAnalysis) {
	  this.useInAnalysis = useInAnalysis;
   }
}
