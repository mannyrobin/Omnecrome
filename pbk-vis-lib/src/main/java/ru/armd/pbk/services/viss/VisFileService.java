package ru.armd.pbk.services.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.FileStream;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.VisFile;
import ru.armd.pbk.repositories.viss.VisFileRepository;

/**
 * Сервис записи журнала взаимодействия с ВИС.
 */
@Service
public class VisFileService
	extends BaseDomainService<VisFile, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(VisFileService.class);

   VisFileRepository repository;

   @Autowired
   VisFileService(VisFileRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить файл по ИД.
	*
	* @param id - ИД файла.
	* @return
	*/
   public FileStream getFileStream(String id) {
	  return repository.getFileStream(id);
   }
}
