package ru.armd.pbk.services.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.Grafic;
import ru.armd.pbk.dto.nsi.GraficDTO;
import ru.armd.pbk.matcher.nsi.IGraficMatcher;
import ru.armd.pbk.repositories.nsi.GraficsRepository;

@Service
public class GraficsService
  extends BaseDomainService<Grafic, GraficDTO>
{
  private GraficsRepository graficsRepository;
  
  @Autowired
  public GraficsService(GraficsRepository graficsRepository)
  {
    super(graficsRepository);
    this.graficsRepository = graficsRepository;
  }
  
  public GraficDTO toDTO(Grafic domain)
  {
    return (GraficDTO)IGraficMatcher.INSTANCE.toDTO(domain);
  }
  
  public Grafic toDomain(GraficDTO dto)
  {
    return (Grafic)IGraficMatcher.INSTANCE.toDomain(dto);
  }
}
