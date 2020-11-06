package ru.armd.pbk.mappers.nsi.department;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.domain.nsi.department.DepartmentPark;

/**
 * Маппер для работы с парками департаментов.
 */
@IsMapper
public interface DepartmentParkMapper
	extends IVersionDomainMapper<DepartmentPark> {

}
