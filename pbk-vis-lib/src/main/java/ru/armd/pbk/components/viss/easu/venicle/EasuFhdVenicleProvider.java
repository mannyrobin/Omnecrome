package ru.armd.pbk.components.viss.easu.venicle;

import armd.lightSoap.client.BaseSoapClient;
import armd.lightSoap.client.SoapClientException;
import com.sap.document.sap.rfc.functions.ZPMINTEGRPBKTC;
import com.sap.document.sap.rfc.functions.ZPMINTEGRPBKTCResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.easu.EasuFhdLoader;
import ru.armd.pbk.components.viss.easu.EasuFhdSoapPacketProcessor;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class EasuFhdVenicleProvider
	extends BaseExchangeProvider {

   @Autowired
   private EasuFhdLoader loader;

   /**
	* Конструктор по умолчанию.
	*/
   public EasuFhdVenicleProvider() {
	  super(Viss.EASU_FHD, VisAuditType.VIS_EASUFHD_VENICLE);
   }

   @Override
   protected void importSoap(VisExchange visExchange, BaseSoapClient client)
	   throws SoapClientException {
	  client.setPacketProcessor(new EasuFhdSoapPacketProcessor("com.sap.document.sap.rfc.functions", "com.sap.document.sap.rfc.functions"));
	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	  ZPMINTEGRPBKTC req = new ZPMINTEGRPBKTC();
	  req.setIVDATEH(df.format(visExchange.getWorkDate()));
	  req.setIVDATEL(df.format(visExchange.getWorkDate()));

	  loader.parseObject(client.callSoap(req, "getVenicle", ZPMINTEGRPBKTCResponse.class), visExchange.getWorkDate());
   }
}
