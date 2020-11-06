package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.branch.Branch;
import ru.armd.pbk.dto.nsi.branch.BranchDTO;

/**
 * Маппер для сопоставления различных типов сущности "филиал".
 */
@Mapper
public interface IBranchMatcher
	extends IDomainMatcher<Branch, BranchDTO> {

   IBranchMatcher INSTANCE = Mappers.getMapper(IBranchMatcher.class);
}
