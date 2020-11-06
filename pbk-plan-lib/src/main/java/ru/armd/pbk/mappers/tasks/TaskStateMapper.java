package ru.armd.pbk.mappers.tasks;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.tasks.TaskState;

import java.util.List;

/**
 * Маппер состояний заданий.
 */
@IsMapper
public interface TaskStateMapper
	extends IDomainMapper<TaskState> {

   /**
	* Получить список статусов заданий.
	*
	* @param taskIds      задания.
	* @param taskIdsCount количество заданий.
	* @return
	*/
   List<SelectItem> getSelectListGroup(@Param("taskIds") List<Long> taskIds, @Param("taskIdsCount") int taskIdsCount);

}
