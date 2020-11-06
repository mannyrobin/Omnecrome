package ru.armd.pbk.test.viss.gismgt;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.gismgt.GismgtLoader;
import ru.armd.pbk.test.BaseVisTest;

import java.io.File;
import java.io.FileInputStream;

public class GismgtLoaderTest
	extends BaseVisTest {

   private static final Logger LOGGER = Logger.getLogger(GismgtLoaderTest.class);

   @Autowired
   private GismgtLoader loader;

   @Test
   public void test() {
	  String filePath = getTestsDataDir();
	  filePath += "gis\\";
	  File file = new File(filePath);
	  FileInputStream is = null;
	  try {
		 for (File f : file.listFiles()) {
			LOGGER.info("Start process file " + f.getName());
			is = new FileInputStream(f);
			loader.loadFromXml(is);
			LOGGER.info("Done process file " + f.getName());
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
