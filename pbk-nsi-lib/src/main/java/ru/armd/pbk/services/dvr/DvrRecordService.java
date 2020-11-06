package ru.armd.pbk.services.dvr;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.dvr.DvrRecord;
import ru.armd.pbk.repositories.dvr.DvrRecordRepository;

@Service
public class DvrRecordService
	extends BaseDomainService<DvrRecord, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(DvrRecordService.class);
   private DvrRecordRepository domainRepository;

   @Autowired
   public DvrRecordService(DvrRecordRepository domainRepository) {
	  super(domainRepository);
	  this.domainRepository = domainRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Привязать записи с видео регистраторов к заданию.
	*
	* @param taskId - ИД задания.
	*/
   @Transactional
   public int bind(Long taskId) {
	  return domainRepository.bind(taskId);
   }
}
