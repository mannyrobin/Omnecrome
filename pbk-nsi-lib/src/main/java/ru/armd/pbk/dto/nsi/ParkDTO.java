package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IParkValidator;

import javax.validation.constraints.NotNull;

/**
 * DTO парков.
 *
 * @author nikita_chebotaryov
 */
@IParkValidator
public class ParkDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"Короткое название\" должно быть заполнено.")
   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Короткое название\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String shortName;

   @NotBlank(message = "Поле \"Название\" должно быть заполнено.")
   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Название\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String name;

   @NotNull(message = "Поле \"Номер парка \" должно быть заполнено.")
   private Integer parkNumber;

   @NotNull(message = "\"Тип ТС\" должен быть выбран.")
   private Long tsKindId;

   private Long gmParkId;

   private String asduDepotId;

   public String getShortName() {
	  return shortName;
   }

   public void setShortName(String shortName) {
	  this.shortName = shortName;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Integer getParkNumber() {
	  return parkNumber;
   }

   public void setParkNumber(Integer parkNumber) {
	  this.parkNumber = parkNumber;
   }

   public Long getTsKindId() {
	  return tsKindId;
   }

   public void setTsKindId(Long tsKindId) {
	  this.tsKindId = tsKindId;
   }

   public Long getGmParkId() {
	  return gmParkId;
   }

   public void setGmParkId(Long gmParkId) {
	  this.gmParkId = gmParkId;
   }

   public String getAsduDepotId() {
	  return asduDepotId;
   }

   public void setAsduDepotId(String asduDepotId) {
	  this.asduDepotId = asduDepotId;
   }
}
