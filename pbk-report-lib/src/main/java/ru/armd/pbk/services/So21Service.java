package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.repositories.So21Repository;

/**
 * Сервис стандартного отчёта "Проходы по БСК контролера".
 */
@Service
public class So21Service
extends SoService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So21Service.class);

   @Autowired
   So21Service(So21Repository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
