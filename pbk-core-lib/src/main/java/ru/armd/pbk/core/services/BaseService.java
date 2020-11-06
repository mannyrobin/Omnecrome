package ru.armd.pbk.core.services;

import org.apache.log4j.Logger;
import ru.armd.pbk.core.components.BaseComponent;

/**
 * Базовый класс сервисов.
 */
public abstract class BaseService
	extends BaseComponent {

   public static final Logger LOGGER = Logger.getLogger(BaseService.class);

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
