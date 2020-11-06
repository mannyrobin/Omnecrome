package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.repositories.So6Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So6View;

import java.util.List;

/**
 * Сервис стандартного отчёта "Результаты контроля (мотивация)".
 */
@Service
public class So6Service
	extends SoService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So6Service.class);

   private So6Repository repository;

   @Autowired
   So6Service(So6Repository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<So6View> so6Views = repository.getGridViews(params);
	  return createJsonGridData(so6Views, params.getPage(), params.getCount());
   }
}
