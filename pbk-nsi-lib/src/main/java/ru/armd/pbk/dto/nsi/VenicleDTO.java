package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IVenicleValidator;

import javax.validation.constraints.NotNull;

/**
 * Дто TC.
 */
@IVenicleValidator
public class VenicleDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"ID ТС в ВИС АСДУ\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ID ТС в ВИС АСДУ\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String asduVenicleId;

   @NotNull(message = "Поле \"Модель ТС\" должно быть заполнено.")
   private Long tsModelId;

   @NotBlank(message = "Поле \"Бортовой номер\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Бортовой номер\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String depoNumber;

   @NotBlank(message = "Поле \"Гос номер\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Гос номер\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String stateNumber;

   @NotBlank(message = "Поле \"ВИН номер\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ВИН номер\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String vinNumber;

   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ID депо в ВИС АСДУ\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String asduDepotId;

   public String getAsduVenicleId() {
	  return asduVenicleId;
   }

   public void setAsduVenicleId(String asduVenicleId) {
	  this.asduVenicleId = asduVenicleId;
   }

   public Long getTsModelId() {
	  return tsModelId;
   }

   public void setTsModelId(Long tsModelId) {
	  this.tsModelId = tsModelId;
   }

   public String getDepoNumber() {
	  return depoNumber;
   }

   public void setDepoNumber(String depoNumber) {
	  this.depoNumber = depoNumber;
   }

   public String getStateNumber() {
	  return stateNumber;
   }

   public void setStateNumber(String stateNumber) {
	  this.stateNumber = stateNumber;
   }

   public String getVinNumber() {
	  return vinNumber;
   }

   public void setVinNumber(String vinNumber) {
	  this.vinNumber = vinNumber;
   }

   public String getAsduDepotId() {
	  return asduDepotId;
   }

   public void setAsduDepotId(String asduDepotId) {
	  this.asduDepotId = asduDepotId;
   }
}
