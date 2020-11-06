package ru.armd.pbk.aspose.core;

import com.aspose.words.License;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Реестр отчётов - центральный класс подсистемы отчётов.
 */
@Component
public class AsposeReports {

   private static final Logger LOGGER = Logger.getLogger(AsposeReports.class);

   private static final String TEMPORARY_DIRECTORY_PROPERTY = "java.io.tmpdir";
   private static final String ROOT_DIRECTORY_PATTERN = "pbk.reports_%d";
   private static final String ASPOSE_WORD_LICENSE_RESOURCE_PATH = "/asposeLicense/Aspose.Words.lic";
   private static final String ASPOSE_CELLS_LICENSE_RESOURCE_PATH = "/asposeLicense/Aspose.Cells.lic";

   private static final String ASPOSE_TEMPLATES_PATH_PROPERTY = "reportTemplatePath";


   /**
	* <code>true</code>, если инициализация состоялась.
	*/
   private static volatile boolean initialized;
   /**
	* Корневая директория для работы.
	*/
   private static String rootDirectory;

   static {
	  if (!initialized) {
		 initialize();
	  }
   }

   /**
	* Отчёты, шаблоны для которых найдены и находятся в rootDirectory.
	*/
   private static Set<IReportType> knownReports = new HashSet<>();
   /**
	* Отображение {тип_отчёта -> имя_предобработанного_шаблона}
	*/
   private static Map<IReportType, String> preprocessedTemplates = new HashMap<>();

   private ReportReflector reportReflector = new ReportReflector();

   @Autowired
   private ProcessorProvider processorProvider;


   /**
	* Генерирует отчёт заданного типа, заполняя его из переданных данных.
	*
	* @param reportType Тип отчёта.
	* @param reportData Данные для отчёта.
	* @param formats    Форматы выходных документов. По умолчанию ReportFormat.MS_WORD
	* @return Соотетствие между формата и сгенерированными файлами.
	*/
   private Map<ReportFormat, File> generateReport(IReportType reportType, ReportData reportData, ReportFormat... formats)
	   throws PBKAsposeReportException {
	  try {
		 if (reportType == null || reportData == null) {
			throw new PBKAsposeReportException("Не задан необходимый параметр");
		 }

		 if (!knownReports.contains(reportType) && !reportType.getSkipTemplate()) {
			preprocessTemplate(reportType);
		 }

		 if (!preprocessedTemplates.containsKey(reportType) && !reportType.getSkipTemplate()) {
			throw new PBKAsposeReportException(String.format("Для отчёта \"%s\" не найден предобработанный шаблон", reportType.getDescription()));
		 }

		 LOGGER.info(String.format("Генерация отчёта \"%s\"", reportType.getDescription()));

		 IReportProcessor processor = processorProvider.getPooledProcessor(reportType);
		 processor.setTemplateData(reportData.getScalarValues());
		 processor.setDataSet(reportData.getDataSet());
		 processor.setDataBean(reportData.getDataBean());
		 processor.setCallbacks(reportData.getCallbacks());
		 processor.setReportType(reportType);

		 String reportFileName = processor.getResultReportNameData(Arrays.asList(reportData)).buildName();
		 Map<ReportFormat, File> resultReport = processor.processTemplate(reportFileName, formats);
		 processorProvider.returnPooledProcessor(reportType, processor);

		 return resultReport;
	  } catch (Throwable t) {
		 throw new PBKAsposeReportException("Ошибка генерации отчета", t);
	  }
   }

   /**
	* Генерирует отчёт заданного типа, заполняя его шаблон значениями свойств.
	*
	* @param reportType Тип отчёта.
	* @param reportData Данные для отчёта.
	* @return Соотетствие между форматами и сгенерированными файлами.
	*/
   public Map<ReportFormat, File> generateReport(IReportType reportType, ReportData reportData)
	   throws PBKAsposeReportException {
	  try {
		 if (reportType == null || reportData == null) {
			throw new PBKAsposeReportException("Не задан необходимый параметр");
		 }
		 if (!ReportFormat.XLS.equals(reportType.getReportFormat())
			 && !ReportFormat.XLSX.equals(reportType.getReportFormat())
			 && reportData.getDataBean() != null) {

			Properties properties = reportReflector.reflectObject(reportData.getDataBean());
			if (properties != null) {
			   if (reportData.getScalarValues() != null) {
				  properties.putAll(reportData.getScalarValues());
			   }
			   reportData.setScalarValues(properties);
			}
		 }
		 return generateReport(reportType, reportData, reportType.getReportFormat());
	  } catch (Throwable t) {
		 throw new PBKAsposeReportException("Ошибка генерации отчета", t);
	  }
   }

   private void preprocessTemplate(IReportType reportType)
	   throws PBKAsposeReportException, IOException, URISyntaxException {
	  LOGGER.info(String.format("Копирование шаблона %s из ресурсов в директорию %s", reportType.getTemplateName(), getRootDirectory()));

	  Properties props = new Properties();
	  props.load(getClass().getClassLoader().getResourceAsStream("report-aspose.properties"));
	  String asposeReportTemplatePath = props.getProperty(ASPOSE_TEMPLATES_PATH_PROPERTY);

	  InputStream inputStream = new FileInputStream(asposeReportTemplatePath + File.separator + reportType.getName());
	  if (inputStream == null) {
		 throw new PBKAsposeReportException("Отсутствует шаблон для данного типа отчета");
	  }

	  String error = String.format("Невозможно скопировать шаблон %s из ресурсов", reportType.getTemplateName());

	  try {
		 copyTemplate(inputStream, reportType);
	  } catch (IOException e) {
		 LOGGER.error(error, e);
		 return;
	  }

	  knownReports.add(reportType);

	  LOGGER.info(String.format("Предобработка шаблона %s", reportType.getTemplateName()));

	  try {
		 IReportProcessor processor = processorProvider.getProcessorRaw(reportType);
		 processor.preprocessTemplate();

		 preprocessedTemplates.put(reportType, processor.getTemplateName());
	  } catch (Throwable e) {
		 LOGGER.error(String.format("Невозможно предобработать шаблон %s", reportType.getTemplateName()), e);
	  }
   }

