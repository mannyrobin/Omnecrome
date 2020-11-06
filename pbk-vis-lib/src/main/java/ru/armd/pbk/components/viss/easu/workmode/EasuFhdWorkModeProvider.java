package ru.armd.pbk.components.viss.easu.workmode;

import armd.lightSoap.client.BaseSoapClient;
import armd.lightSoap.client.SoapClientException;
import com.sap.document.sap.rfc.functions.ZPBKBAL;
import com.sap.document.sap.rfc.functions.ZPBKBALResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.easu.EasuFhdLoader;
import ru.armd.pbk.components.viss.easu.EasuFhdSoapPacketProcessor;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.repositories.nsi.employee.EmployeeRepository;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Calendar;

@Component
public class EasuFhdWorkModeProvider
	extends BaseExchangeProvider {

   @Autowired
   private EmployeeRepository repository;

   @Autowired
   private EasuFhdLoader loader;

   /**
	* Конструктор по умолчанию.
	*/
   public EasuFhdWorkModeProvider() {
	  super(Viss.EASU_FHD, VisAuditType.VIS_EASUFHD_WORK_MODE);
   }

   @Override
   protected void importSoap(VisExchange visExchange, BaseSoapClient client)
	   throws SoapClientException {
	  client.setPacketProcessor(new EasuFhdSoapPacketProcessor("com.sap.document.sap.rfc.functions", "com.sap.document.sap.rfc.functions"));

	  ZPBKBAL reqBal = new ZPBKBAL();
	  reqBal.setFEATD("2");
	  reqBal.setPERNR(visExchange.getParameter());
	  loader.parseObject(client.callSoap(reqBal, "getEmployeeWorkMode", ZPBKBALResponse.class), visExchange.getWorkDate());

	  if (DateUtils.checkDateMonth(visExchange.getWorkDate(), Calendar.DECEMBER)) {
		 reqBal = new ZPBKBAL();
		 reqBal.setFEATD("0");
		 reqBal.setPERNR(visExchange.getParameter());
		 loader.parseObject(client.callSoap(reqBal, "getEmployeeWorkMode", ZPBKBALResponse.class), visExchange.getWorkDate());
	  }
   }
}
