package ru.armd.pbk.components.viss.dvr;

import armd.lightHttp.client.BaseHttpClientParameters;
import org.apache.commons.codec.digest.DigestUtils;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;

/**
 * Базовый провайдер для ВИС ДОЗОР ПРО.
 */
public class DvrExchangeProvider
	extends BaseExchangeProvider {

   private static final String POSSIBLE = "abcdef0123456789";
   private static final String HASH = ":21232f297a57a5a743894a0e4a801fc3:";

   private IDrvLoader loader;

   /**
	* Конструктор.
	*
	* @param viss      - ВИС.
	* @param auditType - аудит.
	* @param loader    - загрузчик.
	*/
   public DvrExchangeProvider(Viss viss, AuditType auditType, IDrvLoader loader) {
	  super(viss, auditType);
	  this.loader = loader;
   }

   protected String signQuery(BaseHttpClientParameters params) {
	  String salt = "";

	  for (int i = 0; i < 10; i++) {
		 salt += POSSIBLE.charAt((int) (Math.floor(Math.random() * POSSIBLE.length())));
	  }
	   String hash = ":"+DigestUtils.md5Hex(params.getServicePassword())+":";
	  return salt + ":" + DigestUtils.shaHex(salt + hash + getPath(params.getServiceAddress()));

   }

   protected String getPath(String adress) {
	  String path = adress;
	  String[] url = adress.split("\\?");
	  if (url.length == 2) {
		 url = url[0].split("/");
		 if (url.length > 3) {
			path = "";
			for (int i = 3; i < url.length; i++) {
			   path += SLASH + url[i];
			}
		 }
	  }
	  return path;
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  return loader.importFile(is);
   }

}
