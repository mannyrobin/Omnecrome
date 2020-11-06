package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.ShiftDepartment;
import ru.armd.pbk.dto.nsi.ShiftDepartmentDTO;

/**
 * Маппер сопоставления различных типов сущности "Смена подразделения".
 *
 * @author alexander_martynov
 */
@Mapper
public interface IShiftDepartmentMatcher
	extends IDomainMatcher<ShiftDepartment, ShiftDepartmentDTO> {

   IShiftDepartmentMatcher INSTANCE = Mappers.getMapper(IShiftDepartmentMatcher.class);

}
