package ru.armd.pbk.utils.json;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.armd.pbk.exceptions.PBKValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс сданными результата выполнения операции.
 */
public class JsonResult {

   Object result;

   Map<String, List<String>> notValid;

   JsonResultException exceptionInfo;

   /**
	* Инициализация.
	*
	* @param validResult Результат валидации.
	*/
   public void fillNotValid(BindingResult validResult) {
	  notValid = new HashMap<String, List<String>>();
	  List<ObjectError> errors = validResult.getAllErrors();
	  for (int it = 0; it < errors.size(); it++) {
		 ObjectError e = errors.get(it);
		 String key = (e instanceof FieldError) ? ((FieldError) e).getField() : e.getObjectName();
		 if (!notValid.containsKey(key)) {
			notValid.put(key, new ArrayList<String>());
		 }
		 notValid.get(key).add(e.getDefaultMessage());
	  }
   }

   /**
	* Инициализация.
	*
	* @param error Исключение валидации.
	*/
   public void fillNotValid(PBKValidationException error) {
	  notValid = new HashMap<String, List<String>>();
	  List<String> errors = new ArrayList<>();
	  errors.add(error.getMessage());
	  notValid.put(error.getName(), errors);
   }

   public Object getResult() {
	  return result;
   }

   public void setResult(Object result) {
	  this.result = result;
   }

   public Map<String, List<String>> getNotValid() {
	  return notValid;
   }

   public void setNotValid(Map<String, List<String>> notValid) {
	  this.notValid = notValid;
   }

   public JsonResultException getExceptionInfo() {
	  return exceptionInfo;
   }

   public void setExceptionInfo(JsonResultException exceptionInfo) {
	  this.exceptionInfo = exceptionInfo;
   }
}
