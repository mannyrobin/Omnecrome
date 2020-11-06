package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов округов.
 */
public class CountyReportView
	implements IReportView {


   private Long id;
   private String cod;
   private String name;
   private String description;

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

   @Override
   public List<Object> getReportList() {
	  List<Object> res = new ArrayList<>();
	  res.add(id);
	  res.add(cod);
	  res.add(name);
	  res.add(description);
	  return res;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "Код", "Название", "Описание");
   }

}
