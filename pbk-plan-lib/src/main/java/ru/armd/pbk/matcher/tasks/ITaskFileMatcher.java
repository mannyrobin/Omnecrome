package ru.armd.pbk.matcher.tasks;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.tasks.TaskFile;
import ru.armd.pbk.dto.tasks.TaskFileDTO;

/**
 * Сопоставление сущностей файлов заданий.
 */
@Mapper
public interface ITaskFileMatcher
	extends IDomainMatcher<TaskFile, TaskFileDTO> {

   ITaskFileMatcher INSTANCE = Mappers.getMapper(ITaskFileMatcher.class);

}
