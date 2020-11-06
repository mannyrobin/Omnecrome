package ru.armd.pbk.dto.nsi.district;

import ru.armd.pbk.core.dto.BaseVersionDTO;

import javax.validation.constraints.NotNull;

/**
 * Базовый домен для работы с версионом объектом района.
 */
public class BaseDistrictVersionDTO
	extends BaseVersionDTO {

   @NotNull(message = "\"Район\" должен быть выбран.")
   private Long districtId;

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

}
