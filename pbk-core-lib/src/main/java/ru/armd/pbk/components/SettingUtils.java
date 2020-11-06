package ru.armd.pbk.components;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.IHasLogger;
import ru.armd.pbk.domain.core.Setting;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.core.SettingsMapper;

/**
 * Компонент настроек.
 */
@Component
public class SettingUtils
	implements IHasLogger {

   public static final Logger LOGGER = Logger.getLogger(AuditUtils.class);

   @Autowired
   protected SettingsMapper settingsMapper;

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получение настройки по id переданной.
	*
	* @param settings переданная настройка
	* @return настройка
	* @throws PBKException
	*/
   public Setting getSetting(Settings settings)
	   throws PBKException {
	  try {
		 return settingsMapper.getById(settings.getId());
	  } catch (Exception e) {
		 getLogger().error("Ошибка получения настройки: " + e.getMessage(), e);
		 throw new PBKException("Ошибка получения настройки: " + e.getMessage(), e);
	  }
   }

   /**
	* Получение значения настройки по id переданной настройки с учетом значения
	* по умолчанию.
	*
	* @param settings переданная настройка
	* @param defValue значение по умолчанию
	* @return значение настройки
	*/
   public String getSettingValue(Settings settings, String defValue) {
	  try {
		 Setting setting = getSetting(settings);
		 return setting.getValue();
	  } catch (PBKException e) {
		 return defValue;
	  }
   }

   /**
	* Получение значения настройки по id переданной настройки.
	*
	* @param settings переданная настройка
	* @return значение настройки
	*/
   public String getSettingStringValue(Settings settings) {
	  return getSettingValue(settings, null);
   }

   /**
	* Получение булевского представления настройки.
	*
	* @param settings переданная настройка
	* @return булевское представление настройки
	*/
   public boolean getSettingBooleanValue(Settings settings) {
	  String value = getSettingValue(settings, "false");
	  boolean result = (value != null) && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1"));
	  return result;
   }

}
