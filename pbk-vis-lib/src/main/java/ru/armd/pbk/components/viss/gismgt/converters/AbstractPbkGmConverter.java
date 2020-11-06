package ru.armd.pbk.components.viss.gismgt.converters;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.viss.gismgt.GmBaseDomain;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;

import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактный класс для конвертера сущностей для БД НСИ.
 * Делает конвертацию из сущности Gismgt в Домен и сохранение в БД НСИ.
 *
 * @param <Gis>       - сущность Gismgt.
 * @param <Domain>    - Домен.
 * @param <PbkDomain> - домен ПБК.
 */
public abstract class AbstractPbkGmConverter<Gis, Domain extends GmBaseDomain, PbkDomain extends BaseDomain>
	extends AbstractGmConverter<Gis, Domain> {

   /**
	* Конструктор.
	*
	* @param gisAuditType - объект аудита.
	*/
   public AbstractPbkGmConverter(AuditType gisAuditType) {
	  super(gisAuditType);
   }

   protected abstract void pbkInsert(PbkDomain pbkDomain, Domain domain);

   protected abstract void pbkUpdate(PbkDomain pbkDomain, Domain domain);

   protected abstract IDomainRepository<PbkDomain> getPbkRepository();

   @Override
   protected void insert(Domain domain) {
	  Long gmId = domain.getId();
	  super.insert(domain);
	  PbkDomain pbkDomain = null;
	  try {
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("gmId", gmId);
		 pbkDomain = getPbkRepository().getDomain(params);
		 pbkInsert(pbkDomain, domain);
	  } catch (Exception e) {
		 logAudit(AuditLevel.DEBUG, VisAuditType.EXCEPTION,
			 AuditObjOperation.INSERT, domain, e.getMessage(), e);
	  }
   }

   @Override
   protected void update(Domain domain) {
	  super.update(domain);
	  PbkDomain pbkDomain = null;
	  try {
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("gmId", domain.getId());
		 pbkDomain = getPbkRepository().getDomain(params);
		 pbkUpdate(pbkDomain, domain);
	  } catch (Exception e) {
		 logAudit(AuditLevel.DEBUG, VisAuditType.EXCEPTION,
			 AuditObjOperation.INSERT, domain, e.getMessage(), e);
	  }
   }

}
