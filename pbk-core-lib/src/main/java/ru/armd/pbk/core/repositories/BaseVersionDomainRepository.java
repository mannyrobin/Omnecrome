package ru.armd.pbk.core.repositories;

import org.apache.log4j.Logger;
import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.HeadVersionDomain;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Базовая реализация интерфейса репозитория версионного домена.
 *
 * @param <VersionDomain> Версионный домен.
 */
public abstract class BaseVersionDomainRepository<VersionDomain extends BaseVersionDomain>
	extends BaseDomainRepository<VersionDomain>
	implements IVersionDomainRepository<VersionDomain> {

   public static final Logger LOGGER = Logger.getLogger(BaseVersionDomainRepository.class);

   private IVersionDomainMapper versionDomainMapper;

   /**
	* Конструктор по умолчанию.
	*/
   public BaseVersionDomainRepository() {
	  super();
   }

   /**
	* * Конструктор.
	*
	* @param domainAuditType     Тип события домена аудита.
	* @param versionDomainMapper Реализация интерфейса маппера домена для работы с БД.
	*/
   public BaseVersionDomainRepository(AuditType domainAuditType, IVersionDomainMapper versionDomainMapper) {
	  super(domainAuditType, versionDomainMapper);
	  this.versionDomainMapper = versionDomainMapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public IVersionDomainMapper<VersionDomain> getVersionDomainMapper() {
	  if (versionDomainMapper != null) {
		 return versionDomainMapper;
	  }
	  throw new UnsupportedOperationException(
		  "Метод getVersionDomainMapper для класса " + this.getClass().getName() + " не реализован.");
   }

   /**
	* Создаёт новый родительский домен и первую версию объекта.
	*
	* @param domain новый домен.
	* @return
	*/
   private VersionDomain createNew(VersionDomain domain) {
	  saveHead(domain);
	  domain.setVersionStartDate(new Date());
	  domain.setVersionEndDate(DateUtils.getVersionEndDate());
	  return create(domain);
   }

   private synchronized void saveHead(VersionDomain domain) {
	  HeadVersionDomain headDomain = new HeadVersionDomain();
	  getVersionDomainMapper().insertHead(headDomain);
	  domain.setHeadId(headDomain.getId());
   }

   @Override
   public VersionDomain save(VersionDomain domain) {
	  int count = 0;
	  // Если у нас отсутсвует head, создаем его
	  if (domain.getHeadId() == null) {
		 return createNew(domain);
	  }
	  return update(domain);
   }

   @Override
   public VersionDomain saveVersion(VersionDomain versionDomain) {
	  if (versionDomain.getHeadId() == null) {
		 return createNew(versionDomain);
	  }
	  VersionDomain actualVersionDomain = getActual(versionDomain.getHeadId());
	  if (actualVersionDomain != null) {
		 actualVersionDomain.setVersionEndDate(versionDomain.getVersionStartDate());
		 update(actualVersionDomain);
	  }
	  versionDomain.setVersionEndDate(DateUtils.getVersionEndDate());
	  return create(versionDomain);
   }

   @Override
   public VersionDomain getActual(Long headId) {
	  VersionDomain versionDomain = null;
	  try {
		 versionDomain = getVersionDomainMapper().getActual(headId);
	  } catch (Exception e) {
		 String message = "Не удалось получить запись. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, headId, message, e);
		 throw new PBKException(message, e);
	  }
	  return versionDomain;
   }

   @Override
   public List<VersionDomain> getHistory(Long headId) {
	  List<VersionDomain> history = null;
	  try {
		 history = getVersionDomainMapper().getHistory(headId);
	  } catch (Exception e) {
		 String message = "Не удалось получить список истории. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, headId, message, e);
		 throw new PBKException(message, e);
	  }
	  return history;
   }

   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getHistoryViews(Params params) {
	  List<Views> views = null;
	  try {
		 views = getVersionDomainMapper().getHistoryViews(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список истории gridView. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return views;
   }

   @Override
   public int deleteSoft(List<Long> ids, Boolean tryDelete) {
	  for (Long id : ids) {
		 VersionDomain actualVersionDomain = getActual(id);
		 if (actualVersionDomain != null) {
			actualVersionDomain.setVersionEndDate(new Date());
			save(actualVersionDomain);
		 }
	  }
	  int count = super.deleteSoft(ids, tryDelete);
	  return count;
   }

   /**
	* Обновить головной домен.
	*/
   protected void updateHead(VersionDomain domain) {
	  HeadVersionDomain headDomain = new HeadVersionDomain();
	  headDomain.setId(domain.getHeadId());
	  headDomain.setIsDelete(domain.getIsDelete());
	  getVersionDomainMapper().updateHead(headDomain);
   }
}
