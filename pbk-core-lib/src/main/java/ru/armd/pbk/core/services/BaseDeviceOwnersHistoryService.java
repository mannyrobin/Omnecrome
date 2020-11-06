package ru.armd.pbk.core.services;

import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.core.repositories.IDeviceOwnersHistoryRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.utils.json.JsonGridData;

import java.util.List;

/**
 * Базовый сервис для приборов с историей смены владельцев.
 */
public class BaseDeviceOwnersHistoryService<VersionDomain extends BaseVersionDomain, VersionDTO extends BaseVersionDTO>
	extends BaseVersionDomainService<VersionDomain, VersionDTO>
	implements IDeviceOwnersHistoryService<VersionDomain, VersionDTO> {
   private IDeviceOwnersHistoryRepository<VersionDomain> deviceOwnersHistoryRepository;

   /**
	* Конструктор.
	*
	* @param versionDomainRepository Реализация интерфейса репозитория домена.
	*/
   public BaseDeviceOwnersHistoryService(IDeviceOwnersHistoryRepository versionDomainRepository) {
	  super(versionDomainRepository);
	  this.deviceOwnersHistoryRepository = versionDomainRepository;
   }

   @Transactional
   public <Views extends BaseGridView, Params extends BaseGridViewParams> JsonGridData getOwnersHistoryViews(
	   Params params) {
	  List<Views> views = deviceOwnersHistoryRepository.getOwnersHistoryViews(params);
	  return createJsonGridData(views, params.getPage(), params.getCount());
   }
}
