package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.repositories.So18Repository;

/**
 * Сервис стандартного отчёта "Сопоставительный анализ данных по наряд-заданию и из АСКП".
 */
@Service
public class So18Service
	extends SoService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So18Service.class);

   @Autowired
   So18Service(So18Repository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
