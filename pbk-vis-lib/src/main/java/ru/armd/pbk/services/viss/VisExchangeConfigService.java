package ru.armd.pbk.services.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.dto.viss.JmsVisExchangeStartMessage;
import ru.armd.pbk.dto.viss.VisExchangeConfigDTO;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.matcher.VisExchangeConfigMatcher;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.services.nsi.DriverService;

import java.util.Date;

/**
 * Сервис конфигураций взаимодействия с ВИС.
 */
@Service
public class VisExchangeConfigService
	extends BaseDomainService<VisExchangeConfig, VisExchangeConfigDTO> {

   private static final Logger LOGGER = Logger.getLogger(DriverService.class);

   @Autowired
   private JmsTemplate jmsTemplate;

   @Autowired
   VisExchangeConfigService(VisExchangeConfigRepository repository) {
	  super(repository);
	  // Устанавливаем разрешение на пакет для сериализации
	  System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public VisExchangeConfigDTO toDTO(VisExchangeConfig domain) {
	  return VisExchangeConfigMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public VisExchangeConfig toDomain(VisExchangeConfigDTO dto) {
	  return VisExchangeConfigMatcher.INSTANCE.toDomain(dto);
   }

   /**
	* Запустить обмен с ВИС на указанный периуд с заданным параметром.
	*
	* @param exchangeConfigId - ИД конфигурации.
	* @param parameter        - параметр.
	* @param start            - начала периуда.
	* @param end              - конец периуда.
	*/
   public void start(Long exchangeConfigId, String parameter, Date start, Date end) {
	  VisExchangeConfig config = domainRepository.getById(exchangeConfigId);
	  if (VisExchangeObjects.ASDU_GTFS_DRIVER.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASDU_GTFS_DRIVER, parameter, start, end));
	  } else if (VisExchangeObjects.ASDU_GTFS_VENICLE.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASDU_GTFS_VENICLE, parameter, start, end));
	  } else if (VisExchangeObjects.ASDU_GTFS_EQUIPMENT.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASDU_GTFS_EQUIPMENT, parameter, start, end));
	  } else if (VisExchangeObjects.ASDU_GTFS_STOP.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASDU_GTFS_STOP, parameter, start, end));
	  } else if (VisExchangeObjects.ASDU_GTFS_TELEMATIC.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASDU_GTFS_TELEMATIC, parameter, start, end));
	  } else if (VisExchangeObjects.ASDU_GTFS_TRIP.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASDU_GTFS_TRIP, parameter, start, end));
	  } else if (VisExchangeObjects.ASDU_GTFS_PLANTRIP.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASDU_GTFS_PLANTRIP, parameter, start, end));
	  } else if (VisExchangeObjects.GIS_MGT_OBJECTS.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GIS_MGT_OBJECTS, parameter, start, end));
	  } else if (VisExchangeObjects.EASU_FHD_DEPARTMENT.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.EASU_FHD_DEPARTMENT, parameter, start, end));
	  } else if (VisExchangeObjects.EASU_FHD_EMPLOYEE.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.EASU_FHD_EMPLOYEE, parameter, start, end));
	  } else if (VisExchangeObjects.EASU_FHD_TRIP_ORDER.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.EASU_FHD_TRIP_ORDER, parameter, start, end));
	  } else if (VisExchangeObjects.EASU_FHD_TRIP_SCHEDULE.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.EASU_FHD_TRIP_SCHEDULE, parameter, start, end));
	  } else if (VisExchangeObjects.EASU_FHD_WORK_MODE.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.EASU_FHD_WORK_MODE, parameter, start, end));
	  } else if (VisExchangeObjects.EASU_FHD_VENICLE.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.EASU_FHD_VENICLE, parameter, start, end));
	  } else if (VisExchangeObjects.EASU_FHD_SKK.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.EASU_FHD_SKK, parameter, start, end));
	  } else if (VisExchangeObjects.EASU_FHD_BSK.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.EASU_FHD_BSK, parameter, start, end));
	  } else if (VisExchangeObjects.DVR_RECORD_MOK1.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.DVR_RECORD_MOK1, parameter, start, end));
	  } else if (VisExchangeObjects.DVR_RECORD_MOK2.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.DVR_RECORD_MOK2, parameter, start, end));
	  } else if (VisExchangeObjects.DVR_RECORD_MOK3.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.DVR_RECORD_MOK3, parameter, start, end));
	  } else if (VisExchangeObjects.DVR_RECORD_MOK4.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.DVR_RECORD_MOK4, parameter, start, end));
	  } else if (VisExchangeObjects.DVR_RECORD_MOK5.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.DVR_RECORD_MOK5, parameter, start, end));
	  } else if (VisExchangeObjects.DVR_DVR.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.DVR_DVR, parameter, start, end));
	  } else if (VisExchangeObjects.GKUOP_EMPLOYEE.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GKUOP_EMPLOYEE, parameter, start, end));
	  } else if (VisExchangeObjects.ASKP_TICKET.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASKP_TICKET, parameter, start, end));
	  } else if (VisExchangeObjects.ASKP_TICKET_CHECK.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASKP_TICKET_CHECK, parameter, start, end));
	  } else if (VisExchangeObjects.ASKP_PUSK_CHECK.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASKP_PUSK_CHECK, parameter, start, end));
	  } else if (VisExchangeObjects.ASKP_CONTACTLESS_CARD.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASKP_CONTACTLESS_CARD, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_CHANGE_EQUIPMENT.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_CHANGE_EQUIPMENT, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_CHANGE_STOP_TIMES.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_CHANGE_STOP_TIMES, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_EQUIPMENT.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_EQUIPMENT, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_IMPACT.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_IMPACT, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_IMPACT_CODE.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_IMPACT_CODE, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_REPLACE_VEHICLE.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_REPLACE_VEHICLE, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_STOPS.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_STOPS, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_STOP_TIMES.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_STOP_TIMES, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_TRIPS.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_TRIPS, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_TRIPS_STOPS.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_TRIPS_STOPS, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_VEHICLE_TASK.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_VEHICLE_TASK, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_DEPOTS.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_DEPOTS, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_CALENDAR.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_CALENDAR, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_VEHICLES.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_VEHICLES, parameter, start, end));
	  } else if (VisExchangeObjects.ASMPP_STOPS.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.ASMPP_STOPS, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_ROUTE.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_ROUTE, parameter, start, end));
	  } else if (VisExchangeObjects.GTFS_FILESYSTEM.getId().equals(config.getExchangeObjectId())) {
		 sendMessage(new JmsVisExchangeStartMessage(VisExchangeObjects.GTFS_FILESYSTEM, parameter, start, end));
	  } else {
		 throw new PBKException("Данная конфигурация обмена недоступна для запуска в ручную");
	  }
   }

   private void sendMessage(JmsVisExchangeStartMessage pbkMessage) {
	  try {
		 jmsTemplate.convertAndSend("VisExchange", pbkMessage);
		 logAudit(AuditLevel.INFO, VisAuditType.VIS_EXCHANGE_CONFIG, AuditObjOperation.SEND_CMD, pbkMessage, "Команда запуска обмена успешно отправлена", null);
	  } catch (Exception e) {
		 getLogger().error(e.getMessage(), e);
		 String message = "Не удалось отправить команду на выполнение. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, VisAuditType.VIS_EXCHANGE_CONFIG, AuditObjOperation.SEND_CMD, null, message, e);
		 throw new PBKException(message, e);
	  }
   }
}
