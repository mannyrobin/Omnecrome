package ru.armd.pbk.repositories;

import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.report.standard.SoMapper;

import java.util.List;
import java.util.Map;

/**
 * Абстрактный классс для стандартных отчетов.
 *
 * @param <Domain> домен.
 */
public abstract class SoRepository<Domain extends BaseDomain>
	extends BaseDomainRepository<Domain> {

   protected SoMapper<Domain> soMapper;

   /**
	* Конструктор.
	*
	* @param domainAuditType тип аудита.
	* @param domainMapper    маппер.
	*/
   public SoRepository(AuditType domainAuditType, IDomainMapper<Domain> domainMapper) {
	  super(domainAuditType, domainMapper);
	  if (domainMapper instanceof SoMapper<?>) {
		 this.soMapper = (SoMapper<Domain>) domainMapper;
	  }
   }

   @Override
   @DepartmentAuthorization
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  return super.getGridViews(params);
   }

   /**
	* Получение значения Итого.
	*
	* @param <View> тип значения Итого.
	* @param params параметры.
	* @return значение итого.
	*/
   @DepartmentAuthorization
   public <View extends BaseGridView> View getGridViewTotal(Map<String, Object> params) {
	  View view = null;
	  try {
		 view = soMapper.getGridViewTotal(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить значение Итого. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return view;
   }
}
