package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Grafic;
import ru.armd.pbk.dto.nsi.GraficDTO;

@Mapper
public abstract interface IGraficMatcher
  extends IDomainMatcher<Grafic, GraficDTO>
{
  public static final IGraficMatcher INSTANCE = (IGraficMatcher)Mappers.getMapper(IGraficMatcher.class);
}
