package ru.armd.pbk.services.manual;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Сервис предоставления Инструкции по работе АСУ ПБК.
 */
@Service
public class ManualReportService {

   private static final String ASPOSE_TEMPLATES_PATH_PROPERTY = "reportTemplatePath";
   private static final String ASPOSE_PROPERTIES = "report-aspose.properties";

   public File generate()
	   throws IOException {
	  Properties props = new Properties();
	  props.load(getClass().getClassLoader().getResourceAsStream(ASPOSE_PROPERTIES));
	  String asposeReportTemplatePath = props.getProperty(ASPOSE_TEMPLATES_PATH_PROPERTY);
	  return new File(asposeReportTemplatePath + File.separator + "manual.docx");
   }

}
