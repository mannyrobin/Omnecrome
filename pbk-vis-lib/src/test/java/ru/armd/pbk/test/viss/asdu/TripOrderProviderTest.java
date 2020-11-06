package ru.armd.pbk.test.viss.asdu;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.easu.triporder.EasuTripOrderProvider;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.test.BaseVisTest;

import java.util.Date;

public class TripOrderProviderTest
	extends BaseVisTest {

   @Autowired
   private EasuTripOrderProvider provider;

   @Autowired
   private VisExchangeConfigRepository visExchangeConfigRepository;

   @Test
   public void tripOrderProviderTest() {
	  VisExchangeConfig visExchangeConfig = visExchangeConfigRepository.getActiveImportConfig(VisExchangeObjects.EASU_FHD_TRIP_ORDER);
	  provider.doExchange(visExchangeConfig, null, new Date(), false);
   }

}
