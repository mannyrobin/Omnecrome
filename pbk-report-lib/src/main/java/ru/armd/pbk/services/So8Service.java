package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.repositories.So8Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So8View;

import java.util.List;

/**
 * Сервис стандартного отчёта "Работа контролеров на маршруте".
 */
@Service
public class So8Service
	extends SoService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So8Service.class);

   private So8Repository repository;

   @Autowired
   So8Service(So8Repository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<So8View> so8Views = repository.getGridViews(params);
	  return createJsonGridData(so8Views, params.getPage(), params.getCount());
   }
}
