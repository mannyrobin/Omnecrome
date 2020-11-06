package ru.armd.pbk.aspose.core;

import org.apache.log4j.Logger;
import org.reflections.Reflections;
import ru.armd.pbk.exceptions.PBKAsposeReportException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractReportType
	implements IReportType {

   public static Logger LOGGER = Logger.getLogger(AbstractReportType.class);

   protected static final String FILE_NAME_PATTERN = "%s_%s%s";
   protected static final String ZIP_FILE_EXT = ".zip";

   protected static final Map<String, IReportType> reportNameMap = new HashMap<>();

   static {
	  // TODO: проблема при автоматическом передеплое, т.к. при удалении war не все либы высвобождаются
	  // TODO: Возможо все же переделать под ServiceLoader????
	  try {
		 Reflections reflections = new Reflections("ru.armd.pbk.aspose");
		 Set<Class<? extends IReportType>> classes = reflections.getSubTypesOf(IReportType.class);
		 for (@SuppressWarnings("rawtypes") Class reportTypeClass : classes) {
			IReportType reportType;
			try {
			   reportType = (IReportType) reportTypeClass.newInstance();
			   reportNameMap.put(reportType.getName(), reportType);
			} catch (InstantiationException e) {
			   LOGGER.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
			   LOGGER.error(e.getMessage(), e);
			}
		 }
	  } catch (Throwable t) {
		 LOGGER.error(t.getMessage(), t);
	  }
   }

   private String name;
   private String description;
   private String templateName;
   private String fileNamePattern;
   protected ReportFormat reportFormat;
   protected ReportFormat defaultReportFormat;
   private Boolean skipTemplate = false;

   public AbstractReportType(String name, String description, String templateName, String fileNamePattern, ReportFormat reportFormat, ReportFormat defaultReportFormat) {
	  this.name = name;
	  this.description = description;
	  this.templateName = templateName;
	  this.fileNamePattern = fileNamePattern;
	  this.reportFormat = reportFormat;
	  this.defaultReportFormat = defaultReportFormat;
   }

   public AbstractReportType(String name, String description, String templateName, String fileNamePattern, ReportFormat reportFormat, ReportFormat defaultReportFormat, Boolean skipTemplate) {
	  this(name, description, templateName, fileNamePattern, reportFormat, defaultReportFormat);
	  this.skipTemplate = skipTemplate;
   }


   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  throw new PBKAsposeReportException("Report processor not found");
   }

   @Override
   public IReportType getReportTypeByName(String name) {
	  return reportNameMap.get(name);
   }

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public String getDescription() {
	  return description;
   }

   @Override
   public String getTemplateName() {
	  return templateName;
   }

   @Override
   public ReportFormat getReportFormat() {
	  return reportFormat;
   }

   @Override
   public void setReportFormat(ReportFormat format) {
	  this.reportFormat = format;
   }

   @Override
   public ReportFormat getDefaultReportFormat() {
	  return defaultReportFormat;
   }

   @Override
   public String getFileNamePattern() {
	  return fileNamePattern;
   }

   @Override
   public String getFileName(Long id) {
	  return getFileName(id, getReportFormat());
   }

   @Override
   public String getFileName(Long id, ReportFormat format) {
	  if (format == null) {
		 return null;
	  }

	  return String.format(FILE_NAME_PATTERN, fileNamePattern, id != null ? id.toString() : "", format.getFileExtension(true));
   }

   @Override
   public String getFileName(String suffix) {
	  return String.format(FILE_NAME_PATTERN, fileNamePattern, suffix, reportFormat.getFileExtension(true));
   }

   @Override
   public String getZipName() {
	  return fileNamePattern + ZIP_FILE_EXT;
   }

   @Override
   public Boolean getSkipTemplate() {
	  return skipTemplate;
   }

}
