package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDeviceOwnersHistoryService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.Dvr;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.dto.nsi.DvrDTO;
import ru.armd.pbk.matcher.nsi.IDvrMatcher;
import ru.armd.pbk.repositories.nsi.DvrRepository;

import java.util.List;

/**
 * Сервис видеорегистраторов.
 */
@Service
public class DvrService
	extends BaseDeviceOwnersHistoryService<Dvr, DvrDTO> {

   private static final Logger LOGGER = Logger.getLogger(DvrService.class);

   private DvrRepository dvrRepository;

   @Autowired
   DvrService(DvrRepository repository) {
	  super(repository);
	  this.dvrRepository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получение списка видеорегистраторов для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список видеорегистраторов.
	*/
   @Transactional
   public List<ISelectItem> getSelectItemsForEmployee(BaseSelectListParams params) {
	  List<ISelectItem> selectItemList = dvrRepository.getSelectItemsForEmployee(params);
	  return selectItemList;
   }

   @Override
   public Dvr toDomain(DvrDTO dto) {
	  return IDvrMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public DvrDTO toDTO(Dvr domain) {
	  return IDvrMatcher.INSTANCE.toDTO(domain);
   }

   /**
	* Получить пользователя по устройству.
	*
	* @param dvrId ИД
	* @return
	*/
   public Employee getEmployeeByDeviceId(Long dvrId) {
	  return dvrRepository.getEmployeeByDeviceId(dvrId);
   }
}
