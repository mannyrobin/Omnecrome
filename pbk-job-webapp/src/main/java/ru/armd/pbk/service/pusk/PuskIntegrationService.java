package ru.armd.pbk.service.pusk;

import javafx.scene.transform.Scale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.armd.pbk.domain.nsi.bso.BsoDto;
import ru.armd.pbk.domain.nsi.bso.BsoInfo;
import ru.armd.pbk.domain.nsi.employee.PuskPlanningEmployee;
import ru.armd.pbk.domain.tasks.PuskTask;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.dto.nsi.bso.BsoDTO;
import ru.armd.pbk.dto.pusk.AuthResponse;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.services.nsi.bso.BsoService;
import ru.armd.pbk.services.nsi.employee.EmployeeService;
import ru.armd.pbk.services.tasks.TasksService;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service
public class PuskIntegrationService {

    public static final Logger LOGGER = Logger.getLogger(PuskIntegrationService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TasksService tasksService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BsoService bsoService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ResponseEntity<AuthResponse> getPuskAuth(String puskUrl, String puskUserName, String puskPassword) {
        URI uri = UriComponentsBuilder.fromHttpUrl(puskUrl)
                .path("/api/auth/login")
                .build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("X-DEVICE-ID", "1234abcd");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", puskUserName);
        map.add("password", puskPassword);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        return restTemplate.postForEntity(uri, entity, AuthResponse.class);
    }

    public ResponseEntity sendTaskData(String token, String puskUrl) {
        List<PuskTask> puskTasks = tasksService.getTasksForPusk();

        URI uri = UriComponentsBuilder.fromHttpUrl(puskUrl)
                .path("/api/tasks/mgt")
                .build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token", token);

        HttpEntity<List<PuskTask>> entity = new HttpEntity<>(puskTasks, headers);
        return restTemplate.postForEntity(uri, entity, Void.class);
    }

    public ResponseEntity sendEmployeeData(String token, String puskUrl) {
        List<PuskPlanningEmployee> puskEmployees = employeeService.getPlanningEmployeeForPusk();

        URI uri = UriComponentsBuilder.fromHttpUrl(puskUrl)
                .path("/api/inspectors")
                .build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token", token);

        HttpEntity<List<PuskPlanningEmployee>> entity = new HttpEntity<>(puskEmployees, headers);
        return restTemplate.postForEntity(uri, entity, Void.class);
    }

    public ResponseEntity sendBsoData(String token, String puskUrl) {
        List<BsoInfo> puskBsoInfo = bsoService.getBsoInfoForPusk();

        URI uri = UriComponentsBuilder.fromHttpUrl(puskUrl)
                .path("/api/acts/mgt")
                .build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token", token);

        HttpEntity<List<BsoInfo>> entity = new HttpEntity<>(puskBsoInfo, headers);
        return restTemplate.postForEntity(uri, entity, Void.class);
    }

    public void loadActs(String token, String puskUrl) {
        List<BsoDto> bsos = getActs(token, puskUrl);
        for (BsoDto bso : bsos) {
            BsoDTO dbBsoDto = new BsoDTO();
            dbBsoDto.setBsoNumber(bso.getNumber());
            dbBsoDto.setIsPrinted(false);
            dbBsoDto.setIsBound(false);
            if (bso.getStatus() == 2) {
                dbBsoDto.setIsUsed(true);
            }
            if (bso.getStatus() == 3) {
                dbBsoDto.setIsTrashed(true);
            }
            bsoService.saveDTO(dbBsoDto);
            bsoService.useBsos(Collections.singletonList(dbBsoDto.getId()), Long.valueOf(bso.getTaskId()));
        }
    }

    private List<BsoDto> getActs(String token, String puskUrl) {
        Calendar from = Calendar.getInstance();
        from.add(Calendar.DAY_OF_MONTH, -3);
        Calendar to = Calendar.getInstance();
        URI uri = UriComponentsBuilder.fromHttpUrl(puskUrl)
                .path("/api/acts/mgt")
                .queryParam("from",sdf.format(from.getTime()))
                .queryParam("to",sdf.format(to.getTime()))
                .build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token", token);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<BsoDto>> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<BsoDto>>(){});
        return response.getBody();
    }

}
