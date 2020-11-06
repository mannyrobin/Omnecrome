package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.Ticket;
import ru.armd.pbk.dto.nsi.TicketDTO;
import ru.armd.pbk.services.nsi.TicketService;

/**
 * rest контроллер для работы с билетами.
 *
 * @author nikita_lobanov
 */
@Controller
@RequestMapping(TicketsControllerApi.RM_CONTROLLER_PATH)
public class TicketsControllerApi
	extends BaseVersionControllerApi<Ticket, TicketDTO> {

   public static final String RM_PATH = "/nsi/tickets";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_TICKETS = "hasAnyRole('DEBUG_TO_REPLACE','TICKETS')";
   static final String PERM_EDIT_TICKETS = "hasAnyRole('DEBUG_TO_REPLACE','TICKETS_EDIT')";

   @Autowired
   TicketsControllerApi(TicketService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_TICKETS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_TICKETS);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_TICKETS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_TICKETS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_TICKETS);
	  this.auth.put(ControllerMethods.DELETE, PERM_EDIT_TICKETS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_TICKETS);
   }
}

