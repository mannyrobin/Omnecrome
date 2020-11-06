package ru.armd.pbk.test.viss.easu;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.easu.EasuFhdLoader;
import ru.armd.pbk.test.BaseVisTest;

import java.io.File;
import java.io.FileInputStream;

public class EasuFhdLoaderTest
	extends BaseVisTest {

   private static final Logger LOGGER = Logger.getLogger(EasuFhdLoaderTest.class);

   @Autowired
   private EasuFhdLoader loader;

   @Test
   public void test() {
	  String filePath = getTestsDataDir();
	  filePath += "easu\\";
	  File file = new File(filePath);
	  FileInputStream is = null;
	  try {
		 for (File f : file.listFiles()) {
			is = new FileInputStream(f);

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
