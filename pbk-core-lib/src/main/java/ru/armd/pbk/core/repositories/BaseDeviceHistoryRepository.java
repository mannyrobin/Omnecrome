package ru.armd.pbk.core.repositories;

import ru.armd.pbk.core.domain.BaseDeviceOwnerHistoryDomain;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.utils.date.DateUtils;

/**
 * Базовый репозиторий для истории усйтроств.
 */
public abstract class BaseDeviceHistoryRepository<VersionDomain extends BaseDeviceOwnerHistoryDomain>
	extends BaseVersionDomainRepository<VersionDomain> {

   /**
	* Конструктор.
	*
	* @param domainAuditType     Тип события домена аудита.
	* @param versionDomainMapper Реализация интерфейса маппера домена для работы с БД.
	*/
   public BaseDeviceHistoryRepository(AuditType domainAuditType, IVersionDomainMapper<VersionDomain> versionDomainMapper) {
	  super(domainAuditType, versionDomainMapper);
   }

   @Override
   public VersionDomain saveVersion(VersionDomain versionDomain) {
	  VersionDomain actualVersionDomain = getActual(versionDomain.getDeviceId());
	  if (actualVersionDomain != null) {
		 actualVersionDomain.setVersionEndDate(versionDomain.getVersionStartDate());
		 update(actualVersionDomain);
	  }
	  versionDomain.setVersionEndDate(DateUtils.getVersionEndDate());
	  return create(versionDomain);
   }

}
