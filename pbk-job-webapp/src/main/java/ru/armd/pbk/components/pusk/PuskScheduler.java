package ru.armd.pbk.components.pusk;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.dto.pusk.AuthResponse;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.service.pusk.PuskIntegrationService;
import ru.armd.pbk.services.tasks.TasksService;

import java.util.List;

/**
 * Класс отвечающий за запуск запланированных задач, которые отправляют данные в ПУсК
 */
@Component
public class PuskScheduler {

    public static final Logger log = Logger.getLogger(PuskScheduler.class);

    @Autowired
    private PuskIntegrationService puskIntegrationService;

    @Autowired
    private VisExchangeConfigRepository visExchangeConfigRepository;

    @Autowired
    TasksService tasksService;

    //@Scheduled(cron = "0 0/5 * * * *")
    public void sendChangesToPusk() {

    }
   @Scheduled(cron = "0 0 6 * * ?")
   public void sendPuskData() {
       List<VisExchangeConfig> puskExchangeConfigs = visExchangeConfigRepository.getActiveExportConfigs(VisExchangeObjects.PUSK);
       VisExchangeConfig puskExchangeConfig = puskExchangeConfigs.get(0);
       String puskUrl = puskExchangeConfig.getUri();
       String puskUserName = puskExchangeConfig.getLogin();
       String puskPassword = puskExchangeConfig.getPassword();
       //получение токена
       ResponseEntity<AuthResponse> authResponse = puskIntegrationService.getPuskAuth(puskUrl, puskUserName, puskPassword);
       String token = authResponse.getBody().getAuthToken();
       //отправка заданий
       ResponseEntity puskTaskResponse = puskIntegrationService.sendTaskData(token, puskUrl);
       if (!puskTaskResponse.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
           log.warn("Can't sendTaskData. Response: " + puskTaskResponse.toString());
           puskTaskResponse = puskIntegrationService.sendTaskData(token, puskUrl);
           if (!puskTaskResponse.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
               //TODO В случае повторного неполучения от сервера кода 204
               //зафиксировать неработоспособность сервера ПусК.
               log.error("Can't sendTaskData. Response: " + puskTaskResponse.toString());
           }
       }
       //отправка списка контролеров, неуволенных, участвующих в планировании
       ResponseEntity puskEmployeeResponse = puskIntegrationService.sendEmployeeData(token, puskUrl);
       if (!puskEmployeeResponse.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
           log.warn("Can't sendEmployeeData. Response: " + puskEmployeeResponse.toString());
           puskEmployeeResponse = puskIntegrationService.sendEmployeeData(token, puskUrl);
           if (!puskEmployeeResponse.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
               //TODO В случае повторного неполучения от сервера кода 204
               //зафиксировать неработоспособность сервера ПусК.
               log.error("Can't sendEmployeeData. Response: " + puskEmployeeResponse.toString());
           }
       }
       //отправка актов, закрепленных за контролерами, неиспользованных и нераспечатанных
       ResponseEntity puskBsoInfoResponse = puskIntegrationService.sendBsoData(token, puskUrl);
       if (!puskBsoInfoResponse.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
           log.warn("Can't sendBsoData. Response: " + puskBsoInfoResponse.toString());
           puskBsoInfoResponse = puskIntegrationService.sendBsoData(token, puskUrl);
           if (!puskBsoInfoResponse.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
               //TODO В случае повторного неполучения от сервера кода 204
               //зафиксировать неработоспособность сервера ПусК.
               log.error("Can't sendBsoData. Response: " + puskBsoInfoResponse.toString());
           }
       }
   }
}
