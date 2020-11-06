package ru.armd.pbk.matcher.core;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.core.Module;
import ru.armd.pbk.dto.core.ModuleDTO;

@Mapper
public interface IModuleMatcher
	extends IDomainMatcher<Module, ModuleDTO> {

   IModuleMatcher INSTANCE = Mappers.getMapper(IModuleMatcher.class);

}
