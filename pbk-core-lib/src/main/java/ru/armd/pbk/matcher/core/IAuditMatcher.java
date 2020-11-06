package ru.armd.pbk.matcher.core;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.core.Audit;
import ru.armd.pbk.dto.core.AuditDTO;

/**
 * Матчер для объектов аудита.
 */
@Mapper
public interface IAuditMatcher
	extends IDomainMatcher<Audit, AuditDTO> {

   IAuditMatcher INSTANCE = Mappers.getMapper(IAuditMatcher.class);

}