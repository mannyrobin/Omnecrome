package ru.armd.pbk.aspose.core;

import ru.armd.pbk.aspose.aspose.MailMergeDataSet;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractReportProcessor
	implements IReportProcessor {
   protected static final boolean TRACE_PREPROCESS_TEMPLATE = true;
   protected static final boolean TRACE_PROCESS_TEMPLATE = false;

   protected IReportType reportType;
   protected String templateName;
   protected Properties templateData;
   protected MailMergeDataSet dataSet;
   protected IReportDataBean dataBean;
   protected File rootDirectory;
   protected Map<String, ICallback> callbacks;

   protected Map<ReportFormat, String> resultFiles;

   @Override
   public void setTemplateName(String fileName) {
	  this.templateName = fileName;
   }

   @Override
   public String getTemplateName() {
	  return templateName;
   }

   @Override
   public IReportType getReportType() {
	  return reportType;
   }

   @Override
   public void setReportType(IReportType reportType) {
	  this.reportType = reportType;
   }

   @Override
   public void setTemplateData(Properties values) {
	  this.templateData = values;
   }

   @Override
   public IReportDataBean getDataBean() {
	  return dataBean;
   }

   @Override
   public void setDataBean(IReportDataBean dataBean) {
	  this.dataBean = dataBean;
   }

   @Override
   public void setRootFolder(String folder)
	   throws Exception {
	  this.rootDirectory = new File(folder);
	  if (rootDirectory == null || !rootDirectory.isDirectory()) {
		 throw new Exception("Bad root folder");
	  }
   }

   @Override
   public void preprocessTemplate()
	   throws Exception {
	  // does nothing
   }

   @Override
   public void deleteReportFiles() {
	  if (resultFiles == null) {
		 return;
	  }

	  for (String absolutePath : resultFiles.values()) {
		 File file = new File(absolutePath);
		 file.delete();
	  }

	  resultFiles = null;
   }

   public MailMergeDataSet getDataSet() {
	  return dataSet;
   }

   public void setDataSet(MailMergeDataSet dataSet) {
	  this.dataSet = dataSet;
   }

   @Override
   public Map<String, ICallback> getCallbacks() {
	  if (callbacks == null) {
		 callbacks = new HashMap<String, ICallback>();
	  }
	  return callbacks;
   }

   @Override
   public void setCallbacks(Map<String, ICallback> callbacks) {
	  this.callbacks = callbacks;
   }
}
