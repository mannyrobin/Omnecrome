package ru.armd.pbk.test.viss.dvr;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.dvr.records.DvrRecordExchangeProvider;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.test.BaseVisTest;

import java.util.Date;

public class DvrProviderTest
	extends BaseVisTest {

   @Autowired
   private DvrRecordExchangeProvider recordProvider;

   @Autowired
   private VisExchangeConfigRepository visExchangeConfigRepository;

   @Test
   public void test() {
	  VisExchangeConfig visExchangeConfig = visExchangeConfigRepository.getActiveImportConfig(VisExchangeObjects.DVR_RECORD);
	  recordProvider.doExchange(visExchangeConfig, null, new Date(), false);
   }
}
