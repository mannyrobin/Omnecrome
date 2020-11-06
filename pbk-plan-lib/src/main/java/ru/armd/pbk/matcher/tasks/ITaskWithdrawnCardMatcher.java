package ru.armd.pbk.matcher.tasks;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.tasks.TaskWithdrawnCard;
import ru.armd.pbk.dto.tasks.TaskWithdrawnCardDTO;

/**
 * Маппер для сопоставления различных типов сущности "Карты к изъятию".
 */
@Mapper
public interface ITaskWithdrawnCardMatcher
	extends IDomainMatcher<TaskWithdrawnCard, TaskWithdrawnCardDTO> {

   ITaskWithdrawnCardMatcher INSTANCE = Mappers.getMapper(ITaskWithdrawnCardMatcher.class);

}
