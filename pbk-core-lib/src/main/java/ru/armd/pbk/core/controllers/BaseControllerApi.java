package ru.armd.pbk.core.controllers;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.armd.pbk.authtoken.AuthenticationManager;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.utils.json.JsonResult;
import ru.armd.pbk.utils.json.JsonResultException;

import javax.servlet.http.HttpServletRequest;

/**
 * Базовый класс для работы с бизнес-логикой приложения.
 */
public abstract class BaseControllerApi
	extends BaseComponent {

   public static final Logger LOGGER = Logger.getLogger(BaseControllerApi.class);

   public static final String RM_API_PATH = "/pbk";

   public static final String ROWS_DEFAULT_VALUE = "10";
   public static final String PAGE_DEFAULT_VALUE = "1";
   public static final String SIDX_DEFAULT_VALUE = "2";
   public static final String SORD_DEFAULT_VALUE = "asc";
   private static final String ERROR_MESSAGE_ID = "ERROR_MESSAGE";

   /**
	* Возвращает логгер.
	*
	* @return
	*/
   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   ;

   /**
	* Общий хендлер ошибок. Пишет в лог и формирует json 500. Если нет
	* рут-прав, стектрейс ошибки будет обрезан.
	*
	* @param t       ошибка
	* @param request запрос
	* @return
	*/
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(Throwable.class)
   @ResponseBody
   public JsonResult handleException(Throwable t, HttpServletRequest request) {
	  getLogger().error(t.getMessage(), t);
	  if (AuthenticationManager.getUserInfo() != null) {
		 logAudit(AuditLevel.ERROR, AuditType.EXCEPTION, null, null, t.getMessage(), t);
	  }
	  JsonResultException ex = new JsonResultException(t);
	  // TODO Когда появятся другие роли заменить и раскоментарить.
	  // if (!AuthenticationManager.hasPermission("DEBUG_TO_REPLACE"))
	  // ex.setStack("");
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setExceptionInfo(ex);
	  return jsonResult;
   }

   @ExceptionHandler(DisabledException.class)
   @ResponseBody
   public ResponseEntity<?> handleDisableUserException(DisabledException error) {
	  String errorMessage = ". " + settingsMapper.getByCode(ERROR_MESSAGE_ID).getValue();
	  return createAuthenticationExceptionResponse(error, "Пользователь удалён" + errorMessage);
   }

   @ExceptionHandler(AccountExpiredException.class)
   @ResponseBody
   public ResponseEntity<?> handleExpiredException(AccountExpiredException error) {
	  String errorMessage = ". " + settingsMapper.getByCode(ERROR_MESSAGE_ID).getValue();
	  return createAuthenticationExceptionResponse(error, "Время доступа пользователя в систему истекло" + errorMessage);
   }

   @ExceptionHandler(LockedException.class)
   @ResponseBody
   public ResponseEntity<?> handleLockedUserException(LockedException error) {
	  String errorMessage = ". " + settingsMapper.getByCode(ERROR_MESSAGE_ID).getValue();
	  return createAuthenticationExceptionResponse(error, "Пользователь заблокирован" + errorMessage);
   }

   private ResponseEntity<?> createAuthenticationExceptionResponse(Exception error, String msg) {
	  JsonResultException ex = new JsonResultException(error);
	  ex.setMessage(msg);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setExceptionInfo(ex);
	  return new ResponseEntity<>(jsonResult, HttpStatus.UNAUTHORIZED);
   }

   /**
	* Хендлер ошибки валидации.
	*
	* @param error ошибка
	* @return джсон с кодом и текстом ошибки
	*/
   @ExceptionHandler(BadCredentialsException.class)
   @ResponseBody
   public ResponseEntity<?> handleAuthenticationException(BadCredentialsException error) {
	  String errorMessage = " " + settingsMapper.getByCode(ERROR_MESSAGE_ID).getValue();
	  return createAuthenticationExceptionResponse(error, "Неверный пользователь/пароль!");
   }

   @ExceptionHandler(PBKValidationException.class)
   @ResponseBody
   public ResponseEntity<?> handleValidationException(PBKValidationException error) {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.fillNotValid(error);
	  return new ResponseEntity<>(jsonResult, HttpStatus.BAD_REQUEST);
   }

   /**
	* Хендлер ошибки аутентификации.
	*
	* @param error ошибка
	* @return джсон с кодом 403
	*/
   @ExceptionHandler(AccessDeniedException.class)
   public ResponseEntity<?> handleAccessException(AccessDeniedException error) {
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.FORBIDDEN);
   }

   /**
	* Хендлер ошибки неправильной передачи параметров.
	*
	* @param error ошибка
	* @return
	*/
   @ExceptionHandler(MethodArgumentNotValidException.class)
   @ResponseBody
   public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
	  BindingResult result = error.getBindingResult();
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.fillNotValid(result);
	  return new ResponseEntity<>(jsonResult, HttpStatus.BAD_REQUEST);
   }
}
