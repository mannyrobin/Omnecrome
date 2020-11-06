package ru.armd.pbk.test.viss.askp;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.askp.ticketcheck.AskpTicketCheckByTicketLoader;
import ru.armd.pbk.test.BaseVisTest;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AskpTicketCheckByTicketTest
	extends BaseVisTest {

   private static final Logger LOGGER = Logger.getLogger(AskpTicketCheckLoaderTest.class);

   private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

   @Autowired
   private AskpTicketCheckByTicketLoader loader;

   @Test
   public void test() {
	  String filePath = "D:\\askp";
	  File file = new File(filePath);
	  FileInputStream is = null;
	  try {
		 for (File f : file.listFiles()) {
			is = new FileInputStream(f);
			long st = new Date().getTime();
			System.out.println("Start " + st + " " + f.getName());
			loader.importFile(is, DATE_FORMAT.parse(FilenameUtils.removeExtension(f.getName()).split("_")[1]));
			System.out.println("Done " + (new Date().getTime() - st) + " " + f.getName());
			System.out.println();
		 }
	  } catch (Exception ex) {
		 LOGGER.error(ex.getMessage(), ex);
	  } finally {
		 try {
			is.close();
		 } catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		 }
	  }
   }

}
