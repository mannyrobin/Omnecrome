package ru.armd.pbk.test.viss.easu;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.easu.tripschedule.EasuTripScheduleProvider;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.test.BaseVisTest;

import java.util.Date;

public class EasuFhdTripScheduleProviderTest
	extends BaseVisTest {

   @Autowired
   private EasuTripScheduleProvider provider;

   @Autowired
   private VisExchangeConfigRepository visExchangeConfigRepository;

   @Test
   public void test() {
	  VisExchangeConfig visExchangeConfig = visExchangeConfigRepository.getActiveImportConfig(VisExchangeObjects.EASU_FHD_TRIP_SCHEDULE);
	  provider.doExchange(visExchangeConfig, null, new Date(), false);
   }
}
