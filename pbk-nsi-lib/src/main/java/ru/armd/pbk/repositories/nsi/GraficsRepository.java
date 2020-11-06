package ru.armd.pbk.repositories.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.Grafic;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.GraficsMapper;

@Repository
public class GraficsRepository
  extends BaseDomainRepository<Grafic>
{
  private GraficsMapper graficsMapper;
  
  @Autowired
  public GraficsRepository(GraficsMapper graficsMapper)
  {
    super(NsiAuditType.NSI_GRAFIC, graficsMapper);
    this.graficsMapper = graficsMapper;
  }
}
