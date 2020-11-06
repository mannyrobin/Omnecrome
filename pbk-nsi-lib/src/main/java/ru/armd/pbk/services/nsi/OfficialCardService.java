package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDeviceOwnersHistoryService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.OfficialCard;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.dto.nsi.OfficialCardDTO;
import ru.armd.pbk.matcher.nsi.IOfficialCardMatcher;
import ru.armd.pbk.repositories.nsi.OfficialCardRepository;

import java.util.List;

/**
 * Сервис ССК.
 */
@Service
public class OfficialCardService
	extends BaseDeviceOwnersHistoryService<OfficialCard, OfficialCardDTO> {

   private static final Logger LOGGER = Logger.getLogger(OfficialCardService.class);

   private OfficialCardRepository officialCardRepository;

   @Autowired
   OfficialCardService(OfficialCardRepository repository) {
	  super(repository);
	  this.officialCardRepository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public OfficialCard toDomain(OfficialCardDTO dto) {
	  return IOfficialCardMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public OfficialCardDTO toDTO(OfficialCard domain) {
	  return IOfficialCardMatcher.INSTANCE.toDTO(domain);
		/*OfficialCardDTO dto = new OfficialCardDTO();
		dto.setCardNumber(domain.getCardNumber());
		dto.setId(domain.getId());
		//dto.setIsDelete(domain.getIsDelete());
		dto.setHeadId(domain.getHeadId());
		return dto;*/
   }

   /**
	* Получение списка ССК для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список ССК.
	*/
   @Transactional
   public List<ISelectItem> getSelectItemsForEmployee(BaseSelectListParams params) {
	  List<ISelectItem> selectItemList = officialCardRepository.getSelectItemsForEmployee(params);
	  return selectItemList;
   }

   /**
	* Получить пользователя по устройству.
	*
	* @param offId ИД
	* @return
	*/
   public Employee getEmployeeByDeviceId(Long offId) {
	  return officialCardRepository.getEmployeeByDeviceId(offId);
   }
}
