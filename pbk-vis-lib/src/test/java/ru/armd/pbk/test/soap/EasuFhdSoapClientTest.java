package ru.armd.pbk.test.soap;

import armd.lightSoap.client.BaseSoapClient;
import armd.lightSoap.client.BaseSoapClientParameters;
import com.sap.document.sap.rfc.functions.ZPBKBAL;
import com.sap.document.sap.rfc.functions.ZPBKBALResponse;
import com.sap.document.sap.rfc.functions.ZPBKDEP;
import com.sap.document.sap.rfc.functions.ZPBKDEPResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.easu.EasuFhdLoader;
import ru.armd.pbk.components.viss.easu.EasuFhdSoapPacketProcessor;
import ru.armd.pbk.test.BaseVisTest;

public class EasuFhdSoapClientTest
	extends BaseVisTest {

   @Autowired
   private EasuFhdLoader loader;

   //	@Test
   public void sendTest() {

	  BaseSoapClientParameters clientParameters = new BaseSoapClientParameters();
	  clientParameters.setServiceAddress("http://saptest1.mosgortrans.com:8000/sap/bc/soap/rfc");
	  clientParameters.setServiceUsername("rfc_user");
	  clientParameters.setServicePassword("tiger");
	  clientParameters.setLogExchangeToFiles(true);
	  clientParameters.setLogExchangeDir("./soap-logs/");

	  EasuFhdSoapPacketProcessor fhdSoapPacketProcessor = new EasuFhdSoapPacketProcessor(
		  "com.sap.document.sap.rfc.functions",
		  "com.sap.document.sap.rfc.functions");

	  BaseSoapClient fhdSoapClient = new BaseSoapClient(clientParameters, null) {
	  };

	  fhdSoapClient.setPacketProcessor(fhdSoapPacketProcessor);
	  try {
		 ZPBKDEP req = new ZPBKDEP();
		 ZPBKDEPResponse resp = fhdSoapClient.callSoap(req, "getDepartments", ZPBKDEPResponse.class);

	  } catch (Throwable t) {
		 t.printStackTrace();
	  }
   }

   @Test
   public void sendTestWorkMode() {

	  BaseSoapClientParameters clientParameters = new BaseSoapClientParameters();
	  clientParameters.setServiceAddress("http://sapprd.mosgortrans.com:8000/sap/bc/soap/rfc");
	  clientParameters.setServiceUsername("rfc_user");
	  clientParameters.setServicePassword("tiger");
	  clientParameters.setLogExchangeToFiles(true);
	  clientParameters.setLogExchangeDir("./soap-logs/");

	  EasuFhdSoapPacketProcessor fhdSoapPacketProcessor = new EasuFhdSoapPacketProcessor(
		  "com.sap.document.sap.rfc.functions",
		  "com.sap.document.sap.rfc.functions");

	  BaseSoapClient fhdSoapClient = new BaseSoapClient(clientParameters, null) {
	  };

	  fhdSoapClient.setPacketProcessor(fhdSoapPacketProcessor);
	  try {


		 ZPBKBAL reqBal = new ZPBKBAL();
		 reqBal.setFEATD("0");
		 reqBal.setPERNR("67017400");
		 ZPBKBALResponse workModeResponse = fhdSoapClient.callSoap(reqBal, "getEmployeeWorkMode", ZPBKBALResponse.class);
	  } catch (Throwable t) {
		 t.printStackTrace();
	  }
   }

}