   private void copyTemplate(InputStream inputStream, IReportType reportType)
	   throws IOException {
	  File outputFile = new File(getRootDirectory(), reportType.getTemplateName());

	  FileOutputStream outputStream = new FileOutputStream(outputFile);
	  IOUtils.copyStream(inputStream, outputStream);
   }


   public static ReportFormat checkReportFormatOwnOrPdf(IReportType type, String format)
	   throws PBKAsposeReportException {
	  if (type.getDefaultReportFormat().getFileExtension(false).equals(format) || format.equals("zip")) {
		 return type.getDefaultReportFormat();
	  }
	  if (ReportFormat.PDF.getFileExtension(false).equals(format)) {
		 return ReportFormat.PDF;
	  }
	  if (ReportFormat.MSWORD.getFileExtension(false).equals(format)) {
		 return ReportFormat.MSWORD;
	  }
	  if (ReportFormat.CSV.getFileExtension(false).equals(format)) {
		 return ReportFormat.CSV;
	  }
	  throw new PBKAsposeReportException("Неподдерживаемый формат");
   }

   /**
	* @return Корневая директория, используемая для хранения шаблонов, предобработанных шаблонов и сгенерированных отчётов.
	*/
   public static String getRootDirectory() {
	  return rootDirectory;
   }

   /**
	* @param reportType Тип отчёта.
	* @return Имя предобработанного шаблона отчёта.
	*/
   public static String getPreprocessedTemplateName(IReportType reportType) {
	  if (reportType == null || preprocessedTemplates == null) {
		 return null;
	  }
	  return preprocessedTemplates.get(reportType);
   }

   /**
	* Удаляет типа отчета из мапа предобработанных.
	* Удаляет все процессоры из пула, связанные с этим типом отчетов.
	* Используется при перезагрузке шаблона отчета, который уже был предобработан, чтобы новый шаблон подхватился.
	*
	* @param reportType тип отчета
	*/
   public void deleteFromKnownReports(IReportType reportType) {
	  knownReports.remove(reportType);
	  processorProvider.clearProcessorPool(reportType);
   }

   private static void initialize() {
	  synchronized (AsposeReports.class) {
		 if (initialized) {
			return;
		 }

		 LOGGER.info("Инициализация подсистемы отчётов");

		 setupRootDirectory();
		 setupAsposeWordLicense();
		 setupAsposeCellsLicense();

		 initialized = true;
	  }
   }

   private static void setupRootDirectory() {
	  LOGGER.info("Создание временной директории для хранения шаблонов и генерации отчётов");

	  String dirPath = System.getProperties().getProperty(TEMPORARY_DIRECTORY_PROPERTY);
	  if (dirPath == null) {
		 LOGGER.error(String.format("Не задано значение свойства %s", TEMPORARY_DIRECTORY_PROPERTY));
		 return;
	  }

	  File dir = new File(dirPath);
	  if (!dir.isDirectory()) {
		 LOGGER.error(String.format("Директория, заданная свойством %s, не существует", TEMPORARY_DIRECTORY_PROPERTY));
		 return;
	  }

	  if (!dir.canWrite()) {
		 LOGGER.error(String.format("Нет прав на запись в директорию, заданную свойством %s", TEMPORARY_DIRECTORY_PROPERTY));
		 return;
	  }

	  String rootDirName = String.format(ROOT_DIRECTORY_PATTERN, System.currentTimeMillis());
	  File rootDir = new File(dir, rootDirName);
	  if (!rootDir.mkdir()) {
		 LOGGER.error("Невозможно создать временную директорию для хранения шаблонов и генерации отчётов");
		 return;
	  }

	  try {
		 rootDirectory = rootDir.getCanonicalPath();
	  } catch (IOException e) {
		 LOGGER.error("Ошибка при получении канонического пути директории", e);
	  }

	  LOGGER.info(String.format("Временная директория для хранения шаблонов и генерации отчётов: %s", rootDirectory));
   }

   private static void setupAsposeWordLicense() {
	  LOGGER.info("Загрузка лицензии Aspose.Words");

	  InputStream inputStream = AsposeReports.class.getResourceAsStream(ASPOSE_WORD_LICENSE_RESOURCE_PATH);
	  if (inputStream == null) {
		 LOGGER.warn("Не найден ресурс с файлом лицензии Aspose.Words");
		 return;
	  }

	  License license = new License();
	  try {
		 license.setLicense(inputStream);
	  } catch (Exception e) {
		 LOGGER.error("Возникло исключение при загрузке лицензии Aspose.Words", e);
	  }
   }

   private static void setupAsposeCellsLicense() {
	  LOGGER.info("Загрузка лицензии Aspose.Cells");

	  InputStream inputStream = AsposeReports.class.getResourceAsStream(ASPOSE_CELLS_LICENSE_RESOURCE_PATH);
	  if (inputStream == null) {
		 LOGGER.warn("Не найден ресурс с файлом лицензии Aspose.Cells");
		 return;
	  }

	  com.aspose.cells.License license = new com.aspose.cells.License();
	  try {
		 license.setLicense(inputStream);
	  } catch (Exception e) {
		 LOGGER.error("Возникло исключение при загрузке лицензии Aspose.Cells", e);
	  }
   }
}
