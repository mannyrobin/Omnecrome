package ru.armd.pbk.repositories.nsi.bso;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.bso.Bso;
import ru.armd.pbk.domain.nsi.bso.BsoInfo;
import ru.armd.pbk.domain.nsi.bso.TrashBso;
import ru.armd.pbk.domain.nsi.bso.TrashInfoBso;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.bso.BsoMapper;
import ru.armd.pbk.views.nsi.bso.BsoView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Репозиторий БСО.
 */
@Repository
public class BsoRepository
	extends BaseDomainRepository<Bso> {

   public static final Logger LOGGER = Logger.getLogger(BsoRepository.class);

   private BsoMapper bsoMapper;


   @Autowired
   BsoRepository(BsoMapper mapper) {
	  super(NsiAuditType.NSI_BSO, mapper);
	  this.bsoMapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Проверить, привязан ли БСО, указанный в id, к сотруднику, указанному в
	* employeeId.
	*
	* @param id         id БСО
	* @param employeeId id сотрудника
	* @return привязан ли БСО
	* @throws PBKException
	*/
   public boolean isBoundToAnother(Long id, Long employeeId) {
	  try {
		 boolean bound = bsoMapper.isBoundToAnother(id, employeeId);
		 return bound;
	  } catch (Exception e) {
		 String message = "Не удалось проверить привязку БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Проверить, привязан ли БСО, указанный в id, к сотруднику, указанному в
	* employeeId.
	*
	* @param id         id БСО
	* @param employeeId id сотрудника
	* @return привязан ли БСО
	* @throws PBKException
	*/
   public boolean isBoundToTheSame(Long id, Long employeeId) {
	  try {
		 boolean bound = bsoMapper.isBoundToTheSame(id, employeeId);
		 return bound;
	  } catch (Exception e) {
		 String message = "Не удалось проверить привязку БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Привязать БСО, указанный в bsoId, к сотруднику, указанному в employeeId.
	*
	* @param bsoId      id БСО
	* @param employeeId id сотрудника
	* @throws PBKException
	*/
   public void bind(Long bsoId, Long employeeId) {
	  try {
		 bsoMapper.bind(bsoId, employeeId);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null, "Успешная привязка БСО",
			 null);
		 bsoMapper.updateBindState(bsoId, true);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null,
			 "Успешное обновление статуса привязки БСО", null);
	  } catch (Exception e) {
		 String message = "Не удалось привязать БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Отвязать БСО, указанный в bsoId, от сотрудника.
	*
	* @param id id БСО
	* @throws PBKException
	*/
   public void unbind(Long id) {
	  try {
		 bsoMapper.unbind(id);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null, "Успешная отвязка БСО",
			 null);
		 bsoMapper.updateBindState(id, false);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null,
			 "Успешное обновление статуса привязки БСО", null);
	  } catch (Exception e) {
		 String message = "Не удалось отвязать БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Отправить в мусор БСО
	*
	* @param trashBso БСО
	*/
   public void trashBso(TrashBso trashBso) {
	  try {
		 bsoMapper.trashBso(trashBso);
		 bsoMapper.setIsTrash(Collections.singletonList(trashBso.getBsoId()));
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null,
			 "Успешная отправка в мусор БСО", null);
	  } catch (Exception e) {
		 String message = "Не удалось отправить в мусор БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Исправить БСО, указанные в ids.
	*
	* @param ids id БСО
	*/
//   public void fixBsos(List<Long> ids) {
//	  try {
//		 bsoMapper.fixBsos(ids);
//		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null, "БСО успешно исправлены",
//			 null);
//	  } catch (Exception e) {
//		 String message = "Не удалось исправить БСО. Ошибка: " + e.getMessage();
//		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
//		 throw new PBKException(message, e);
//	  }
//   }

   /**
	* Распечатать БСО, указанные в ids.
	*
	* @param ids id БСО
	*/
   public void printBsos(List<Long> ids) {
	  try {
		 bsoMapper.printBsos(ids);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null, "Успешная печать БСО",
			 null);
	  } catch (Exception e) {
		 String message = "Не удалось распечатать БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Проверить, использован ли БСО, указанный в id.
	*
	* @param id id БСО
	* @return использован ли БСО
	*/
   public boolean isUsed(Long id) {
	  try {
		 boolean used = bsoMapper.isUsed(id);
		 return used;
	  } catch (Exception e) {
		 String message = "Не удалось проверить использование БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Проверить, забраковано ли БСО, указанный в id.
	*
	* @param id id БСО
	* @return использован ли БСО
	*/
   public boolean isTrashed(Long id) {
	  try {
		 boolean used = bsoMapper.isTrashed(id);
		 return used;
	  } catch (Exception e) {
		 String message = "Не удалось проверить забракованность БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Использовать БСО, указанный в bsoId, для задания, указанного в taskId.
	*
	* @param bsoId  id БСО
	* @param taskId id задания
	* @throws PBKException
	*/
   public void use(Long bsoId, Long taskId) {
	  try {
		 bsoMapper.use(bsoId, taskId);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null,
			 "Успешное использование БСО", null);
		 bsoMapper.updateUseState(bsoId, true);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null,
			 "Успешное обновление статуса использования БСО", null);
	  } catch (Exception e) {
		 String message = "Не удалось использовать БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

	public void updateUseState(Long bsoId) {
		try {
			bsoMapper.updateUseState(bsoId, true);
			logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null,
					"Успешное обновление статуса использования БСО", null);
		} catch (Exception e) {
			String message = "Не удалось обновить статус БСО. Ошибка: " + e.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
			throw new PBKException(message, e);
		}
	}

   /**
	* Отвязать использование БСО, указанного в bsoId, от задания, указанного в
	* taskId.
	*
	* @param bsoId  id БСО
	* @param taskId id задания
	* @throws PBKException
	*/
   public void disuse(Long bsoId, Long taskId) {
	  try {
		 bsoMapper.disuse(bsoId, taskId);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null,
			 "Успешная отвязка использования БСО", null);
		 bsoMapper.updateUseState(bsoId, false);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null,
			 "Успешное обновление статуса использования БСО", null);
	  } catch (Exception e) {
		 String message = "Не удалось отвязать использование БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Получить все неиспользованные БСО указанного сотрудника.
	*
	* @param params фильтры
	* @return
	*/
   public List<SelectItem> getSelectItemsForTask(BaseSelectListParams params) {
	  try {
		 List<SelectItem> bsos = bsoMapper.getSelectItemsForTask(params.getFilter());
		 return bsos;
	  } catch (Exception e) {
		 String message = "Не удалось получить список неиспользованных БСО сотрудника. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

	public List<SelectItem> getSelectItemsForTaskCard(BaseSelectListParams params) {
		try {
			List<SelectItem> bsos = bsoMapper.getSelectItemsForTaskCard(params.getFilter());
			return bsos;
		} catch (Exception e) {
			String message = "Не удалось получить список неиспользованных БСО сотрудника. Ошибка: " + e.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
			throw new PBKException(message, e);
		}
	}

   /**
	* Получить все неиспользованные БСО указанного расписания сотрудника.
	*
	* @param taskId             - id задачи.
	* @param employeeScheduleId - id расписания сотрудника.
	* @param currentId          - id текущего
	* @return список БСО
	*/
   public List<SelectItem> getSelectItemsForSchedule(Long taskId, Long employeeScheduleId, Long currentId) {
	  try {
		 List<SelectItem> bsos = bsoMapper.getSelectItemsForSchedule(taskId, employeeScheduleId, currentId);
		 return bsos;
	  } catch (Exception e) {
		 String message = "Не удалось получить список неиспользованных БСО сотрудника. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Возвращает список использованных БСО для сотрудника указанного задания
	* (taskId в params).
	*
	* @param params параметры фильтрации
	* @return список использованных БСО
	*/
   public List<BsoView> getBsosUsedForTask(BaseGridViewParams params) {
	  List<BsoView> views = null;
	  try {
		 views = bsoMapper.getBsosUsedForTask(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список использованных БСО. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return views;
   }


	public List<SelectItem> getNonUsedBsoForEmployee(Long employeeId, Long bsoType) {
		try {
			List<SelectItem> bsos = bsoMapper.getNonUsedBsoForEmployee(employeeId, bsoType);
			return bsos;
		} catch (Exception e) {
			String message = "Не удалось получить неиспользованное БСО сотрудника. Ошибка: " + e.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
			throw new PBKException(message, e);
		}
	}

	public List<Bso> getNonUsedBso(Long deptId, Long bsoTypeId, Integer count) {
		try {
			List<Bso> bsos = bsoMapper.getNonUsedBso(deptId, bsoTypeId, count);
			return bsos;
		} catch (Exception e) {
			String message = "Не удалось получить неиспользованные БСО сотрудника. Ошибка: " + e.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
			throw new PBKException(message, e);
		}
	}


	public Date getBindDateById(Long bsoId) {
		Date bsoDate = null;
		try {
			bsoDate = bsoMapper.getBindDateById(bsoId);
		} catch (Exception e) {
			String message = "Не удалось получить дату привязки БСО. Ошибка: " + e.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, bsoId, message, e);
			throw new PBKException(message, e);
		}
		return bsoDate;
	}

	public TrashInfoBso getTrashBsoByBsoId(Long bsoId) {
		TrashInfoBso bso = null;
		try {
			bso = bsoMapper.getTrashBsoByBsoId(bsoId);
		} catch (Exception e) {
			String message = "Не удалось получить бракованное БСО. Ошибка: " + e.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, bsoId, message, e);
			throw new PBKException(message, e);
		}
		return bso;
	}

	public Long getTaskIdByBsoId(Long bsoId) {
		Long taskId = null;
		try {
			taskId = bsoMapper.getTaskIdByBsoId(bsoId);
		} catch (Exception e) {
			String message = "Не удалось получить номер задания БСО. Ошибка: " + e.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, bsoId, message, e);
			throw new PBKException(message, e);
		}
		return taskId;
	}

	public void updateTaskBsoId(Long taskId, Long bsoId) {
		try {
			bsoMapper.updateTaskBsoId(taskId, bsoId);
		} catch (Exception e) {
			String message = "Не удалось обновить БСО у НЗ. Ошибка: " + e.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, bsoId, message, e);
			throw new PBKException(message, e);
		}
	}

   /**
	* Отвязать все не использованые БСО от сотрудника.
	*
	* @param employeeId индефикатор сотрудника
	*/
   public void unbindNonUseBsosForEmployee(Long employeeId) {
	  List<Long> ids = new ArrayList<Long>();
	  for (SelectItem selectItem : bsoMapper.getNonUsedBsoForEmployee(employeeId, null)) {
		 ids.add(selectItem.getId());
	  }
	  if (ids.size() > 0) {
		 bsoMapper.unbinds(ids);
		 bsoMapper.updateBindsState(ids, false);
	  }
   }

   @Override
   @DepartmentAuthorization
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  return super.getGridViews(params);
   }

	public List<BsoInfo> getBsoInfoForPusk() {
		return bsoMapper.getBsoInfoForPusk();
	}
}
