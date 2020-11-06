package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.nsi.Duty;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.DutyMapper;

import java.util.List;

/**
 * Репозиторий для работы с нарядами.
 */
@Repository
public class DutyRepository
	extends BaseDomainRepository<Duty> {

   public static final Logger LOGGER = Logger.getLogger(CountyRepository.class);

   @Autowired
   DutyRepository(DutyMapper mapper) {
	  super(NsiAuditType.NSI_DUTY, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  params.setOrderBy(params.getOrderBy().equals("workTime") ? "moveStartTime" : params.getOrderBy());
	  return super.getGridViews(params);
   }
}
