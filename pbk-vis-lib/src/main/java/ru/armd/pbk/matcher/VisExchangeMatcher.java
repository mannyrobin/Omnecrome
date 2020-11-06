package ru.armd.pbk.matcher;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.viss.Vis;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.domain.viss.VisExchangeObject;
import ru.armd.pbk.domain.viss.VisExchangeOperation;
import ru.armd.pbk.domain.viss.VisExchangeState;
import ru.armd.pbk.domain.viss.VisTransportType;
import ru.armd.pbk.dto.viss.VisExchangeDTO;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.repositories.viss.VisExchangeObjectRepository;
import ru.armd.pbk.repositories.viss.VisExchangeOperationRepository;
import ru.armd.pbk.repositories.viss.VisExchangeStateRepository;
import ru.armd.pbk.repositories.viss.VisRepository;
import ru.armd.pbk.repositories.viss.VisTransportTypeRepository;

/**
 * Сопоставление сущностей журнала взаимодействия с ВИС.
 */
@Mapper
public abstract class VisExchangeMatcher
	implements IDomainMatcher<VisExchange, VisExchangeDTO> {

   public static final VisExchangeMatcher INSTANCE = Mappers.getMapper(VisExchangeMatcher.class);

   private static final String DAY_FAIL = "Нарушено";
   private static final String NOT_DAY_FAIL = "Ненарушено";
   private static final Integer DAY_FAIL_VALUE = 1;

   /**
	* Инициализация репозиториев.
	*/
   public static void setRepository() {
   }

   /**
	* @param domain                         Домен.
	* @param visExchangeConfigRepository    репозиторий конфигураций взаимодействия.
	* @param visExchangeStateRepository     репозиторий статусов конфигураций взаимодействия.
	* @param visTransportTypeRepository     репозиторий видов транспорта.
	* @param visExchangeObjectRepository    репозиторий типов взаимодействия.
	* @param visExchangeOperationRepository репозиторий типов операций.
	* @param visRepository                  репозиторий ВИС.
	* @return ДТО.
	*/
   public VisExchangeDTO toDTOForView(VisExchange domain,
									  VisExchangeConfigRepository visExchangeConfigRepository,
									  VisExchangeStateRepository visExchangeStateRepository,
									  VisTransportTypeRepository visTransportTypeRepository,
									  VisExchangeObjectRepository visExchangeObjectRepository,
									  VisExchangeOperationRepository visExchangeOperationRepository,
									  VisRepository visRepository) {
	  VisExchangeDTO dto = toDTO(domain);
	  if (dto != null) {
		 if (dto.getConfigId() != null) {

			VisExchangeConfig exchangeConfig = visExchangeConfigRepository.getById(dto.getConfigId());
			if (exchangeConfig != null) {
			   dto.setConfigName(exchangeConfig.getName());

			   VisTransportType transportType = visTransportTypeRepository
				   .getById(exchangeConfig.getTransportTypeId());
			   if (transportType != null) {
				  dto.setTransportTypeName(transportType.getName());
			   }

			   VisExchangeOperation exchangeOperation = visExchangeOperationRepository
				   .getById(exchangeConfig.getExchangeOperationId());
			   if (exchangeOperation != null) {
				  dto.setExchangeOperationName(exchangeOperation.getName());
			   }

			   VisExchangeObject exchangeObject = visExchangeObjectRepository.getById(exchangeConfig.getExchangeObjectId());
			   if (exchangeObject != null) {
				  dto.setExchangeObjectName(exchangeObject.getName());

				  Vis vis = visRepository.getById(exchangeObject.getVisId());
				  dto.setVisName(vis == null ? null : vis.getName());

			   }
			}
		 }

		 VisExchangeState exchangeState = visExchangeStateRepository.getById(dto.getStateId());
		 if (exchangeState != null) {
			dto.setStateName(exchangeState.getName());
		 }

		 if (dto.getIsDayFail() != null && dto.getIsDayFail().equals(DAY_FAIL_VALUE)) {
			dto.setDayFail(DAY_FAIL);
		 } else {
			dto.setDayFail(NOT_DAY_FAIL);
		 }
	  }
	  return dto;
   }
}
