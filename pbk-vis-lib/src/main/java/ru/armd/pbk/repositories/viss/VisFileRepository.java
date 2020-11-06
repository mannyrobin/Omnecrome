package ru.armd.pbk.repositories.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.FileStream;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.VisFile;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.viss.VisFileMapper;
import ru.armd.pbk.mappers.viss.VisFileStreamMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Репозиторий файла.
 */
@Repository
public class VisFileRepository
	extends BaseDomainRepository<VisFile> {

   public static final Logger LOGGER = Logger.getLogger(VisFileRepository.class);

   @Autowired
   protected VisFileStreamMapper visFileStreamMapper;

   /**
	* Конструктор.
	*
	* @param visFileMapper Маппер репозитория.
	*/
   @Autowired
   public VisFileRepository(VisFileMapper visFileMapper) {
	  super(VisAuditType.VIS_FILE, visFileMapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Создать файл.
	*
	* @param domain  - домен файла.
	* @param content - содержимое.
	* @return
	*/
   public synchronized VisFile createFile(VisFile domain, byte[] content) {
	  try {
		 DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 FileStream fs = new FileStream();
		 fs.setStream(content);
		 fs.setName(df.format(new Date()) + "_" + domain.getName());
		 visFileStreamMapper.insert(fs);
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
	* Получить содержимое файла по ИД.
	*
	* @param id - ид файла.
	* @return
	*/
   public FileStream getFileStream(String id) {
	  FileStream fs = null;
	  try {
		 fs = visFileStreamMapper.getById(id);
	  } catch (Exception e) {
		 String message = "Не удалось получить содержимое файла. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return fs;
   }
}
