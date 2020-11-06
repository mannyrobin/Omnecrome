package ru.armd.pbk.components.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.utils.ImportResult;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Провайдер взаимодействия с ВИС АСДУ-НГПТ для импорта списка ТС.
 */
@Component
public class GisMgtProvider
	extends BaseExchangeProvider {

   public static final Logger LOGGER = Logger.getLogger(GisMgtProvider.class);

   @Autowired
   private GismgtLoader gismgtLoader;

   /**
	* Конструктор по умолчанию.
	*/
   public GisMgtProvider() {
	  super(Viss.ASDU, VisAuditType.VIS_GISMGT_UPDATE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  Pattern pattern = Pattern.compile(df.format(visExchange.getWorkDate()) + "_[_0-9]+\\.xml");
	  Matcher matcher = pattern.matcher(name);
	  return matcher.matches();
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  ImportResult<?> result = new ImportResult<Object>();
	  try {
		 result.setProcessingCount(1);
		 gismgtLoader.loadFromXml(is);
		 result.setSuccessCount(1);
		 result.setAllCount(1);
	  } catch (NoSuchMethodException | SecurityException
		  | IllegalAccessException | IllegalArgumentException
		  | InvocationTargetException | JAXBException e) {
		 LOGGER.error("Ошибка обработки", e);
	  }
	  return result; // TODO: gismgtLoader не возвращает результат
   }
}
