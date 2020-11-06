package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.repositories.So13Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So13TicketTypePassCountsView;

import java.util.List;

/**
 * Сервис стандартного отчёта "Маршруты и выходы".
 */
@Service
public class So13Service
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So13Service.class);

   private So13Repository repository;

   @Autowired
   So13Service(So13Repository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<So13TicketTypePassCountsView> ticketTypePassCountsViews = repository.getTicketTypesPassCounts(params);
	  return createJsonGridData(ticketTypePassCountsViews, params.getPage(), params.getCount());
   }
}
