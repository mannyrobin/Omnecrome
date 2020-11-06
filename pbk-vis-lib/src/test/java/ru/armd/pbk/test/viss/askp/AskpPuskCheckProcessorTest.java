package ru.armd.pbk.test.viss.askp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.askp.AskpExchangeProcessor;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.test.BaseVisTest;

public class AskpPuskCheckProcessorTest
	extends BaseVisTest {

   @Autowired
   private AskpExchangeProcessor processor;

   @Test
   public void test() {
	  processor.doImport(VisExchangeObjects.ASKP_PUSK_CHECK, null, null, null, true);
   }
}
