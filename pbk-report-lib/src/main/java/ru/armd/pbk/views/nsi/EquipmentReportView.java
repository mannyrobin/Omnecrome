package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * View для отчетов по бортовому оборуованию.
 */
public class EquipmentReportView
	implements IReportView {

   private Long id;
   private String asduEquipmentId;
   private String asduVenicleId;
   private String brand;
   private String model;
   private String firmwareVersion;
   private String cellNumber;
   private String tsView;

   @Override
   public List<Object> getReportList() {
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(brand);
	  result.add(model);
	  result.add(firmwareVersion);
	  result.add(cellNumber);
	  result.add(tsView);
	  result.add(asduEquipmentId);
	  result.add(asduVenicleId);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "Марка", "Модель", "Версия прошивки", "Номер абонента", "ТС", "ID в ВИС АСДУ", "ID ТС в ВИС АСДУ");
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

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
   }

   public String getTsView() {
	  return tsView;
   }

   public void setTsView(String tsView) {
	  this.tsView = tsView;
   }

}
