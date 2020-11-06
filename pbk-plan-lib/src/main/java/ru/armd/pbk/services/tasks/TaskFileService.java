package ru.armd.pbk.services.tasks;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.FileStream;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.tasks.TaskFile;
import ru.armd.pbk.dto.tasks.TaskFileDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.matcher.tasks.ITaskFileMatcher;
import ru.armd.pbk.repositories.tasks.TaskFileRepository;

import java.io.IOException;
import java.util.List;

/**
 * Сервис записи файлов заданий.
 */
@Service
public class TaskFileService
	extends BaseDomainService<TaskFile, TaskFileDTO> {

   private static final Logger LOGGER = Logger.getLogger(TaskFileService.class);

   TaskFileRepository repository;

   @Autowired
   TaskFileService(TaskFileRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Создание файла.
	*
	* @param dto ДТО.
	* @return домен файла.
	*/
   public TaskFile createTaskFile(TaskFileDTO dto) {
	  try {
		 TaskFile file = repository.createFile(toDomain(dto), dto.getFile().getBytes());
		 if (dto.getIsScan() == 1) {
			repository.updateTaskScan(file.getTaskId(), file.getId());
		 }
		 return file;
	  } catch (IOException e) {
		 throw new PBKException("Ошибка при сохранении файла.");
	  }
   }

   /**
	* Получение файла.
	*
	* @param id ИД
	* @return файл.
	*/
   public FileStream getFileStream(String id) {
	  return repository.getFileStream(id);
   }

   @Override
   public TaskFile toDomain(TaskFileDTO dto) {
	  TaskFile file = ITaskFileMatcher.INSTANCE.toDomain(dto);
	  if (dto.getFile() != null) {
		 file.setName(file.getName() == null ? dto.getFile().getOriginalFilename()
			 : file.getName() + "." + FilenameUtils.getExtension(dto.getFile().getOriginalFilename()));
		 file.setSize(dto.getFile().getSize());
	  }
	  return file;
   }

   /**
	* Удаление файлов.
	*
	* @param ids список ИД
	* @return
	*/
   public int deleteFiles(List<String> ids) {
	  return repository.deleteFiles(ids);
   }

   @Override
   public TaskFileDTO toDTO(TaskFile domain) {
	  return ITaskFileMatcher.INSTANCE.toDTO(domain);
   }
}
