package ru.armd.pbk.jobweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.armd.pbk.dto.pusk.AuthResponse;
import ru.armd.pbk.service.pusk.PuskIntegrationService;

import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping( {"*"})
public class JobWebAppMainController {

	@Autowired
    private PuskIntegrationService puskIntegrationService;

   @RequestMapping(method = RequestMethod.GET)
   public String getMainPage(Map<String, Object> model) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  String login = auth != null ? auth.getName() : "unautorized";
	  model.put("login", login);
	  return "index";
   }
}
