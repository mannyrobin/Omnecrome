package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов типов ТС маршрутов.
 */
public class RouteTsKindReportView
	implements IReportView {

   private Long id;
   private String cod;
   private String name;
   private String description;
   private String routeTsKind;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getCod() {
	  return cod;
   }

   public void setCod(String cod) {
	  this.cod = cod;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getRouteTsKind() {
	  return routeTsKind;
   }

   public void setRouteTsKind(String routeTsKind) {
	  this.routeTsKind = routeTsKind;
   }

   @Override
   public List<Object> getReportList() {
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(cod);
	  result.add(name);
	  result.add(routeTsKind);
	  result.add(description);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "Код", "Название", "Вид транспорта маршрута в ВИС ГИС МГТ", "Описание");
   }

}
