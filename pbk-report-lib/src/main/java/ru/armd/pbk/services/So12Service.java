package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.repositories.So12Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So12VenueShiftEmployeeCountsView;

import java.util.List;

/**
 * Сервис стандартного отчёта "Совместный график по местам встреч".
 */
@Service
public class So12Service
	extends SoService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So12Service.class);

   private So12Repository repository;

   @Autowired
   So12Service(So12Repository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<So12VenueShiftEmployeeCountsView> venuesEmployeeCounts = repository.getVenuesEmployeeCounts(params);
	  return createJsonGridData(venuesEmployeeCounts, params.getPage(), params.getCount());
   }

}
