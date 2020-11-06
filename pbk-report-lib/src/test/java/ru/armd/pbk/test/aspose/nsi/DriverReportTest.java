package ru.armd.pbk.test.aspose.nsi;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.services.aspose.nsi.DriverReportService;

public class DriverReportTest
	extends NsiReportTest {

   @Autowired
   private DriverReportService driverService;

   @Before
   public void setService()
	   throws Exception {
	  setNsiReportTest(driverService);
   }

}
