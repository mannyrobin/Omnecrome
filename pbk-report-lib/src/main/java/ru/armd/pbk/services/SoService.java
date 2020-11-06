package ru.armd.pbk.services;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.repositories.SoRepository;

import java.util.Map;

public class SoService<Domain extends BaseDomain, DTO extends BaseDTO>
	extends BaseDomainService<Domain, DTO> {

   private SoRepository<Domain> soRepository;

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   public SoService(SoRepository<Domain> domainRepository) {
	  super(domainRepository);
	  this.soRepository = domainRepository;
   }

   /**
	* Получение значения Итого.
	*
	* @param <View> тип значения Итого.
	* @param params параметры.
	* @return значение итого.
	*/
   public <View extends BaseGridView> View getGridViewTotal(Map<String, Object> params) {
	  return soRepository.getGridViewTotal(params);
   }
}
