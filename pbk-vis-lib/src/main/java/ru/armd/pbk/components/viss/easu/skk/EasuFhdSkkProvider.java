package ru.armd.pbk.components.viss.easu.skk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;

/**
 * Провайдер для СКК.
 */
@Component
public class EasuFhdSkkProvider
	extends BaseExchangeProvider {

   @Autowired
   private EasuFhdSkkLoader loader;

   /**
	* Конструктор.
	*/
   public EasuFhdSkkProvider() {
	  super(Viss.EASU_FHD, VisAuditType.VIS_EASUFHD_SKK);
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  return loader.importFile(is);
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  return true;
   }
}
