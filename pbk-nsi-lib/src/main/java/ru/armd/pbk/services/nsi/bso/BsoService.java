package ru.armd.pbk.services.nsi.bso;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.core.Setting;
import ru.armd.pbk.domain.nsi.bso.Bso;
import ru.armd.pbk.domain.nsi.bso.BsoInfo;
import ru.armd.pbk.domain.nsi.bso.TrashBso;
import ru.armd.pbk.domain.nsi.bso.TrashInfoBso;
import ru.armd.pbk.domain.nsi.bsonumberrule.BsoNumberRule;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.dto.nsi.bso.BsoDTO;
import ru.armd.pbk.enums.core.BsoStates;
import ru.armd.pbk.enums.nsi.BsoType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.mappers.nsi.employee.EmployeeMapper;
import ru.armd.pbk.matcher.nsi.IBsoMatcher;
import ru.armd.pbk.repositories.core.SettingsRepository;
import ru.armd.pbk.repositories.nsi.bso.BsoRepository;
import ru.armd.pbk.repositories.nsi.bsonumberrule.BsoNumberRuleRepository;
import ru.armd.pbk.services.nsi.bsonumberrule.BsoNumberRulesService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Сервисы правил формирования номеров БСО.
 */
@Service
public class BsoService
	extends BaseDomainService<Bso, BsoDTO> {

   private static final Logger LOGGER = Logger.getLogger(BsoService.class);
   private static final String RESP_INV_PROP_NAME = "errors"; // Имя свойства,
   // содержащего
   // сообщение об
   // ошибке в
   // response.notValid;
   // принято
   // произвольным

   private BsoRepository bsoRepository;
   private BsoNumberRulesService bsoNumberRuleService;
   private BsoNumberRuleRepository bsoNumberRuleRepository;

   private EmployeeMapper employeeMapper;

   @Autowired
   private SettingsRepository settingsRepository;

   @Autowired
   BsoService(BsoRepository repository, BsoNumberRulesService bsoNumberRuleService,
			  BsoNumberRuleRepository bsoNumberRuleRepository, EmployeeMapper employeeMapper) {
	  super(repository);
	  this.bsoRepository = repository;
	  this.bsoNumberRuleService = bsoNumberRuleService;
	  this.bsoNumberRuleRepository = bsoNumberRuleRepository;
	  this.employeeMapper = employeeMapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Bso toDomain(BsoDTO dto) {
	  return IBsoMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public BsoDTO toDTO(Bso domain) {
	  return IBsoMatcher.INSTANCE.toDTO(domain);
   }

   /**
	* Получить список SelectItem состояний БСО (print, trash, use, bind).
	*
	* @return список SelectItem
	*/
   @Transactional
   public List<SelectItem> getStateSelectList() {
	  return BsoStates.getSelItems();
   }

   /**
	* Сгенерировать БСО типа bsoTypeId по правилам для подразделения
	* departmentId, в количестве count.
	*
	* @param departmentId id подразделения
	* @param bsoTypeId    id типа БСО
	* @param count        количество генерируемых БСО
	*/
   @Transactional
   public List<Bso> saveBulk(Long departmentId, Long bsoTypeId, Integer count) {
	  BsoNumberRule bsoNumberRule = bsoNumberRuleService.find(bsoTypeId, departmentId);
	  if (bsoNumberRule == null) {
		 throw new PBKValidationException(RESP_INV_PROP_NAME,
			 "Не найдено правило формирования номера БСО с данными типом БСО и подразделением.");
	  }
	  Setting bsoLengthSetting = settingsRepository.getByCode("BSO_MAX_LENGTH");
	  List<Bso> bsos = new ArrayList<Bso>();
	  for (int i = 0; i < count; i++) {
		 Long inc = bsoNumberRule.getIncrement() + 1;
		 bsoNumberRule.setIncrement(bsoNumberRule.getIncrement() + 1);
		 Integer bsoLength = Integer.valueOf(bsoLengthSetting.getValue());
		 if (inc.toString().length() > bsoLength) {
			bsoLengthSetting.setValue((++bsoLength).toString());
			bsoLengthSetting = settingsRepository.save(bsoLengthSetting);
			bsoLength = Integer.valueOf(bsoLengthSetting.getValue());
		 }
		 String bsoNumber = bsoNumberRuleService.getNextBsoNumber(bsoNumberRule, bsoLength);
		 bsos.add(toDomain(saveDTO(bsoNumberRule.getId(), bsoNumber)));
	  }
	  return bsos;
   }

   /**
	* Привязать БСО, указанные в bsoIds, к сотруднику, указанному в employeeId.
	*
	* @param bsoIds     id БСО
	* @param employeeId id сотрудника
	*/
   @Transactional
   public void bindBsos(List<Long> bsoIds, Long employeeId) {
	  if (!validateSameDepartment(bsoIds)) {
		 throw new PBKValidationException(RESP_INV_PROP_NAME,
			 "Выбранные БСО должны принадлежать одному подразделению.");
	  }
	  for (Long bsoId : bsoIds) {
		 if (bsoRepository.isBoundToAnother(bsoId, employeeId)) {
			throw new PBKValidationException(RESP_INV_PROP_NAME,
				"Среди выбранных БСО есть по крайней мере один, уже привязанный к другому пользователю.");
		 }
		 if (bsoRepository.isBoundToTheSame(bsoId, employeeId)) {
			throw new PBKValidationException(RESP_INV_PROP_NAME,
				"Среди выбранных БСО есть по крайней мере один, уже привязанный к выбранному пользователю. Невозможно привязать БСО повторно.");
		 }
	  }
	  for (Long bsoId : bsoIds) {
		 bsoRepository.bind(bsoId, employeeId);
	  }
   }

   /**
	* Отвязать БСО, указанные в ids, от сотрудников.
	*
	* @param ids id БСО
	*/
   @Transactional
   public void unbindBsos(List<Long> ids) {
	  for (Long id : ids) {
		 if (bsoRepository.isUsed(id)) {
			throw new PBKValidationException(RESP_INV_PROP_NAME,
				"Среди выбранных БСО есть по крайней мере один уже использованный БСО.");
		 }
	  }
	  for (Long id : ids) {
		 bsoRepository.unbind(id);
	  }
   }

   /**
	* Отправить в мусор БСО.
	*
	* @param trashBso БСО
	*/
   @Transactional
   public void trashBso(TrashBso trashBso) {
	   initCreaterInfo(trashBso);
	   initUpdaterInfo(trashBso);
	   Long taskId = bsoRepository.getTaskIdByBsoId(trashBso.getBsoId());
	   if (taskId != null) {
		   Employee employee = employeeMapper.getEmployeeByTaskId(taskId);
		   if (employee != null) {
				Long newBsoId = getEmployeeBso(employee.getDepartmentId(), employee.getHeadId());
				if (newBsoId != null) {
					bsoRepository.updateTaskBsoId(taskId, newBsoId);
					bsoRepository.updateUseState(newBsoId);
				} else {
					throw new PBKException("Не получилось привязать новый БСО к заданию во время отправки БСО в брак");
				}
		   }
	   }
	   bsoRepository.trashBso(trashBso);
   }

	private Long getEmployeeBso(Long deptId, Long employeeId) {
		Long bsoId = null;
		List<SelectItem> bsos = bsoRepository.getNonUsedBsoForEmployee(employeeId, BsoType.TASK.getId());
		if (bsos.size() == 0) {
			List<Bso> newBsos = null;
			newBsos = bsoRepository.getNonUsedBso(deptId, BsoType.TASK.getId(), 20);
			if (newBsos != null && newBsos.size() > 0) {
				newBsos = newBsos.subList(0, Math.min(10, newBsos.size()));
			}
			if (newBsos == null || newBsos.size() == 0) {
				newBsos = saveBulk(deptId, BsoType.TASK.getId(), 10);
			}
			for (Bso bso : newBsos) {
				bsoRepository.bind(bso.getId(), employeeId);
			}
			bsoId = newBsos.get(0).getId();
		} else {
			bsoId = bsos.get(0).getId();
		}
		return bsoId;
	}

   /**
	* Исправить БСО, указанные в ids.
	*
	* @param ids id БСО
	*/
//   @Transactional
//   public void fixBsos(List<Long> ids) {
//	  bsoRepository.fixBsos(ids);
//   }

   /**
	* Напечатать БСО, указанные в ids.
	*
	* @param ids id БСО
	*/
   @Transactional
   public void printBsos(List<Long> ids) {
	  bsoRepository.printBsos(ids);
   }

   /**
	* Использовать БСО, указанные в списке bsoIds, для задания, указанного в
	* taskId.
	*
	* @param bsoIds список id БСО
	* @param taskId id задания
	* @throws PBKValidationException
	*/
   @Transactional
   public void useBsos(List<Long> bsoIds, Long taskId)
	   throws PBKValidationException {
	  for (Long bsoId : bsoIds) {
		 if (bsoRepository.isUsed(bsoId)) {
			throw new PBKValidationException(RESP_INV_PROP_NAME,
				"Среди выбранных БСО есть по крайней мере один уже использованный БСО.");
		 }
	  }
	  for (Long bsoId : bsoIds) {
		 bsoRepository.use(bsoId, taskId);
	  }
   }

   /**
	* Отвязать использование БСО, указанных в списке bsoIds, от задания,
	* указанного в taskId.
	*
	* @param bsoIds список id БСО
	* @param taskId id задания
	* @throws PBKValidationException
	*/
   @Transactional
   public void disuseBsos(List<Long> bsoIds, Long taskId)
	   throws PBKValidationException {
	  for (Long bsoId : bsoIds) {
		 if (bsoRepository.isTrashed(bsoId)) {
			throw new PBKValidationException(RESP_INV_PROP_NAME,
				"Среди выбранных БСО есть по крайней мере один забракованный БСО.");
		 }
	  }
	  for (Long bsoId : bsoIds) {
		 bsoRepository.disuse(bsoId, taskId);
	  }
   }

   /**
	* Получить все неиспользованные БСО указанного сотрудника.
	*
	* @param params фильтры
	* @return
	*/
   public List<SelectItem> getSelectItemsForTask(BaseSelectListParams params) {
	  return bsoRepository.getSelectItemsForTask(params);
   }

	public String getBindDateById(Long bsoId) {
   	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
   	Date bsoDate = bsoRepository.getBindDateById(bsoId);
   		if (bsoDate == null)
   			return "Не выдавалось";
   		else
   			return sdf.format(bsoDate);
	}

	public TrashInfoBso getTrashBsoByBsoId(Long bsoId) {
		return bsoRepository.getTrashBsoByBsoId(bsoId);
	}

	public List<SelectItem> getSelectItemsForTaskCard(BaseSelectListParams params) {
		return bsoRepository.getSelectItemsForTaskCard(params);
	}


   /**
	* Получить все неиспользованные БСО указанного расписания сотрудника.
	*
	* @param taskId             id задания
	* @param employeeScheduleId id расписания сотрудника
	* @param currentId          id текущего
	* @return список БСО
	*/
   public List<SelectItem> getSelectItemsForSchedule(Long taskId, Long employeeScheduleId, Long currentId) {
	  return bsoRepository.getSelectItemsForSchedule(taskId, employeeScheduleId, currentId);
   }

   /**
	* Проверка того что все БСО из списка {@code bsoIds}
	* принадлежат одному подразделению.
	*
	* @param bsoIds список идентификаторов БСО.
	* @return
	*/
   public boolean validateSameDepartment(List<Long> bsoIds) {
	  if (bsoIds.isEmpty()) {
		 return true;
	  }
	  List<Long> bsoNumberRuleIds = new ArrayList<>();
	  for (Long bsoId : bsoIds) {
		 bsoNumberRuleIds.add(bsoRepository.getById(bsoId).getBsoNumberRuleId());
	  }
	  List<BsoNumberRule> bsoNumberRules = new ArrayList<>();
	  for (Long ruleId : bsoNumberRuleIds) {
		 bsoNumberRules.add(bsoNumberRuleRepository.getById(ruleId));
	  }
	  List<Long> departmentIds = new ArrayList<>();
	  for (BsoNumberRule rule : bsoNumberRules) {
		 departmentIds.add(rule.getDepartmentId());
	  }
	  long firstDepartmentId = departmentIds.get(0);
	  for (long departmentId : departmentIds) {
		 if (firstDepartmentId != departmentId) {
			return false;
		 }
	  }
	  return true;
   }

   private BsoDTO saveDTO(Long bsoNumberRuleId, String bsoNumber) {
	  BsoDTO bso = new BsoDTO();
	  bso.setBsoNumberRuleId(bsoNumberRuleId);
	  bso.setBsoNumber(bsoNumber);
	  bso.setIsPrinted(false);
	  bso.setIsTrashed(false);
	  bso.setIsBound(false);
	  bso.setIsUsed(false);
	  return super.saveDTO(bso);
   }

	@Transactional
	public List<BsoInfo> getBsoInfoForPusk() {
		return bsoRepository.getBsoInfoForPusk();
	}

}
