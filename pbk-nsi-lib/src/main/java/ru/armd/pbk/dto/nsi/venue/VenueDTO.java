package ru.armd.pbk.dto.nsi.venue;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;

import java.util.List;

/**
 * ДТО мест втреч.
 */
public class VenueDTO
	extends BaseVersionDTO
	implements IHasName, IHasCod {

   private String latitude;
   private String longitude;

   @NotBlank(message = "Поле \"Название\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Название\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;

   private String cod;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать " + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   //	@NotNull(message = "Поле \"Округ\" должно быть заполнено.")
   private Long countyId;

   private Long districtId;

   private Boolean shiftI;
   private Boolean shiftII;
   private Boolean shiftIII;

    private Boolean shiftDay;
   private Boolean shiftNight;

   //@NotNull(message = "Поле \"Подразделение\" должно быть заполнено.")
   private Long deptId;

   private Boolean tpu;

   private List<Long> deptIds;

   @Override
   public String getCod() {
	  return cod;
   }

   @Override
   public void setCod(String cod) {
	  this.cod = cod;
   }

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public void setName(String name) {
	  this.name = name;
   }

   public String getLongitude() {
	  return longitude;
   }

   public void setLongitude(String longitude) {
	  this.longitude = longitude;
   }

   public String getLatitude() {
	  return latitude;
   }

   public void setLatitude(String latitude) {
	  this.latitude = latitude;
   }

   public Boolean getShiftI() {
	  return shiftI;
   }

   public void setShiftI(Boolean shiftI) {
	  this.shiftI = shiftI;
   }

   public Boolean getShiftII() {
	  return shiftII;
   }

   public void setShiftII(Boolean shiftII) {
	  this.shiftII = shiftII;
   }

    public Boolean getShiftIII() {
        return shiftIII;
    }

    public void setShiftIII(Boolean shiftIII) {
        this.shiftIII = shiftIII;
    }

    public Boolean getShiftDay() {
	  return shiftDay;
   }

   public void setShiftDay(Boolean shiftDay) {
	  this.shiftDay = shiftDay;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getCountyId() {
	  return countyId;
   }

   public void setCountyId(Long countyId) {
	  this.countyId = countyId;
   }

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public Boolean getTpu() {
	  return tpu;
   }

   public void setTpu(Boolean tpu) {
	  this.tpu = tpu;
   }

   public void setShiftNight(Boolean shiftNight) {
	  this.shiftNight = shiftNight;
   }

   public Boolean getShiftNight() {
	  return shiftNight;
   }

   public List<Long> getDeptIds() {
	  return deptIds;
   }

   public void setDeptIds(List<Long> deptIds) {
	  this.deptIds = deptIds;
   }
}