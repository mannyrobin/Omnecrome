package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов парков.
 */
public class ParkReportView
	implements IReportView {

   private Long id;
   private String shortName;
   private String name;
   private Integer parkNumber;
   private String tsKind;
   private Long gmParkId;
   private String asduDepotId;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

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

   public String getTsKind() {
	  return tsKind;
   }

   public void setTsKind(String tsKind) {
	  this.tsKind = tsKind;
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

   @Override
   public List<Object> getReportList() {
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(shortName);
	  result.add(name);
	  result.add(parkNumber);
	  result.add(tsKind);
	  result.add(gmParkId);
	  result.add(asduDepotId);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "Короткое название", "Название", "Номер парка", "Вид транспорта", "ГИС МГТ ИД парка", "ID парка в ВИС АСДУ");
   }

}
