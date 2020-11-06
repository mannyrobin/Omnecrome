package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.repositories.So7Repository;

/**
 * Сервис стандартного отчёта "Количество перевезенных пассажиров по маршрутам".
 */
@Service
public class So7Service
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So7Service.class);

   @Autowired
   So7Service(So7Repository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
