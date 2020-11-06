package ru.armd.pbk.services.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.core.Setting;
import ru.armd.pbk.dto.core.SettingDTO;
import ru.armd.pbk.repositories.core.SettingsRepository;

/**
 * Сервис для работы с настройками системы.
 */
@Service
public class SettingsService
	extends BaseDomainService<Setting, SettingDTO> {

   private static final Logger LOGGER = Logger.getLogger(SettingsService.class);

   @Autowired
   SettingsService(SettingsRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Setting toDomain(SettingDTO dto) {
	  if (dto == null) {
		 return null;
	  }
	  Setting domain = initBaseDomain(dto, new Setting());
	  domain.setValue(dto.getValue());
	  return domain;
   }

   @Override
   public SettingDTO toDTO(Setting domain) {
	  if (domain == null) {
		 return null;
	  }
	  SettingDTO dto = initBaseDTO(domain, new SettingDTO());
	  dto.setValue(domain.getValue());
	  return dto;
   }
}
