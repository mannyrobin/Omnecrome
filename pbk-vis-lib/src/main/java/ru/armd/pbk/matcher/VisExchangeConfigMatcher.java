package ru.armd.pbk.matcher;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.dto.viss.VisExchangeConfigDTO;

/**
 * Сопоставление сущностей конфигураций взаимодействия с ВИС.
 */
@Mapper
public abstract class VisExchangeConfigMatcher
	implements IDomainMatcher<VisExchangeConfig, VisExchangeConfigDTO> {

   public static final VisExchangeConfigMatcher INSTANCE = Mappers.getMapper(VisExchangeConfigMatcher.class);

   @Override
   @Mapping(target = "confirmPassword", source = "password")
   public abstract VisExchangeConfigDTO toDTO(VisExchangeConfig domain);
}
