package ru.armd.pbk.views.nsi.askp;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Представление отчета АСКП по часам.
 */
public class AskpByHourReportView
	implements IReportView {

   private Map<String, String> extcols;

   private List<String> headers;

   @Override
   public List<Object> getReportList() {
	  List<Object> result = new ArrayList<Object>();
	  for (String header : headers) {
		 result.add(extcols.containsKey(header) ? extcols.get(header) : "");
	  }
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return headers;
   }

   public void setHeaders(List<String> headers) {
	  this.headers = headers;
   }


   public Map<String, String> getExtcols() {
	  return extcols;
   }

   public void setExtcols(Map<String, String> extcols) {
	  this.extcols = extcols;
   }

   public List<String> getHeaders() {
	  return headers;
   }

}
