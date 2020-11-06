package ru.armd.pbk.core.repositories;

import org.apache.log4j.Logger;
import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasId;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.sql.SQLAdapter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Базовая реализация интерфейса репозитория домена.
 *
 * @param <Domain> Домен.
 */
public abstract class BaseDomainRepository<Domain extends BaseDomain>
	extends BaseComponent
	implements IDomainRepository<Domain> {

   public static final Logger LOGGER = Logger.getLogger(BaseDomainRepository.class);

   protected IDomainMapper<Domain> domainMapper;

   private AuditType domainAuditType;


   /**
	* Конструктор по умолчанию.
	*/
   public BaseDomainRepository() {
	  super();
   }

   /**
	* Конструктор.
	*
	* @param domainAuditType Тип события домена аудита.
	* @param domainMapper    Реализация интерфейса маппера домена для работы с БД.
	*/
   public BaseDomainRepository(AuditType domainAuditType, IDomainMapper<Domain> domainMapper) {
	  this.domainAuditType = domainAuditType;
	  this.domainMapper = domainMapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public AuditType getDomainAuditType() {
	  if (domainAuditType != null) {
		 return domainAuditType;
	  }
	  throw new UnsupportedOperationException(
		  "Метод getDomainAuditType для класса " + this.getClass().getName() + " не реализован.");
   }

   @Override
   public IDomainMapper<Domain> getDomainMapper() {
	  if (domainMapper != null) {
		 return domainMapper;
	  }
	  throw new UnsupportedOperationException(
		  "Метод getDomainMapper для класса " + this.getClass().getName() + " не реализован.");
   }

   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  List<Views> views = null;
	  try {
		 views = getDomainMapper().getGridViews(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список gridView. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return views;
   }

   @Override
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getSelectItems(
	   Params params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = getDomainMapper().getSelectItems(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   @Override
   public List<Domain> getDomains(Map<String, Object> params) {
	  List<Domain> domains = null;
	  try {
		 domains = getDomainMapper().getDomains(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список доменов. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return domains;
   }

   @Override
   public Domain getDomain(Map<String, Object> params) {
	  Domain domain = null;
	  try {
		 domain = getDomainMapper().getDomain(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить домен. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

   @Override
   public List<Long> getIds(Map<String, Object> params) {
	  List<Long> ids = null;
	  try {
		 ids = getDomainMapper().getIds(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список id. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return ids;
   }

   @Override
   public Domain save(Domain domain) {
	  if (domain == null) {
		 return null;
	  }
	  if (domain.getId() == null) {
		 return create(domain);
	  } else {
		 return update(domain);
	  }
   }

   /**
	* Создает домен в БД.
	*
	* @param domain Домен.
	* @return Созданный домен.
	*/
   protected Domain create(Domain domain) {
	  int count = 0;
	  try {
		 initCreaterInfo(domain);
		 initUpdaterInfo(domain);
		 synchronized (this) {
			count = getDomainMapper().insert(domain);
		 }
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.INSERT, domain,
			 "Успешное создание записи", null);
	  } catch (Exception e) {
		  String message = "Не удалось создать запись. Ошибка: " + domain.toAuditString() + " " + e.getMessage();
		  logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.INSERT, domain, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

   @Override
   public int save(List<Domain> domains, Date date) {
	  try {
		 synchronized (this) {
			return domains == null ? 0
				: date == null ? domainMapper.insertChunck(domains)
				: domainMapper.insertChunckOnDate(domains, date);
		 }
	  } catch (Exception e) {
		 String message = "Не удалось создать записи. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.INSERT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Обновляет домен в БД.
	*
	* @param domain Домен.
	* @return Измененный домен.
	*/
   protected Domain update(Domain domain) {
	  int count = 0;
	  try {
		 initUpdaterInfo(domain);
		 count = getDomainMapper().update(domain);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, domain,
			 "Успешное обновление записи", null);
	  } catch (Exception e) {
		 String message = "Не удалось обновить запись. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, domain, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

   @Override
   public Domain getById(Long id) {
	  Domain domain = null;
	  try {
		 domain = getDomainMapper().getById(id);
	  } catch (Exception e) {
		 String message = "Не удалось получить запись по id. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, id, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

   @Override
   public Domain getById(IHasId hasId) {
	  if (hasId == null) {
		 return null;
	  }
	  return getById(hasId.getId());
   }

   @Override
   public Domain getByCode(String code) {
	  Domain domain = null;
	  try {
		 domain = getDomainMapper().getByCode(code);
	  } catch (Exception e) {
		 String message = "Не удалось получить запись по code. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, code, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

   @Override
   public Domain getByCode(IHasCod hasCod) {
	  if (hasCod == null) {
		 return null;
	  }
	  return getByCode(hasCod.getCod());
   }

   @Override
   public int delete(List<Long> ids) {
	  int count = 0;
	  if (ids == null) {
		 return count;
	  }
	  try {
		 count = getDomainMapper().delete(ids);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.DELETE, ids,
			 "Успешно удалено " + count + " из " + ids.size() + " записи/записей.", null);
	  } catch (Exception e) {
		 String message = "Не удалось удалить записи. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.DELETE, ids, message, e);
		 throw new PBKException(message, e);
	  }
	  return count;
   }

   @Override
   public int deleteSoft(List<Long> ids, Boolean tryDelete) {
	  int count = 0;
	  if (ids == null) {
		 return count;
	  }
	  if (!tryDelete) {
		 try {
			// TODO: Нужно по списку пробежаться и каждый из них удалять
			count = getDomainMapper().deleteSoft(ids);
		 } catch (Exception ex) {
			String message = "Не удалось удалить записи. Ошибка: " + ex.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.DELETE, ids, message, ex);
			throw new PBKException(message, ex);
		 }
	  } else {
		 try {
			count = getDomainMapper().delete(ids);
			logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.DELETE, ids,
				"Успешно удалено " + count + " из " + ids.size() + " записи/записей.", null);
		 } catch (Exception e) {
			try {
			   // TODO: Нужно по списку пробежаться и каждый из них удалять
			   count = getDomainMapper().deleteSoft(ids);
			} catch (Exception ex) {
			   String message = "Не удалось удалить записи. Ошибка: " + ex.getMessage();
			   logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.DELETE, ids, message, ex);
			   throw new PBKException(message, ex);
			}
			logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.DELETE, ids,
				"Записи не могут быть удалены, т.к. существуют данные, ссылающиеся на них.", null);
		 }
	  }
	  return count;
   }

   @Override
   public int delete(Long id) {
	  int count = 0;
	  if (id == null) {
		 return count;
	  }
	  return delete(Arrays.asList(id));
   }

   @Override
   public int delete(Domain domain) {
	  int count = 0;
	  if (domain == null) {
		 return count;
	  }
	  return delete(domain.getId());
   }

   public void execSql(String sql) {
	  getDomainMapper().execSql(new SQLAdapter(sql));
   }

}
