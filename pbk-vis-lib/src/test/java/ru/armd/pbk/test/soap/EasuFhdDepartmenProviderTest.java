package ru.armd.pbk.test.soap;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.easu.EasuFhdProcessor;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.test.BaseVisTest;

public class EasuFhdDepartmenProviderTest
	extends BaseVisTest {

   @Autowired
   private EasuFhdProcessor processor;

   @Test
   public void test() {
	  processor.doImport(VisExchangeObjects.EASU_FHD_WORK_MODE, "67024770", null, null, true);
   }

}
