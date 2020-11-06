package ru.armd.pbk.domain.nsi.venue;

import ru.armd.pbk.core.domain.BaseVersionDomain;

/**
 * Связка места встречи с районом.
 */
public class VenueDistrict
	extends BaseVersionDomain {

   private Long venueId;
   private Long districtId;

   public Long getVenueId() {
	  return venueId;
   }

   public void setVenueId(Long venueId) {
	  this.venueId = venueId;
   }

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

}
