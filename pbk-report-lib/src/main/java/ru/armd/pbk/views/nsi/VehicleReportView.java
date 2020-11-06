package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов ТС.
 */
public class VehicleReportView
	implements IReportView {

   private Long id;
   private String tsTypeName;
   private String make;
   private String model;
   private String asduVenicleId;
   private String depoNumber;
   private String stateNumber;
   private String vinNumber;
   private String tsCapacities;


   public String getTsCapacities() {
	  return tsCapacities;
   }

   public void setTsCapacities(String tsCapacities) {
	  this.tsCapacities = tsCapacities;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getTsTypeName() {
	  return tsTypeName;
   }

   public void setTsTypeName(String tsTypeName) {
	  this.tsTypeName = tsTypeName;
   }

   public String getMake() {
	  return make;
   }

   public void setMake(String make) {
	  this.make = make;
   }

   public String getModel() {
	  return model;
   }

   public void setModel(String model) {
	  this.model = model;
   }

   public String getAsduVenicleId() {
	  return asduVenicleId;
   }

   public void setAsduVenicleId(String asduVenicleId) {
	  this.asduVenicleId = asduVenicleId;
   }

   public String getDepoNumber() {
	  return depoNumber;
   }

   public void setDepoNumber(String depoNumber) {
	  this.depoNumber = depoNumber;
   }

   public String getStateNumber() {
	  return stateNumber;
   }

   public void setStateNumber(String stateNumber) {
	  this.stateNumber = stateNumber;
   }

   public String getVinNumber() {
	  return vinNumber;
   }

   public void setVinNumber(String vinNumber) {
	  this.vinNumber = vinNumber;
   }

   @Override
   public List<Object> getReportList() {
	  List<Object> res = new ArrayList<>();
	  res.add(id);
	  res.add(make + ' ' + model);
	  res.add(tsTypeName + '(' + tsCapacities + ')');
	  res.add(depoNumber);
	  res.add(stateNumber);
	  res.add(vinNumber);
	  res.add(asduVenicleId);
	  return res;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "Марка Модель", "Тип ТС", "Бортовой номер", "Гос. номер", "ВИН номер", "ID ТС в ВИС АСДУ");
   }

}
