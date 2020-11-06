package ru.armd.pbk.core.services;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.core.repositories.IVersionDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.utils.json.JsonGridData;

import java.util.Date;
import java.util.List;

public class BaseVersionDomainService<VersionDomain extends BaseVersionDomain, VersionDTO extends BaseVersionDTO>
	extends BaseDomainService<VersionDomain, VersionDTO>
	implements IVersionDomainService<VersionDomain, VersionDTO> {

   private static final Logger LOGGER = Logger.getLogger(BaseVersionDomainService.class);

   protected IVersionDomainRepository<VersionDomain> versionDomainRepository;

   /**
	* Конструктор.
	*
	* @param versionDomainRepository Реализация интерфейса репозитория домена.
	*/
   public BaseVersionDomainService(IVersionDomainRepository versionDomainRepository) {
	  super(versionDomainRepository);
	  this.versionDomainRepository = versionDomainRepository;
   }

   @Transactional
   @Override
   public VersionDTO getDTOById(Long id) {
	  VersionDomain domain = versionDomainRepository.getActual(id);
	  VersionDTO dto = toDTO(domain);
	  return dto;
   }

   @Transactional
   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> JsonGridData getHistoryViews(Params params) {
	  List<Views> views = versionDomainRepository.getHistoryViews(params);
	  return createJsonGridData(views, params.getPage(), params.getCount());
   }

   @Transactional
   public VersionDTO restoreVersionDTO(Long id) {
	  VersionDTO dto = toDTO(versionDomainRepository.getById(id));
	  if (dto == null) {
		 return null;
	  }
	  dto.setId(null);
	  dto.setVersionStartDate(new Date());
	  return saveVersionDTO(dto);
   }

   @Transactional
   @Override
   public VersionDTO saveVersionDTO(VersionDTO dto) {
	  VersionDomain actual = versionDomainRepository.getActual(dto.getHeadId());
	  if (actual != null && actual.getVersionStartDate().after(dto.getVersionStartDate())) {
		 throw new PBKValidationException("version start date",
			 "Дата начала новой версии должна быть позже даты начала актуальной!");
	  }
	  VersionDomain domain = toDomain(dto);
	  VersionDomain resultDomain = versionDomainRepository.saveVersion(domain);
	  return toDTO(resultDomain);
   }
}
