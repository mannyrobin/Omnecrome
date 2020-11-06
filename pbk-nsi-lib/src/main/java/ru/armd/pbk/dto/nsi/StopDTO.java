package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * ДТО остановочных пунктов.
 */
public class StopDTO
	extends BaseVersionDTO
	implements IHasName {

   @NotBlank(message = "Поле \"Название\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Название\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;

   @NotNull(message = "\"ID Остановочного пункта в ВИС ГИС МГТ\" должен быть выбран.")
   private Long gmStopId;
   private String latitude;
   private String longitude;
   private String routeNames;
   private String nameWithoutDistrict;

   public String getLatitude() {
	  return latitude;
   }

   public void setLatitude(String latitude) {
	  this.latitude = latitude;
   }

   public String getLongitude() {
	  return longitude;
   }

   public void setLongitude(String longitude) {
	  this.longitude = longitude;
   }

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public void setName(String name) {
	  this.name = name;
   }

   public Long getGmStopId() {
	  return gmStopId;
   }

   public void setGmStopId(Long gmStopId) {
	  this.gmStopId = gmStopId;
   }

   public String getRouteNames() {
	  return routeNames;
   }

   public void setRouteNames(String routeNames) {
	  this.routeNames = routeNames;
   }

   public String getNameWithoutDistrict() {
	  return nameWithoutDistrict;
   }

   public void setNameWithoutDistrict(String nameWithoutDistrict) {
	  this.nameWithoutDistrict = nameWithoutDistrict;
   }

}
