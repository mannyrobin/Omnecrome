package ru.armd.pbk.domain.nsi.venue;

import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.domain.BaseVersionDomain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Домен мест встреч.
 */
public class Venue
	extends BaseVersionDomain
	implements IHasName, IHasCod {

   private String wktGeom;
   private String name;
   private String cod;
   private String description;
   private Long districtId;

   private int shiftI = 0;
   private int shiftII = 0;
    private int shiftIII = 0;
   private int shiftDay = 0;
   private int shiftNight = 0;

   private Long deptId;
   private int tpu = 0;

   private List<Long> deptIds;

   private String countyIds;

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

   public String getWktGeom() {
	  return wktGeom;
   }

   public void setWktGeom(String wktGeom) {
	  this.wktGeom = wktGeom;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

   public int isShiftI() {
	  return shiftI;
   }

   public void setShiftI(int shiftI) {
	  this.shiftI = shiftI;
   }

   public int isShiftII() {
	  return shiftII;
   }

   public void setShiftII(int shiftII) {
	  this.shiftII = shiftII;
   }

    public int isShiftIII() {
        return shiftIII;
    }

    public void setShiftIII(int shiftIII) {
        this.shiftIII = shiftIII;
    }

    public int isShiftDay() {
	  return shiftDay;
   }

   public void setShiftDay(int shiftDay) {
	  this.shiftDay = shiftDay;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public int getTpu() {
	  return tpu;
   }

   public void setTpu(int tpu) {
	  this.tpu = tpu;
   }

   public void setShiftNight(int shiftNight) {
	  this.shiftNight = shiftNight;
   }

   public int isShiftNight() {
	  return shiftNight;
   }

   public List<Long> getDeptIds() {
	  return deptIds;
   }

   public void setDeptIds(List<Long> deptIds) {
	  this.deptIds = deptIds;
   }

   public String getCountyIds() {
	  return countyIds;
   }

   public void setCountyIds(String countyIds) {
	  this.countyIds = countyIds;
   }

   /**
	* Получить список округов, через которые проходит данное место встречи.
	* Округа места встречи определяются через районы, к которым оно привязано.
	*
	* @return список округов.
	*/
   public List<String> getCountyList() {
	  if (countyIds == null) {
		 return new ArrayList<>();
	  }
	  return Arrays.asList(countyIds.split(","));
   }
}