package ru.armd.pbk.test.viss.asdu;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.asdu.vehicle.GtfsVehicleLoader;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.test.BaseVisTest;

import java.io.FileInputStream;

//@Transactional
public class GtfsVenicleLoaderTest
	extends BaseVisTest {

   private static final Logger LOGGER = Logger.getLogger(GtfsDriverLoaderTest.class);

   @Autowired
   GtfsVehicleLoader gtfsVenicleLoader;

   @Test
   public void loadTest() {
	  String fileName = "GTFS_VEHICLES";
	  String filePath = getTestsDataDir() + fileName;
	  FileInputStream is = null;
	  try {
		 is = new FileInputStream(filePath);
		 gtfsVenicleLoader.importFile(is);
	  } catch (Exception ex) {
		 LOGGER.error(ex.getMessage(), ex);
		 throw new PBKException(ex.getMessage(), ex);
	  } finally {
		 try {
			is.close();
		 } catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		 }
	  }
   }

}
