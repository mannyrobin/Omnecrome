package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.domain.viss.gismgt.GmBaseDomain;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.TComponentIds;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

/**
 * Абстрактный класс для конвертера.
 * Делает конвертацию из сущности Gismgt в Домен.
 *
 * @param <Gis>    - сущность Gismgt.
 * @param <Domain> - Домен.
 */
public abstract class AbstractGmConverter<Gis, Domain extends GmBaseDomain>
	extends BaseComponent {

   public static final String OPERATION_CREATE = "CREATE";
   public static final String OPERATION_UPDATE = "UPDATE";
   public static final String OPERATION_NOP = "NOP";

   public static final Logger LOGGER = Logger.getLogger(AbstractGmConverter.class);

   private AuditType gisAuditType;

   /**
	* Конструктор.
	*
	* @param gisAuditType - объект аудита.
	*/
   public AbstractGmConverter(AuditType gisAuditType) {
	  this.gisAuditType = gisAuditType;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   protected abstract IGmMapper<Domain> getMapper();

   protected abstract Domain convert(Gis gis);

   protected abstract Gis toGis(ObjectMessage objectMessage);

   protected abstract Domain fillMain(Long muid);

	protected Boolean merge(Domain to, Domain from) {
		Boolean result = false;
		if (from.getId() != null && !from.getId().equals(to.getId())) {
			result = true;
			to.setId(from.getId());
		}
		if (to.getIsDelete() != from.getIsDelete()) {
			result = true;
			to.setIsDelete(from.getIsDelete());
		}
		//	ГИС МГТ версия приходит в поле version и хранится в gmVersion
		//  и функционального значения не имеет
		if (to.getGmVersion() == null || !to.getGmVersion().equals(from.getVersion())) {
			result = true;
			to.setGmVersion(from.getVersion());
		}
		if (from.getAsduId() != null && !from.getAsduId().equals(to.getAsduId())) {
			//ASDU_ID не храниться в ГИС, храниться только в ПБК.
			to.setAsduId(from.getAsduId());
		}
		// Версия в поле version не имеет отношения к версии ГИС МГТ
		// функционал в коде привязан к ней, а именно ее максимальному значению
		// инкрементируется при каждом обновлении объекта
		to.setVersion(to.getVersion() + 1);
		return result;
	}

   protected void insert(Domain domain) {
	  try {
		 getMapper().insert(domain);
		 logAudit(AuditLevel.DEBUG, gisAuditType,
			 AuditObjOperation.INSERT, domain, domain.toAuditString(),
			 null);
		  LOGGER.info("Успешно добавлен в БД " + domain.getClass() + " MUID: " + domain.getMuid() + " version: " + domain.getVersion());
	  } catch (Exception e) {
		 logAudit(AuditLevel.DEBUG, VisAuditType.EXCEPTION,
			 AuditObjOperation.INSERT, domain, e.getMessage(), e);
		  LOGGER.error("Ошибка добавления в БД " + domain.getClass() + " MUID: " + domain.getMuid() + " version: " + domain.getVersion());
	  }
   }

   protected void update(Domain domain) {
	  try {
		 getMapper().update(domain);
		 logAudit(AuditLevel.DEBUG, gisAuditType,
			 AuditObjOperation.UPDATE, domain, domain.toAuditString(),
			 null);
		 LOGGER.info("Успешно обновлен в БД " + domain.getClass() + " MUID: " + domain.getMuid() + " version: " + domain.getVersion());
	  } catch (Exception e) {
		 logAudit(AuditLevel.DEBUG, VisAuditType.EXCEPTION,
			 AuditObjOperation.UPDATE, domain, e.getMessage(), e);
		  LOGGER.error("Ошибка обновления в БД " + domain.getClass() + " MUID: " + domain.getMuid() + " version: " + domain.getVersion());
	  }
   }

   private Domain getDomainByMuid(Domain domain) {
	  return getMapper().getByMuid(domain.getMuid());
   }

	protected void save(Domain domain, String operation) {
		Domain oldDomain = getDomainByMuid(domain);
		if (oldDomain == null) {
			domain.setGmVersion(domain.getVersion());
			insert(domain);
		} else if (merge(oldDomain, domain)) {
			update(oldDomain);
		}
	}

   /**
	* Сохранить объект обмена ВИС ГИС МГТ в БД.
	*
	* @param objectMessage - объект обмена ВИС ГИС МГТ.
	*/
   public void save(ObjectMessage objectMessage) {
	  convertAndSaveToDB(toGis(objectMessage), objectMessage.getHeader().getOperation().name());
   }

   /**
	* Сделать конвертацию из сущности Gismgt в Домен и сохранить в БД.
	*
	* @param gis       - сущность Gismgt.
	* @param operation - операция.
	*/
   public void convertAndSaveToDB(Gis gis, String operation) {

	  if (operation == null) {
		 return;
	  }
	  Domain domain = null;
	  try {
		 domain = convert(gis);
		 if (domain != null) {
			save(domain, operation);
		 }
	  } catch (Exception e) {
		 logAudit(AuditLevel.DEBUG, VisAuditType.EXCEPTION,
			 AuditObjOperation.OTHER, domain, e.getMessage(), e);
		 LOGGER.error("Ошибка конвертирования объекта " + gis.getClass());
	  }
   }

   /**
	* Сохранить МУИД объекта обмена ВИС ГИС МГТ в БД.
	*
	* @param muid - МУИД объекта обмена ВИС ГИС МГТ.
	*/
   public void saveId(Long muid) {
	  Domain newDomain = fillMain(muid);
	  Domain oldDomain = getDomainByMuid(newDomain);
	  if (oldDomain == null) {
		 insert(newDomain);
	  }
   }

   protected String getStringValue(JAXBElement<String> value) {
	  if (value != null) {
		 return value.getValue();
	  }
	  return null;
   }

   protected byte[] getByteValue(JAXBElement<byte[]> value) {
	  if (value != null) {
		 return value.getValue();
	  }
	  return null;
   }

   protected Date getDateValue(JAXBElement<XMLGregorianCalendar> value) {
	  if (value != null) {
		 return value.getValue().toGregorianCalendar().getTime();
	  }
	  return null;
   }

   protected Long getLongValue(JAXBElement<Long> value) {
	  if (value != null) {
		 return value.getValue().longValue();
	  }
	  return null;
   }

   protected Integer getIntValue(JAXBElement<BigInteger> value) {
	  if (value != null) {
		 return value.getValue().intValue();
	  }
	  return null;
   }

   protected Long getLongValueFromString(String value) {
	  if (value != null) {
		 return Long.valueOf(value).longValue();
	  }
	  return null;
   }

   protected Float getFloatValue(JAXBElement<Float> value) {
	  if (value != null) {
		 return value.getValue();
	  }
	  return null;
   }

   protected Long getId(TComponentIds ids) {
	  return Long.valueOf(
		  ids.getComponentIds().get(0).getComponentIdent().getValue())
		  .longValue();
   }

   protected String getAsduId(TComponentIds ids) {
	  return getStringValue(ids.getComponentIds().get(0).getComponentIdent()); // get(1).getComponentIdent());
   }
}
