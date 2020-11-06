package ru.armd.pbk.aspose.core;

import ru.armd.pbk.aspose.aspose.MailMergeDataSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Данные для генерации отчёта.
 */
public class ReportData {

   private Properties scalarValues;
   private MailMergeDataSet dataSet;
   private IReportDataBean dataBean;
   protected Map<String, ICallback> callbacks;

   public ReportData(Properties scalarValues, MailMergeDataSet dataSet, IReportDataBean dataBean) {
	  setScalarValues(scalarValues);
	  setDataSet(dataSet);
	  setDataBean(dataBean);
   }

   public ReportData(Properties scalarValues, MailMergeDataSet dataSet) {
	  this(scalarValues, dataSet, null);
   }

   public ReportData(Properties scalarValues) {
	  this(scalarValues, null, null);
   }

   public ReportData(IReportDataBean dataBean) {
	  this(null, null, dataBean);
   }

   public ReportData(MailMergeDataSet dataSet, IReportDataBean dataBean) {
	  this(null, dataSet, dataBean);
   }

   public void setScalarValues(Properties scalarValues) {
	  this.scalarValues = scalarValues;
   }

   public Properties getScalarValues() {
	  return scalarValues;
   }

   public void setDataSet(MailMergeDataSet dataSet) {
	  this.dataSet = dataSet;
   }

   public MailMergeDataSet getDataSet() {
	  return dataSet;
   }

   public IReportDataBean getDataBean() {
	  return dataBean;
   }

   public void setDataBean(IReportDataBean dataBean) {
	  this.dataBean = dataBean;
   }

   public Map<String, ICallback> getCallbacks() {
	  if (callbacks == null) {
		 callbacks = new HashMap<String, ICallback>();
	  }
	  return callbacks;
   }

   public void setCallbacks(Map<String, ICallback> callbacks) {
	  this.callbacks = callbacks;
   }
}
