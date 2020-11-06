package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDeviceOwnersHistoryService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.ContactlessCard;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.dto.nsi.ContactlessCardDTO;
import ru.armd.pbk.matcher.nsi.IContactlessCardMatcher;
import ru.armd.pbk.repositories.nsi.ContactlessCardRepository;

import java.util.List;

/**
 * Сервис БСК.
 */
@Service
public class ContactlessCardService
	extends BaseDeviceOwnersHistoryService<ContactlessCard, ContactlessCardDTO> {

   private static final Logger LOGGER = Logger.getLogger(ContactlessCardService.class);

   private ContactlessCardRepository contactlessCardRepository;

   @Autowired
   ContactlessCardService(ContactlessCardRepository repository) {
	  super(repository);
	  this.contactlessCardRepository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public ContactlessCard toDomain(ContactlessCardDTO dto) {
	  return IContactlessCardMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public ContactlessCardDTO toDTO(ContactlessCard domain) {
	  return IContactlessCardMatcher.INSTANCE.toDTO(domain);
   }

   /**
	* Получение списка БСК для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список БСК.
	*/
   @Transactional
   public List<ISelectItem> getSelectItemsForEmployee(BaseSelectListParams params) {
	  List<ISelectItem> selectItemList = contactlessCardRepository.getSelectItemsForEmployee(params);
	  return selectItemList;
   }


   /**
	* Получить пользователя по устройству.
	*
	* @param contId ИД
	* @return
	*/
   public Employee getEmployeeByDeviceId(Long contId) {
	  return contactlessCardRepository.getEmployeeByDeviceId(contId);
   }
}
