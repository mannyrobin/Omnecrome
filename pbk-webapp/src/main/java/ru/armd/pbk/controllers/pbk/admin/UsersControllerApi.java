package ru.armd.pbk.controllers.pbk.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.core.User;
import ru.armd.pbk.dto.core.UserDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.services.core.UserService;
import ru.armd.pbk.utils.json.JsonResult;

import javax.validation.Valid;
import java.util.List;

/**
 * Котроллер пользователей.
 */
@Controller
@RequestMapping(UsersControllerApi.RM_CONTROLLER_PATH)
public class UsersControllerApi
	extends BaseDomainControllerApi<User, UserDTO> {

   public static final String RM_PATH = "/admin/users";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_CURRENT_USER = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_USER','PBK_USERS')";
   static final String PERM_READ_USERS = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_USER')";
   static final String PERM_READ_EDIT_USERS = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_USER_EDIT')";


   @Autowired
   UsersControllerApi(UserService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_USERS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_CURRENT_USER);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_READ_EDIT_USERS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_READ_EDIT_USERS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_READ_EDIT_USERS);
	  this.auth.put(ControllerMethods.DELETE, PERM_READ_EDIT_USERS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

   /**
	* Меняет пароль пользователя.
	*
	* @param userId        идентификатор пользователя.
	* @param changePassDto dto пользователя с новым паролем внутри.
	* @return
	*/
   @RequestMapping(value = "{userId}/password", method = RequestMethod.PUT)
   @PreAuthorize("hasAnyRole('DEBUG_TO_REPLACE', 'ADMIN_USER_EDIT', 'PBK_USERS')")
   public ResponseEntity<?> changePassword(@PathVariable("userId") Long userId, @Valid @RequestBody UserDTO changePassDto) {
	  if (!testRole(changePassDto)) {
		 new ResponseEntity<>(HttpStatus.FORBIDDEN);
	  }
	  if (!userId.equals(changePassDto.getId())) {
		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	  }
	  if (changePassDto.getIsLdap()) {
		 throw new PBKException("Менять пароль можно только у внутреннего пользователя. Возможно вы забыли соранить изменения статуса пользователя.");
	  }
	  ((UserService) service).changePassword(changePassDto);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
	* Разблокировать пользователя.
	*
	* @param userId     индефикатор пользователя
	* @param unlockUser пользователь
	* @return
	*/
   @RequestMapping(value = "{userId}/unlock", method = RequestMethod.PUT)
   @PreAuthorize("hasAnyRole('DEBUG_TO_REPLACE','ADMIN_USER_EDIT')")
   public ResponseEntity<?> unlockUser(@PathVariable("userId") Long userId, @RequestBody UserDTO unlockUser) {
	  if (unlockUser.getIsLdap() != null && unlockUser.getIsLdap()) {
		 throw new PBKException("Разблокировать можно только внутреннего пользователя.");
	  }
	  if (!unlockUser.getIsActive()) {
		 throw new PBKException("Поле IsActive должно быть true");
	  }
	  if (!userId.equals(unlockUser.getId())) {
		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	  }
	  ((UserService) service).unlockUser(unlockUser);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   @RequestMapping(value = RM_BASE, method = RequestMethod.POST)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).ADD_DTO)")
   @Override
   public ResponseEntity<?> insertDTO(@Valid @RequestBody UserDTO dto)
	   throws PBKException {
	  dto.setIsLdap(false);
	  service.saveDTO(dto);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   @RequestMapping(value = RM_BASE, method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("hasAnyRole('DEBUG_TO_REPLACE', 'PBK_USERS')")
   @Override
   public ResponseEntity<?> updateDTO(@Valid @RequestBody UserDTO dto)
	   throws PBKException {
	  if (!testRole(dto)) {
		 new ResponseEntity<>(HttpStatus.FORBIDDEN);
	  }
	  service.saveDTO(dto);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   private boolean testRole(UserDTO userDto) {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  Object principal = authentication.getPrincipal();
	  UserDetails userDetails = (UserDetails) principal;
	  if (userDetails.getAuthorities().size() == 1 && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("PBK_USERS"))) {
		 return userDto.getLogin().equals(userDetails.getUsername());
	  }
	  return true;
   }

   /**
	* Получить список пользователей для выпадаюшего списка сотрудников.
	*
	* @param params фильтры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/employee-slist", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<ISelectItem> selItems = ((UserService) service).getSelectItemsForEmployee(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   @Override
   protected void customDeleteValidation(List<Long> ids)
	   throws PBKValidationException {
	  if (ids != null) {
		 for (Long id : ids) {
			UserDTO userDTO = service.getDTOById(id);
			if (userDTO != null) {
			   if (userDTO.getLogin().equals("ADMIN") || userDTO.getLogin().equals("SYSTEM")) {
				  throw new PBKValidationException("user id", "Удаление системных пользователей недоступно!");
			   }
			}
		 }
	  }
   }

}