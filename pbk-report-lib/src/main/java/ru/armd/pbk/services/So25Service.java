package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.enums.core.TaskCheckType;
import ru.armd.pbk.repositories.So24Repository;
import ru.armd.pbk.repositories.So25Repository;

import java.util.List;

/**
 * Сервис стандартного отчёта "Cписок маршрутов АСМ-ПП".
 */
@Service
public class So25Service
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So25Service.class);

   @Autowired
   So25Service(So25Repository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
