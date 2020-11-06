package ru.armd.pbk.dto.nsi.district;

import javax.validation.constraints.NotNull;

/**
 * ДТО мест встреч района.
 */
public class DistrictVenueDTO
	extends BaseDistrictVersionDTO {

   @NotNull(message = "\"Место встречи\" должно быть выбрано.")
   private Long venueId;

   public Long getVenueId() {
	  return venueId;
   }

   public void setVenueId(Long venueId) {
	  this.venueId = venueId;
   }

}
