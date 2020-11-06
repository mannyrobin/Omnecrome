package ru.armd.pbk.controllers.pbk.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.FileStream;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.tasks.TaskFile;
import ru.armd.pbk.dto.tasks.TaskFileDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.tasks.TaskFileService;
import ru.armd.pbk.utils.AttachFileUtils;
import ru.armd.pbk.utils.json.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * rest контроллер для работы с файлами задания.
 */
@Controller
@RequestMapping(TaskFileControllerApi.RM_CONTROLLER_PATH)
public class TaskFileControllerApi
	extends BaseDomainControllerApi<TaskFile, TaskFileDTO> {

   public static final String RM_PATH = "/pbk/task-files";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   public static final String TASK_FILE_ATTACH = "/{taskId}/file";
   public static final String TASK_FILE_REMOVE = "/{taskId}/file/delete";

   static final String PERM_READ_TASKS = "hasAnyRole('DEBUG_TO_REPLACE','PBK_TASKS')";
   static final String PERM_READ_EDIT_TASKS = "hasAnyRole('DEBUG_TO_REPLACE','PBK_TASKS_EDIT')";

   TaskFileService taskFileService;

   @Autowired
   TaskFileControllerApi(TaskFileService service) {
	  this.service = service;
	  this.taskFileService = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_TASKS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_TASKS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_READ_EDIT_TASKS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_READ_EDIT_TASKS);
	  this.auth.put(ControllerMethods.DELETE, PERM_READ_EDIT_TASKS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

   /**
	* Добавляет файл к заданию.
	*
	* @param file   - добавляемый файл
	* @param taskId - id задания
	* @return
	*/
   @RequestMapping(value = TASK_FILE_ATTACH, method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.OK)
   @PreAuthorize(PERM_READ_EDIT_TASKS)
   public ResponseEntity<?> attachFileToTask(
	   @Valid @ModelAttribute("file") TaskFileDTO file,
	   @PathVariable("taskId") Long taskId) {
	  file.setTaskId(taskId);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(taskFileService.createTaskFile(file));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }


   /**
	* Получает файл.
	*
	* @param streamId ИД потока.
	* @param request  запрос.
	* @param response ответ.
	*/
   @ResponseBody
   @RequestMapping(value = "/stream/{streamId}", method = RequestMethod.GET)
   @PreAuthorize(PERM_READ_TASKS)
   public void getFile(@PathVariable(value = "streamId") String streamId,
					   HttpServletRequest request,
					   HttpServletResponse response) {

	  FileStream fs = ((TaskFileService) service).getFileStream(streamId);
	  ByteArrayInputStream bais = new ByteArrayInputStream(fs.getStream());
	  AttachFileUtils.writeStreamToResponse(response, bais, fs.getName(), "application/octet-stream", false);
   }

   /**
	* Получает файл.
	*
	* @param streamId ИД потока.
	* @param request  запрос.
	* @param response ответ.
	*/
   @ResponseBody
   @RequestMapping(value = "/view/{streamId}", method = RequestMethod.GET)
   @PreAuthorize(PERM_READ_TASKS)
   public byte[] getViewFile(@PathVariable(value = "streamId") String streamId,
							 HttpServletRequest request,
							 HttpServletResponse response) {

	  FileStream fs = ((TaskFileService) service).getFileStream(streamId);
	  AttachFileUtils.attachBytesToResponse(fs.getName(), request, response);
	  return fs.getStream();
   }

   /**
	* Удалить объекты по stream_id.
	*
	* @param ids список id.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = TASK_FILE_REMOVE, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(PERM_READ_EDIT_TASKS)
   public ResponseEntity<?> deleteFiles(@RequestParam("ids") List<String> ids)
	   throws PBKException {
	  taskFileService.deleteFiles(ids);
	  return new ResponseEntity<>(HttpStatus.OK);
   }
}
