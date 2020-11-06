package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов остановочных пунктов.
 */
public class StopReportView
	implements IReportView {

   private Long id;
   private String name;
   private String gmStopName;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getGmStopName() {
	  return gmStopName;
   }

   public void setGmStopName(String gmStopName) {
	  this.gmStopName = gmStopName;
   }

   @Override
   public List<Object> getReportList() {
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(name);
	  result.add(gmStopName);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "Название", "Остановочный пункт в ВИС ГИС МГТ");
   }
}
