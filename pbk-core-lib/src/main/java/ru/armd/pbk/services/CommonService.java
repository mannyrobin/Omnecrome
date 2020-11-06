package ru.armd.pbk.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseService;
import ru.armd.pbk.dto.VersionInfoDTO;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.utils.audit.TimeMeter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Сервси с общими методами.
 */
@Service
public class CommonService
	extends BaseService {

   /**
	* Метод получения информации о версии приложения.
	*
	* @return
	*/
   @Cacheable(value = "systemCache", key = "#root.method.name")
   public VersionInfoDTO getVersionInfoDTO() {
	  TimeMeter timeMeter = new TimeMeter();
	  VersionInfoDTO versionInfoDTO = new VersionInfoDTO();
	  InputStream is = null;
	  Properties properties = new Properties();
	  try {
		 is = CommonService.class.getClassLoader().getResourceAsStream("version.properties");
		 if (is == null) {
			return new VersionInfoDTO();
		 }
		 properties.load(is);

		 versionInfoDTO.setPomVersion(properties.getProperty("pom_version"));
		 versionInfoDTO.setBuildNumber(properties.getProperty("build_number"));
		 versionInfoDTO.setBuildTag(properties.getProperty("build_tag"));
		 versionInfoDTO.setBuildDate(properties.getProperty("build_date"));
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, AuditType.SYSTEM, null, null,
			 "Ошибка получения версии приложения. Ошибка: " + e.getMessage(), e);
	  } finally {
		 if (is != null) {
			try {
			   is.close();
			} catch (IOException ex) {
			   logAudit(AuditLevel.ERROR, AuditType.SYSTEM, null, null,
				   "Ошибка закрытия потока при получения версии приложения. Ошибка: " + ex.getMessage(), ex);
			}
		 }
		 logAudit(AuditLevel.DEBUG, AuditType.SYSTEM, null, null,
			 "getVersionInfoDTO" + timeMeter.printInterval(null), null);
	  }
	  return versionInfoDTO;
   }
}
