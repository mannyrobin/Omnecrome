package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов маршрутов.
 */
public class RouteReportView
	implements IReportView {

   private Long id;

   private String routeNumber;
   private String asduRouteId;
   private String askpRouteCode;
   private String asmppRouteCode;
   private String routeTsKind;
   private String vsGsmgtNumber;
   private String district;
   private String cities;

   public String getVsGsmgtNumber() {
	  return vsGsmgtNumber;
   }

   public void setVsGsmgtNumber(String vsGsmgtNumber) {
	  this.vsGsmgtNumber = vsGsmgtNumber;
   }


   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public String getAsduRouteId() {
	  return asduRouteId;
   }

   public void setAsduRouteId(String asduRouteId) {
	  this.asduRouteId = asduRouteId;
   }

   public String getAskpRouteCode() {
	  return askpRouteCode;
   }

   public void setAskpRouteCode(String askpRouteCode) {
	  this.askpRouteCode = askpRouteCode;
   }

   public String getAsmppRouteCode() {
	  return asmppRouteCode;
   }

   public void setAsmppRouteCode(String asmppRouteCode) {
	  this.asmppRouteCode = asmppRouteCode;
   }

   public String getRouteTsKind() {
	  return routeTsKind;
   }

   public void setRouteTsKind(String routeTsKind) {
	  this.routeTsKind = routeTsKind;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    @Override
   public List<Object> getReportList() {
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(routeNumber);
	  result.add(asduRouteId);
	  result.add(askpRouteCode);
	  result.add(asmppRouteCode);
	  result.add(vsGsmgtNumber);
	  result.add(routeTsKind);
	  result.add(cities);
	  result.add(district);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "Номер маршрута", "ID маршрута в ВИС ASDU", "Код маршрута в ВИС ASKP", "Код маршрута в ВИС ASMPP", "Номер маршрута в ВИС ГИС МГТ", "Вид транспорта", "Округ", "Район");
   }

}
