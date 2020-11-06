package ru.armd.pbk.components.viss.dvr.records;

import armd.lightHttp.client.BaseHttpClientParameters;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseJsonDomainLoader;
import ru.armd.pbk.components.viss.dvr.IDrvLoader;
import ru.armd.pbk.core.domain.BaseDeviceOwnerHistoryDomain;
import ru.armd.pbk.domain.dvr.DvrRecord;
import ru.armd.pbk.domain.dvr.DvrStorePeriod;
import ru.armd.pbk.domain.nsi.Dvr;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.domain.tasks.Task;
import ru.armd.pbk.mappers.nsi.employee.EmployeeMapper;
import ru.armd.pbk.repositories.dvr.DvrRecordRepository;
import ru.armd.pbk.repositories.dvr.DvrStorePeriodRepository;
import ru.armd.pbk.repositories.nsi.DvrRepository;
import ru.armd.pbk.repositories.nsi.device.history.DvrHistoryRepository;
import ru.armd.pbk.repositories.tasks.TaskRepository;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Загрузчик для записей с видеорегистраторов.
 */
@Component
@Scope("prototype")
public class DvrRecordLoader
	extends BaseJsonDomainLoader<DvrRecord>
	implements IDrvLoader {

   private static final String POSSIBLE = "abcdef0123456789";
   private static final String HASH = ":21232f297a57a5a743894a0e4a801fc3:";

   private DvrRecordRepository dvrRecordRepository;

   @Autowired
   private DvrRepository dvrRepository;

   @Autowired
   private DvrStorePeriodRepository dvrStorePeriodRepository;

   @Autowired
   private TaskRepository taskRepository;

   @Autowired
   private EmployeeMapper employeeMapper;

   @Autowired
   private DvrHistoryRepository dvrHistoryRepository;

   private String dvrUrl = "";

   private BaseHttpClientParameters parameters = null;

   /**
	* Конструктор.
	*
	* @param repository - репозиторий.
	*/
   @Autowired
   public DvrRecordLoader(DvrRecordRepository repository) {
	  super(repository);
	  dvrRecordRepository = repository;
   }

   @Override
   protected JsonArray getDomainsArray(JsonObject object) {
	  return object.getAsJsonArray("response");
   }

   @Override
   protected DvrRecord createDomain(JsonObject element) {
	  DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  DvrRecord domain = new DvrRecord();
	  try {
		 domain.setDateRecord(format.parse(element.get("date_record").getAsString()));
		 domain.setDateSession(format.parse(element.get("date_session").getAsString()));
	  } catch (ParseException e) {
		 e.printStackTrace();
	  }
	  domain.setDescriptionSession(element.get("description_session").getAsString());
	  domain.setDesecriptionRecord(element.get("description_record").getAsString());
	  domain.setDuration(element.get("duration").getAsString());
	  domain.setDvrId(element.get("id").getAsLong());
	  domain.setFileSize(element.get("file_size").getAsString());
	  domain.setMime(element.get("mime").getAsString());
	  domain.setOwnerName(element.get("owner").getAsJsonObject().get("name").getAsString());
	  String dvrNumber = element.get("device").getAsJsonObject().get("number").getAsString();
	  Long storeId = element.get("store").getAsJsonObject().get("id").getAsLong();
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("dvrNumber", dvrNumber);
	  params.put("dvrId", storeId);
	  Dvr dvr = dvrRepository.getDomain(params);
	  if (dvr == null) {
		 dvr = new Dvr();
		 dvr.setDvrNumber(dvrNumber);
		 dvr = dvrRepository.save(dvr);
	  }
	  DvrStorePeriod store = dvrStorePeriodRepository.getDomain(params);
	  if (store == null) {
		 store = new DvrStorePeriod();
		 store.setDvrId(storeId);
		 store.setName(element.get("store").getAsJsonObject().get("value").getAsString());
		 store = dvrStorePeriodRepository.save(store);
	  }
	  domain.setStoreId(store.getId());
	  domain.setDeviceId(dvr.getHeadId());
	  String path = "/api/records/" + element.get("id").getAsLong() + "/get";
	  domain.setUrl(dvrUrl + path + "?sign=" + signQuery(path));
	  Employee employee = employeeMapper.getDomain(getFioParams(domain.getOwnerName().trim()));
	  if (employee == null) {
		 employee = employeeMapper.getDomain(getDvrParams(dvr.getHeadId()));
	  }
	  if (employee != null) {
		 if (dvr.getDeptId() == null) {
			dvr.setDeptId(employee.getDepartmentId());
			dvr = dvrRepository.save(dvr);
		 }
		 Task task = taskRepository.getEmployeeTask(employee.getHeadId(), domain.getDateRecord(), true);
		 if (task != null) {
			domain.setTaskId(task.getId());
		 }
		 BaseDeviceOwnerHistoryDomain history = dvrHistoryRepository.getActual(dvr.getHeadId());
		 if (history == null) {
			history = new BaseDeviceOwnerHistoryDomain();
			history.setDeviceId(dvr.getHeadId());
		 }
		 if (!employee.getHeadId().equals(history.getEmployeeId())) {
			history.setEmployeeId(employee.getHeadId());
			history.setVersionStartDate(new Date());
			dvrHistoryRepository.saveVersion(history);
		 }
	  }
	  return domain;
   }

   private Map<String, Object> getFioParams(String param) {
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("fio_e", param);
	  params.put("forPlanUse", 1);
	  return params;
   }

   private Map<String, Object> getDvrParams(Long param) {
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("dvrId", param);
	  return params;
   }

   protected String signQuery(String path) {
	  String salt = "";
	  for (int i = 0; i < 10; i++) {
		 salt += POSSIBLE.charAt((int) (Math.floor(Math.random() * POSSIBLE.length())));
	  }
	  String hash = ":"+DigestUtils.md5Hex(parameters.getServicePassword())+":";
	  return salt + ":" + DigestUtils.shaHex(salt + hash + path);

   }

   @Override
   protected DvrRecord getExistedDomain(DvrRecord newDomain) {
	  return dvrRecordRepository.getDomain(getRecordParams(newDomain));
   }

   @Override
   protected DvrRecord save(DvrRecord domain) {
		/*
		 * TODO не известно, могут ли у них изменяться записи
		 * или может быть у разных записей один ID (разные моки)
		 * поэтому данный метод нужен что бы отсеять повторяющиеся 
		 * записи при обновлении на неделю.
		 */
	  return domain.getId() == null ?
		  super.save(domain) : null;
   }

   protected Map<String, Object> getRecordParams(DvrRecord newDomain) {
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("dvrId", newDomain.getDvrId());
	  params.put("deviceId", newDomain.getDeviceId());
	  return params;
   }

   /**
	* Загрузить файл.
	*
	* @param inputStream входной поток.
	* @param uri         uri записей.
	* @return
	*/
   public ImportResult<?> importFile(InputStream inputStream, String uri,BaseHttpClientParameters params) {
	  dvrUrl = uri.split("/api")[0];
	  parameters = params;
	  return importFile(inputStream);
   }

}
