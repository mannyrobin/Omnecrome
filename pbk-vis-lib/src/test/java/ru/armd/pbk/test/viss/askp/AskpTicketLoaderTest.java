package ru.armd.pbk.test.viss.askp;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.askp.ticket.AskpTicketLoader;
import ru.armd.pbk.test.BaseVisTest;

import java.io.File;
import java.io.FileInputStream;

public class AskpTicketLoaderTest
	extends BaseVisTest {

   private static final Logger LOGGER = Logger.getLogger(AskpTicketLoaderTest.class);

   @Autowired
   private AskpTicketLoader loader;

   @Test
   public void test() {
	  String filePath = getTestsDataDir();
	  filePath += "askp\\";
	  File file = new File(filePath);
	  FileInputStream is = null;
	  try {
		 for (File f : file.listFiles()) {
			is = new FileInputStream(f);
			loader.importFile(is);
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
