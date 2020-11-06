package ru.armd.pbk.mappers.tasks;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.tasks.TaskFile;

/**
 * Маппер файла.
 */
@IsMapper
public interface TaskFileMapper
	extends IDomainMapper<TaskFile> {

   /**
	* Обновить скан задания.
	*
	* @param taskId задание
	* @param fileId файл
	*/
   void updateTaskScan(@Param("taskId") Long taskId, @Param("fileId") Long fileId);

}
