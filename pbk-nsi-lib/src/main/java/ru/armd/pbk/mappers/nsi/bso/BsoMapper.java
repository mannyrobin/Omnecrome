package ru.armd.pbk.mappers.nsi.bso;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.bso.Bso;
import ru.armd.pbk.domain.nsi.bso.BsoInfo;
import ru.armd.pbk.domain.nsi.bso.TrashBso;
import ru.armd.pbk.domain.nsi.bso.TrashInfoBso;
import ru.armd.pbk.views.nsi.bso.BsoView;

import java.util.*;

/**
 * Маппер БСО.
 */
@IsMapper
public interface BsoMapper
	extends IDomainMapper<Bso> {

   /**
	* Проверить, привязан ли БСО, указанный в id, к сотруднику, указанному в
	* employeeId.
	*
	* @param id         id БСО
	* @param employeeId id сотрудника
	* @return привязан ли БСО
	* @throws PBKException
	*/
   boolean isBoundToAnother(@Param("id") Long id, @Param("employeeId") Long employeeId);

   /**
	* Проверить, привязан ли БСО, указанный в id, к сотруднику, указанному в
	* employeeId.
	*
	* @param id         id БСО
	* @param employeeId id сотрудника
	* @return привязан ли БСО
	* @throws PBKException
	*/
   boolean isBoundToTheSame(@Param("id") Long id, @Param("employeeId") Long employeeId);

   /**
	* Привязать БСО, указанный в bsoId, к сотруднику, указанному в employeeId.
	*
	* @param bsoId      id БСО
	* @param employeeId id сотрудника
	* @throws PBKException
	*/
   void bind(@Param("bsoId") Long bsoId, @Param("employeeId") Long employeeId);

   /**
	* Привязать БСО к заданиям.
	*
	* @param tasks задания.
	*/
   void binds(@Param("tasks") List<SelectItem> tasks);

   /**
	* Отвязать БСО, указанный в bsoId, от всех сотрудников.
	*
	* @param id id БСО
	* @throws PBKException
	*/
   void unbind(Long id);

   /**
	* Отвязать БСО от всех сотрудников.
	*
	* @param ids индефикаторы БСО
	* @throws PBKException
	*/
   void unbinds(List<Long> ids);

   /**
	* Обновить состояние флага "Закреплен" для БСО.
	*
	* @param id    id БСО
	* @param bound состояние флага "Закреплен"
	*/
   void updateBindState(@Param("id") Long id, @Param("bound") Boolean bound);

   /**
	* Обновить состояние флага "Закреплен" для БСО.
	*
	* @param ids   идефикаторы БСО
	* @param bound состояние флага "Закреплен"
	*/
   void updateBindsState(@Param("ids") List<Long> ids, @Param("bound") Boolean bound);

   /**
	* Отправить в мусор БСО, указанные в ids.
	*
	* @param ids id БСО
	*/
   void setIsTrash(List<Long> ids);

   /**
	* Исправить БСО, указанные в ids.
	*
	* @param ids id БСО
	*/
   //void fixBsos(List<Long> ids);

   /**
	* Напечатать БСО, указанные в ids.
	*
	* @param ids id БСО
	*/
   void printBsos(List<Long> ids);

   /**
	* Проверить, использован ли БСО, указанный в id.
	*
	* @param id id БСО
	* @return использован ли БСО
	*/
   boolean isUsed(Long id);

   /**
	* Проверить, забраковано ли БСО, указанный в id.
	*
	* @param id id БСО
	* @return использован ли БСО
	*/
   boolean isTrashed(Long id);

   /**
	* Привязать БСО, указанный в bsoId, к заданию, указанному в taskId.
	*
	* @param bsoId  id БСО
	* @param taskId id задания
	*/
   void use(@Param("bsoId") Long bsoId, @Param("taskId") Long taskId);

   /**
	* Для БСО с ИД из {@code bsoIds} выставить признак использования
	* в true. Т.е привязать БСО к заданиям.
	*
	* @param bsoIds ИД - БСО.
	*/
   void uses(@Param("bsoIds") Collection<Long> bsoIds);

   /**
	* Отвязать БСО, указанный в bsoId, от задания, указанного в taskId.
	*
	* @param bsoId  id БСО
	* @param taskId id задания
	*/
   void disuse(@Param("bsoId") Long bsoId, @Param("taskId") Long taskId);

   /**
	* Обновить состояние флага "Использован" для БСО.
	*
	* @param id   id БСО
	* @param used состояние флага "Использован"
	*/
   void updateUseState(@Param("id") Long id, @Param("used") Boolean used);

   /**
	* Обновить состояние флага "Использован" для БСО.
	*
	* @param ids  индефикаторы БСО
	* @param used состояние флага "Использован"
	*/
   void updateUsesState(@Param("ids") List<Long> ids, @Param("used") Boolean used);

   /**
	* Получить все неиспользованные БСО указанного сотрудника.
	*
	* @param params фильтры
	* @return
	*/
   List<SelectItem> getSelectItemsForTask(Map<String, Object> params);

   List<SelectItem> getSelectItemsForTaskCard(Map<String, Object> params);


   /**
	* Получить все неиспользованные БСО типа {@code bsoTypeId}
	* указанного сотрудника {@code employeeId}.
	*
	* @param employeeId ИД сотрудника.
	* @param bsoTypeId  тип БСО.
	* @return список БСО
	*/
   List<SelectItem> getNonUsedBsoForEmployee(@Param("employeeId") Long employeeId, @Param("bsoTypeId") Long bsoTypeId);

   /**
	* Получить все неиспользованные БСО типа {@code bsoTypeId}
	* из списка указанных сотрудников {@code employeeIds}.
	*
	* @param employeeIds ИД сотрудника.
	* @param bsoTypeId   тип БСО.
	* @return список БСО
	*/
   List<SelectItem> getNonUsedBsoForEmployees(@Param("employeeIds") Set<Long> employeeIds,
											  @Param("bsoTypeId") Long bsoTypeId);

   /**
	* Получить все неиспользованные БСО указанного расписания сотрудника.
	*
	* @param taskId             - id задания.
	* @param employeeScheduleId id расписания сотрудника.
	* @param currentId          id текущего.
	* @return список БСО
	*/
   List<SelectItem> getSelectItemsForSchedule(@Param("taskId") Long taskId,
											  @Param("employeeScheduleId") Long employeeScheduleId, @Param("currentId") Long currentId);

   /**
	* Возвращает список использованных БСО для указанного задания (taskId в
	* params).
	*
	* @param params параметры фильтрации
	* @return список использованных БСО
	*/
   List<BsoView> getBsosUsedForTask(BaseGridViewParams params);

   /**
	* Получить не использованные БСО.
	*
	* @param deptId    подразделение.
	* @param bsoTypeId тип БСО.
	* @param count     количество.
	* @return
	*/
   List<Bso> getNonUsedBso(@Param("deptId") Long deptId, @Param("bsoTypeId") Long bsoTypeId,
						   @Param("count") Integer count);

   Date getBindDateById(@Param("bsoId") Long bsoId);

   void trashBso(@Param("trashBso") TrashBso trashBso);

   Long getTaskIdByBsoId(@Param("bsoId") Long bsoId);

   void updateTaskBsoId(@Param("taskId") Long taskId, @Param("bsoId") Long bsoId);

   TrashInfoBso getTrashBsoByBsoId(@Param("bsoId") Long bsoId);

	List<BsoInfo> getBsoInfoForPusk();

}