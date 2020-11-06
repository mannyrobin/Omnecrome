package ru.armd.pbk.core.repositories;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.mappers.IDeviceOwnersHistoryMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.exceptions.PBKException;

import java.util.List;

/**
 * Базовый репозиторий для устройств с иторией владения.
 */
public class BaseDeviceOwnersHistoryRepository<VersionDomain extends BaseVersionDomain>
	extends BaseVersionDomainRepository<VersionDomain>
	implements IDeviceOwnersHistoryRepository<VersionDomain> {

   private IDeviceOwnersHistoryMapper deviceOwnersHistoryMapper;

   public BaseDeviceOwnersHistoryRepository(AuditType domainAuditType,
											IDeviceOwnersHistoryMapper deviceOwnersHistoryMapper) {
	  super(domainAuditType, deviceOwnersHistoryMapper);
	  this.deviceOwnersHistoryMapper = deviceOwnersHistoryMapper;
   }

   @Override
   public IDeviceOwnersHistoryMapper<VersionDomain> getOwnerHistoryDomainMapper() {
	  if (deviceOwnersHistoryMapper != null) {
		 return deviceOwnersHistoryMapper;
	  }
	  throw new UnsupportedOperationException(
		  "Метод getOwnerHistoryDomainMapper для класса " + this.getClass().getName() + " не реализован.");
   }

   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getOwnersHistoryViews(
	   Params params) {
	  List<Views> ownersHist = null;
	  try {
		 ownersHist = getOwnerHistoryDomainMapper().getOwnersHistoryViews(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список истории смены владельцев. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return ownersHist;
   }
}
