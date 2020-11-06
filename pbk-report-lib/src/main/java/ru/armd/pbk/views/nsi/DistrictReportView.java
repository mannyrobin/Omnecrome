package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов районов.
 */
public class DistrictReportView
	implements IReportView {

   private Long id;

   private String cod;

   private String name;

   private String countyName;

   private Long countRoute;

   private Long countVenues;

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

   public String getCountyName() {
	  return countyName;
   }

   public void setCountyName(String countyName) {
	  this.countyName = countyName;
   }

   public Long getCountRoute() {
	  return countRoute;
   }

   public void setCountRoute(Long countRoute) {
	  this.countRoute = countRoute;
   }

   public Long getCountVenues() {
	  return countVenues;
   }

   public void setCountVenues(Long countVenues) {
	  this.countVenues = countVenues;
   }


   @Override
   public List<Object> getReportList() {
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(cod);
	  result.add(name);
	  result.add(countyName);
	  result.add(countRoute);
	  result.add(countVenues);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "Код", "Название", "Округ", "Кол-во маршрутов", "Кол-во мест встреч");
   }

}
