package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDeviceOwnersHistoryRepository;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.OfficialCard;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.OfficialCardMapper;
import ru.armd.pbk.repositories.nsi.employee.EmployeeRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий ССК.
 */
@Repository
public class OfficialCardRepository
	extends BaseDeviceOwnersHistoryRepository<OfficialCard> {

   public static final Logger LOGGER = Logger.getLogger(OfficialCardRepository.class);

   private OfficialCardMapper officialCardMapper;

   @Autowired
   private EmployeeRepository employeeRepository;

   @Autowired
   OfficialCardRepository(OfficialCardMapper mapper) {
	  super(NsiAuditType.NSI_OFFICILA_CARD, mapper);
	  this.officialCardMapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получение списка ССК для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список ССК.
	*/
   public List<ISelectItem> getSelectItemsForEmployee(BaseSelectListParams params) {
	  List<ISelectItem> sList = null;
	  try {
		 sList = officialCardMapper.getSelectItemsForEmployee(params.getFilter());
		 sList.add(0, new SelectItem(null, "Не выбрано"));
	  } catch (Exception e) {
		 String message = "Не удалось получить список СКК. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   @Override
   public int deleteSoft(List<Long> ids, Boolean tryDelete) {
	  int result = super.deleteSoft(ids, tryDelete);
	  Map<String, Object> params = new HashMap<String, Object>();
	  for (Long id : ids) {
		 params.put("offId", id);
		 Employee employee = employeeRepository.getDomain(params);
		 if (employee != null) {
			employee.setOffCardId(null);
			employee.setVersionStartDate(new Date());
			employeeRepository.saveVersion(employee);
		 }
	  }
	  return result;
   }

   /**
	* Получить пользователя по устройству.
	*
	* @param offId ИД
	* @return
	*/
   public Employee getEmployeeByDeviceId(Long offId) {
	  return employeeRepository.getEmployeeByDeviceId(null, null, offId, null);
   }
}
