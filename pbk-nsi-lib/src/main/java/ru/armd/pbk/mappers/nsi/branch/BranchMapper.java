package ru.armd.pbk.mappers.nsi.branch;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.branch.Branch;

/**
 * Маппер филиалов.
 */
@IsMapper
public interface BranchMapper
	extends IDomainMapper<Branch> {
}
