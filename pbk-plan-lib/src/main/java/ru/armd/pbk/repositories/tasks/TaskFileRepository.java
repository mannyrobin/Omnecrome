package ru.armd.pbk.repositories.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.FileStream;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.tasks.TaskFile;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.TaskAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.tasks.TaskFileMapper;
import ru.armd.pbk.mappers.tasks.TaskFileStreamMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Репозиторий файла задания.
 */
@Repository
public class TaskFileRepository
	extends BaseDomainRepository<TaskFile> {

   public static final Logger LOGGER = Logger.getLogger(TaskFileRepository.class);

   @Autowired
   protected TaskFileStreamMapper taskFileStreamMapper;

   /**
	* Конструктор.
	*
	* @param taskFileMapper Маппер репозитория.
	*/
   @Autowired
   public TaskFileRepository(TaskFileMapper taskFileMapper) {
	  super(TaskAuditType.TASK_FILE, taskFileMapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Создание файла c передайчей содержимого.
	*
	* @param domain  domain.
	* @param content содержимое.
	* @return файл.
	*/
   public TaskFile createFile(TaskFile domain, byte[] content) {
	  try {
		 DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 FileStream fs = new FileStream();
		 fs.setStream(content);
		 fs.setName(df.format(new Date()) + "_" + domain.getName());
		 taskFileStreamMapper.insert(fs);
		 domain.setStreamId(fs.getId());
		 create(domain);
	  } catch (Exception e) {
		 String message = "Не удалось создать запись. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.INSERT, domain, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

   /**
	* Удаление файлов.
	*
	* @param ids ИД файлов.
	* @return
	*/
   public int deleteFiles(List<String> ids) {
	  int count = 0;
	  if (ids == null) {
		 return count;
	  }
	  try {
		 count = taskFileStreamMapper.delete(ids);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.DELETE, ids, "Успешно удалено " + count + " из " + ids.size() + " записи/записей.", null);
	  } catch (Exception e) {
		 String message = "Не удалось удалить записи. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.DELETE, ids, message, e);
		 throw new PBKException(message, e);
	  }
	  return count;
   }


   /**
	* Получение файла.
	*
	* @param id ИД
	* @return файл.
	*/
   public FileStream getFileStream(String id) {
	  FileStream fs = null;
	  try {
		 fs = taskFileStreamMapper.getById(id);
	  } catch (Exception e) {
		 String message = "Не удалось получить содержимое файла. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return fs;
   }

   /**
	* Обновить скан задания.
	*
	* @param taskId задание
	* @param fileId файл
	*/
   public void updateTaskScan(Long taskId, Long fileId) {
	  ((TaskFileMapper) this.getDomainMapper()).updateTaskScan(taskId, fileId);
   }
}
