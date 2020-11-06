package ru.armd.pbk.matcher.core;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.core.AuditType;
import ru.armd.pbk.dto.core.AuditTypeDTO;

/**
 * Мапер для сопостовления различных типов сущности "События Аудита".
 */
@Mapper
public interface IAuditTypeMatcher
	extends IDomainMatcher<AuditType, AuditTypeDTO> {

   IAuditTypeMatcher INSTANCE = Mappers.getMapper(IAuditTypeMatcher.class);

}