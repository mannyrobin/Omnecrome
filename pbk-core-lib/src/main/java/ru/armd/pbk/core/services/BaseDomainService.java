package ru.armd.pbk.core.services;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.domain.BaseDictionaryDomain;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.dto.BaseDictionaryDTO;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.IPageSupport;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.utils.json.JsonGridData;

import java.util.List;
import java.util.Map;

/**
 * Базовая реализация интерфейса сервиса домена и аудита.
 *
 * @param <Domain> Домен.
 * @param <DTO>    ДТО.
 */
public abstract class BaseDomainService<Domain extends BaseDomain, DTO extends BaseDTO>
	extends BaseService
	implements IDomainService<Domain, DTO> {

   private static final Logger LOGGER = Logger.getLogger(BaseDomainService.class);

   protected IDomainRepository<Domain> domainRepository;

   /**
	* Конструктор.
	*
	* @param domainRepository Реализация интерфейса репозитория домена.
	*/
   public BaseDomainService(IDomainRepository<Domain> domainRepository) {
	  this.domainRepository = domainRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Transactional
   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> JsonGridData getGridViews(Params params) {
	  List<Views> views = domainRepository.getGridViews(params);
	  return createJsonGridData(views, params.getPage(), params.getCount());
   }

   @Transactional
   @Override
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getSelectList(
	   Params params) {
	  List<SelectItem> selectItemList = domainRepository.getSelectItems(params);
	  return selectItemList;
   }

   @Transactional
   @Override
   public List<Domain> getDomains(Map<String, Object> params) {
	  return domainRepository.getDomains(params);
   }

   @Transactional
   @Override
   public List<Long> getIds(Map<String, Object> params) {
	  return domainRepository.getIds(params);
   }

   @Transactional
   @Override
   public DTO saveDTO(DTO dto) {
	  Domain domain = toDomain(dto);
	  Domain resultDomain = domainRepository.save(domain);
	  return toDTO(resultDomain);
   }

   @Transactional
   @Override
   public DTO getDTOById(Long id) {
	  Domain domain = domainRepository.getById(id);
	  DTO dto = toDTO(domain);
	  return dto;
   }

   @Transactional
   @Override
   public int delete(List<Long> ids) {
	  return domainRepository.delete(ids);
   }

   @Transactional
   @Override
   public int deleteSoft(List<Long> ids, Boolean tryDelete) {
	  return domainRepository.deleteSoft(ids, tryDelete);
   }

   @Override
   public Domain toDomain(DTO dto) {
	  throw new UnsupportedOperationException(
		  "Метод toDomain для класса " + this.getClass().getName() + " не реализован.");
   }

   @Override
   public DTO toDTO(Domain domain) {
	  throw new UnsupportedOperationException(
		  "Метод toDTO для класса " + this.getClass().getName() + " не реализован.");
   }

   protected JsonGridData createJsonGridData(List<? extends IPageSupport> views, Long page, Long count) {
	  if (views != null && views.size() > 0) {
		 Long totalCount = views.get(0).getCnt();
		 JsonGridData bean = new JsonGridData(views, totalCount, count, page);
		 return bean;
	  }
	  return new JsonGridData(views, 0, count, page);
   }

   protected JsonGridData createJsonGridData(IPageSupport views, Long page, Long count) {
	  Long totalCount = views.getCnt();
	  JsonGridData bean = new JsonGridData(views, totalCount, count, page);
	  return bean;
   }

   protected Domain initBaseDomain(DTO dto, Domain domain) {
	  if (dto == null) {
		 return null;
	  }
	  domain.setId(dto.getId());
	  return domain;
   }

   protected DTO initBaseDTO(Domain domain, DTO dto) {
	  if (domain == null) {
		 return null;
	  }
	  dto.setId(domain.getId());
	  return dto;
   }

   protected <DictionaryDomain extends BaseDictionaryDomain, DictionaryDTO extends BaseDictionaryDTO> DictionaryDomain initBaseDictionaryDomain(
	   DictionaryDTO dto, DictionaryDomain domain) {
	  if (dto == null) {
		 return null;
	  }
	  domain.setId(dto.getId());
	  domain.setCod(dto.getCod());
	  domain.setName(dto.getName());
	  domain.setDescription(dto.getDescription());
	  return domain;
   }

   protected <DictionaryDomain extends BaseDictionaryDomain, DictionaryDTO extends BaseDictionaryDTO> DictionaryDTO initBaseDictionaryDTO(
	   DictionaryDomain domain, DictionaryDTO dto) {
	  if (domain == null) {
		 return null;
	  }
	  dto.setId(domain.getId());
	  dto.setCod(domain.getCod());
	  dto.setName(domain.getName());
	  dto.setDescription(domain.getDescription());
	  return dto;
   }
}
