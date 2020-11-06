package ru.armd.pbk.controllers.pbk.nsi;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.Grafic;
import ru.armd.pbk.dto.nsi.GraficDTO;
import ru.armd.pbk.services.nsi.GraficsService;

@RestController
@RequestMapping({"/pbk/nsi/grafics"})
public class GraficsControllerApi
  extends BaseDomainControllerApi<Grafic, GraficDTO>
{
  private static final String PERM_READ_GRAFICS = "hasAnyRole('DEBUG_TO_REPLACE','VENUES')";
  private static final String PERM_EDIT_GRAFICS = "hasAnyRole('DEBUG_TO_REPLACE','VENUES_EDIT')";
  
  @Autowired
  public GraficsControllerApi(GraficsService service)
  {
    this.service = service;
    this.auth.put(ControllerMethods.GET_VIEWS, "hasAnyRole('DEBUG_TO_REPLACE','VENUES')");
    this.auth.put(ControllerMethods.GET_DTO, "hasAnyRole('DEBUG_TO_REPLACE','VENUES')");
    this.auth.put(ControllerMethods.EDIT_DTO, "hasAnyRole('DEBUG_TO_REPLACE','VENUES_EDIT')");
    this.auth.put(ControllerMethods.ADD_DTO, "hasAnyRole('DEBUG_TO_REPLACE','VENUES_EDIT')");
    this.auth.put(ControllerMethods.DELETE, "hasAnyRole('DEBUG_TO_REPLACE','VENUES_EDIT')");
  }
}
