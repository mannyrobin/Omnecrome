package ru.armd.pbk.dto.nsi;

import ru.armd.pbk.core.dto.BaseVersionDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * ДТО водителя парка.
 */
public class ParkDriverDTO
	extends BaseVersionDTO {

   @NotNull(message = "\"Парк\" должен быть выбран.")
   private Long parkId;

   private Long tsDriverId;

   @NotNull(message = "\"Водитель\" должен быть выбран.")
   @Size(min = 1, message = "\"Водитель\" должен быть выбран.")
   private List<Long> tsDriverIds = new ArrayList<Long>();

   public Long getParkId() {
	  return parkId;
   }

   public void setParkId(Long parkId) {
	  this.parkId = parkId;
   }

   public Long getTsDriverId() {
	  return tsDriverId;
   }

   public void setTsDriverId(Long tsDriverId) {
	  this.tsDriverId = tsDriverId;
   }

   public List<Long> getTsDriverIds() {
	  return tsDriverIds;
   }

   public void setTsDriverIds(List<Long> tsDriverIds) {
	  this.tsDriverIds = tsDriverIds;
   }
}
