package ru.armd.pbk.test.viss.askp;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.askp.ticketcheck.AskpTicketCheckLoader;
import ru.armd.pbk.test.BaseVisTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

public class AskpTicketCheckLoaderTest
	extends BaseVisTest {

   private static final Logger LOGGER = Logger.getLogger(AskpTicketCheckLoaderTest.class);

   @Autowired
   private AskpTicketCheckLoader loader;

   @Test
   public void test() {
	  String filePath = getTestsDataDir();
	  filePath += "askp\\";
	  File file = new File(filePath);
	  FileInputStream is = null;
	  try {
		 for (File f : file.listFiles()) {
			is = new FileInputStream(f);
			long st = new Date().getTime();
			System.out.println("Start " + st);
			loader.importFile(is);
			System.out.println("Done " + (new Date().getTime() - st));
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
