package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDeviceOwnersHistoryService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.Pusk;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.dto.nsi.PuskDTO;
import ru.armd.pbk.matcher.nsi.IPuskMatcher;
import ru.armd.pbk.repositories.nsi.PuskRepository;

import java.util.List;

/**
 * Сервис ПУсКов.
 */
@Service
public class PuskService
	extends BaseDeviceOwnersHistoryService<Pusk, PuskDTO> {

   private static final Logger LOGGER = Logger.getLogger(PuskService.class);

   private PuskRepository puskRepository;

   @Autowired
   PuskService(PuskRepository repository) {
	  super(repository);
	  this.puskRepository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Pusk toDomain(PuskDTO dto) {
	  return IPuskMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public PuskDTO toDTO(Pusk domain) {
	  return IPuskMatcher.INSTANCE.toDTO(domain);
   }

   /**
	* Получение списка ПУсКов для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список ПУсКов.
	*/
   @Transactional
   public List<ISelectItem> getSelectItemsForEmployee(BaseSelectListParams params) {
	  List<ISelectItem> selectItemList = puskRepository.getSelectItemsForEmployee(params);
	  return selectItemList;
   }


   /**
	* Получить пользователя по устройству.
	*
	* @param puskId ИД
	* @return
	*/
   public Employee getEmployeeByDeviceId(Long puskId) {
	  return puskRepository.getEmployeeByDeviceId(puskId);
   }

}
