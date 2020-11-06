package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IEquipmentValidator;

import javax.validation.constraints.NotNull;

/**
 * Дто бортового оборудования.
 */
@IEquipmentValidator
public class EquipmentDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"ID в ВИС АСДУ\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ID в ВИС АСДУ\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String asduEquipmentId;

   @NotBlank(message = "Поле \"ID ТС в ВИС АСДУ\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ID ТС в ВИС АСДУ\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String asduVenicleId;

	/*@NotBlank(message = "Поле \"Марка\" должно быть заполнено.")
	@Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Марка\" не должно превышать "
			+ StringUtils.MIDDLE_INPUT_SIZE + " символов.")
	private String brand;
	
	@NotBlank(message = "Поле \"Модель\" должно быть заполнено.")
	@Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Модель\" не должно превышать "
			+ StringUtils.MIDDLE_INPUT_SIZE + " символов.")
	private String model;
	
	@NotBlank(message = "Поле \"Версия прошивки\" должно быть заполнено.")
	@Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Версия прошивки\" не должно превышать "
			+ StringUtils.X_SHORT_INPUT_SIZE + " символов.")
	private String firmwareVersion;
	
	@NotBlank(message = "Поле \"Номер абонента\" должно быть заполнено.")
	@Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Номер абонента\" не должно превышать "
			+ StringUtils.X_SHORT_INPUT_SIZE + " символов.")
	private String cellNumber;*/

   @NotNull(message = "\"ТС\" должно быть выбрано.")
   private Long venicleId;

   public String getAsduEquipmentId() {
	  return asduEquipmentId;
   }

   public void setAsduEquipmentId(String asduEquipmentId) {
	  this.asduEquipmentId = asduEquipmentId;
   }

   public String getAsduVenicleId() {
	  return asduVenicleId;
   }

   public void setAsduVenicleId(String asduVenicleId) {
	  this.asduVenicleId = asduVenicleId;
   }

	/*
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}*/

   public Long getVenicleId() {
	  return venicleId;
   }

   public void setVenicleId(Long venicleId) {
	  this.venicleId = venicleId;
   }
}
