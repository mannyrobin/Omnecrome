package ru.armd.pbk.components.viss.jobs;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.mappers.viss.calc.CalcMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Джоб, который создает схему загрузки
 * временных файлов для расчета ПП.
 */
@Component
public class CalcSchemaJob
	extends BaseComponent {

   public static final Logger LOGGER = Logger.getLogger(PassengersJob.class);

   private static final String SCHEMA_NAME = "PBK_CALC_%s";

   @Autowired
   private CalcMapper mapper;

   /**
	* Создать схему на дату {@code date}.
	*
	* @param date дата создания схемы.
	*/
   @Transactional
   public void createSchema(Date date) {
	  try {
		 LOGGER.info(String.format("strat create schema on %tD", date));
		 String name = String.format(SCHEMA_NAME, new SimpleDateFormat("yyyyMMdd").format(date));
		 if (!mapper.isSchemaExists(name)) {
			mapper.createSchema(name);
		 }
		 LOGGER.info(String.format("end create schema on %tD", date));
	  } catch (Exception e) {
		 LOGGER.error(String.format("error create schema on %tD", date), e);
	  }
   }

}
