package ru.armd.pbk.components.viss.core;

import armd.lightHttp.client.BaseHttpClientParameters;
import armd.lightHttp.client.HttpClientException;
import armd.lightRest.client.BaseRestClient;
import armd.lightSoap.client.BaseSoapClient;
import armd.lightSoap.client.BaseSoapClientParameters;
import armd.lightSoap.client.SoapClientException;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.components.viss.core.clients.DefaultRestClient;
import ru.armd.pbk.components.viss.core.clients.DefaultSoapClient;
import ru.armd.pbk.components.viss.core.exceptions.VisFileSysConnectionException;
import ru.armd.pbk.components.viss.core.exceptions.VisFtpConnectionException;
import ru.armd.pbk.components.viss.core.exceptions.VisFtpException;
import ru.armd.pbk.components.viss.core.exceptions.VisHttpConnectionException;
import ru.armd.pbk.components.viss.core.exceptions.VisHttpException;
import ru.armd.pbk.components.viss.core.exceptions.VisNonFileException;
import ru.armd.pbk.components.viss.core.exceptions.VisSoapConnectionException;
import ru.armd.pbk.components.viss.core.exceptions.VisSoapException;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.domain.viss.VisExchangeAttempt;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.domain.viss.VisExchangeResult;
import ru.armd.pbk.domain.viss.VisFile;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.viss.VisExchangeOperations;
import ru.armd.pbk.enums.viss.VisExchangeStates;
import ru.armd.pbk.enums.viss.VisTransportTypes;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.exceptions.PBKConnectionException;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.repositories.viss.VisExchangeAttemptRepository;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.repositories.viss.VisExchangeObjectRepository;
import ru.armd.pbk.repositories.viss.VisExchangeRepository;
import ru.armd.pbk.repositories.viss.VisExchangeResultRepository;
import ru.armd.pbk.repositories.viss.VisFileRepository;
import ru.armd.pbk.utils.ImportResult;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.audit.TimeMeter;
import ru.armd.pbk.utils.files.File;
import ru.armd.pbk.utils.files.FileProvider;
import ru.armd.pbk.utils.files.FileProviderFactory;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Базовый класс провайдера взаимодействия с ВИС. Содержит общие методы всех
 * провайдеров ВИС.
 */
public abstract class BaseExchangeProvider
	extends BaseComponent {

   public static final Logger LOGGER = Logger.getLogger(BaseExchangeProvider.class);

   protected static final char SLASH = '/';

   protected static final String PERFORMANCE_TASK_STR = "Выполнение задачи по информационному обмену на дату ";

   @Autowired
   protected VisExchangeRepository visExchangeRepository;

   @Autowired
   protected VisExchangeAttemptRepository visExchangeAttemptRepository;

   @Autowired
   protected VisExchangeResultRepository visExchangeResultRepository;

   @Autowired
   protected VisExchangeConfigRepository visExchangeConfigRepository;

   @Autowired
   protected VisFileRepository visFileRepository;

   @Autowired
   protected VisExchangeObjectRepository visExchangeObjectRepository;

   protected Viss viss = null;
   protected AuditType auditType = null;
   protected AuditObjOperation auditObjOperation = null;

   /**
	* Конструктор.
	*
	* @param viss      ВИС.
	* @param auditType Тип аудита для логгирования.
	*/
   public BaseExchangeProvider(Viss viss, AuditType auditType) {
	  this.viss = viss;
	  this.auditType = auditType;
	  this.auditObjOperation = AuditObjOperation.EXCHANGE;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Метод производит информационный обмен согласно конфигурации на дату. Если
	* обмена на дату нет, то его создает. Если обмен есть, то смотрит на
	* статус. Если обмен завершился неудачей, то производит повторный обмен.
	* Если признак принудительного запуска true, то обмен произойдет повторно.
	*
	* @param visExchangeConfig Конфигурация.
	* @param parameter         Параметр запуска.
	* @param workDate          Дата обмена.
	* @param force             Признак принудительного запуска.
	*/
   @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS)
   public void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate, boolean force) {
	  TimeMeter timeMeter = new TimeMeter();
	  String workDateStr = StringUtils.getStringByDate(workDate);
	  VisExchange visExchange = null;
	  try {
		 logAudit(AuditLevel.INFO, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Начинаю информационный обмен на дату " + workDateStr, null);
		 if (visExchangeConfig == null || visExchangeConfig.getId() == null) {
			throw new PBKException("Конфигурация обмена не определена");
		 }
		 if (workDate == null) {
			throw new PBKException("Дата обмена не определена");
		 }
		 // Если конфигурация отключена, то обмена не произойдет
		 if (!visExchangeConfig.isActive()) {
			logAudit(AuditLevel.WARNING, auditType, auditObjOperation, null,
				Thread.currentThread().getName() + PERFORMANCE_TASK_STR
					+ workDateStr + " отключено конфигурацией обмена",
				null);
			return;
		 }
		 // Ищу запись обмена по дате, конфигурации и параметру
		 visExchange = visExchangeRepository.getVisExchange(visExchangeConfig.getId(), workDate, parameter);
		 if (visExchange == null) {
			// Создаю новый обмен согласно конфигурации
			visExchange = createExchange(visExchangeConfig.getId(), workDate, VisExchangeStates.TO_PROCESS.getId(),
				parameter);
		 } else {
			// Обмен уже существует. Смотрю его статус.
			// Корректен для обработки только один статус - "Ошибка
			// обработки".
			// Если получаются у обмена другие статусы - то мы не правильно
			// произвели выборку или ошибка в логике многопоточности
			if (visExchange.getStateId().equals(VisExchangeStates.TO_PROCESS.getId())) {
			   logAudit(AuditLevel.WARNING, auditType, auditObjOperation, null,
				   Thread.currentThread().getName() + PERFORMANCE_TASK_STR
					   + workDateStr + " уже запланировано",
				   null);
			   throw new PBKException(PERFORMANCE_TASK_STR + workDateStr
				   + " уже запланировано");
			} else if (visExchange.getStateId().equals(VisExchangeStates.IN_PROCESS.getId())) {
			   logAudit(AuditLevel.WARNING, auditType, auditObjOperation, null,
				   Thread.currentThread().getName() + PERFORMANCE_TASK_STR
					   + workDateStr + " уже в процессе исполнения",
				   null);
			   throw new PBKException(PERFORMANCE_TASK_STR + workDateStr
				   + " уже в процессе исполнения");
			} else if (visExchange.getStateId().equals(VisExchangeStates.SUCCESS.getId()) && !force) {
			   logAudit(AuditLevel.WARNING, auditType, auditObjOperation, null,
				   Thread.currentThread().getName() + PERFORMANCE_TASK_STR
					   + workDateStr + " уже успешно завершено",
				   null);
			   throw new PBKException(PERFORMANCE_TASK_STR + workDateStr
				   + " уже успешно завершено");
			}
		 }
		 // Перевожу статус журнальной записи обмена "В обработке"
		 setExchangeState(visExchange.getId(), VisExchangeStates.IN_PROCESS.getId(), null);
		 // Произвожу обмен
		 doExchangeAttempt(visExchange, visExchangeConfig);
		 // Перевожу статус журнальной записи обмена "Успешно обработан"
		 setExchangeState(visExchange.getId(), VisExchangeStates.SUCCESS.getId(), null);
		 timeMeter.appendPrintInterval("done");
		 logAudit(AuditLevel.INFO, auditType, auditObjOperation, null, Thread.currentThread().getName()
			 + "Ответ на запрос от ВИС на дату " + workDateStr + " успешно обработан", null);
	  } catch (VisFileSysConnectionException e) {
		 if (visExchange != null) {
			setExchangeState(visExchange.getId(), VisExchangeStates.FAIL.getId(), e.getMessage());
		 }
		 logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " ВИС недоступна. Не удалось подключится к файловой системе. "
				 + "Информационный обмен на дату " + workDateStr + " завершен с ошибкой: "
				 + e.getCause() != null ? e.getCause().getMessage() : e.getMessage(),
			 e);
	  } catch (VisFtpConnectionException e) {
		 if (visExchange != null) {
			setExchangeState(visExchange.getId(), VisExchangeStates.FAIL.getId(), e.getMessage());
		 }
		 logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " ftp-ресурс недоступен. Не удалось подключится к ftp. "
				 + "Информационный обмен на дату " + workDateStr + " завершен с ошибкой: "
				 + e.getCause() != null ? e.getCause().getMessage() : e.getMessage(),
			 e);
	  } catch (VisHttpConnectionException e) {
		 if (visExchange != null) {
			setExchangeState(visExchange.getId(), VisExchangeStates.FAIL.getId(), e.getMessage());
		 }
		 logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " ВИС недоступна. Не удалось подключится по http. "
				 + "Информационный обмен на дату " + workDateStr + " завершен с ошибкой: "
				 + e.getCause() != null ? e.getCause().getMessage() : e.getMessage(),
			 e);
	  } catch (VisSoapConnectionException e) {
		 if (visExchange != null) {
			setExchangeState(visExchange.getId(), VisExchangeStates.FAIL.getId(), e.getMessage());
		 }
		 logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " ВИС недоступна. Не удалось отправить soap запрос. "
				 + "Информационный обмен на дату " + workDateStr + " завершен с ошибкой: "
				 + e.getCause() != null ? e.getCause().getMessage() : e.getMessage(),
			 e);
	  } catch (Exception e) {
		 if (visExchange != null) {
			// Перевожу статус журнальной записи обмена "Ошибка обработки"
			setExchangeState(visExchange.getId(), VisExchangeStates.FAIL.getId(), e.getMessage());
		 }
		 logAudit(AuditLevel.ERROR,
			 auditType, auditObjOperation, null, Thread.currentThread().getName()
				 + "Информационный обмен на дату " + workDateStr + " завершен с ошибкой: " + e.getMessage(),
			 e);
	  } finally {
		 logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + ": doProcess finish in " + timeMeter.getMessageInterval(), null);
	  }
   }

   /**
	* Метод создает новую журнальную запись отправки обмена и производит обмен
	* согласно транспорту.
	*
	* @param visExchange       Журнальная запись обмена.
	* @param visExchangeConfig Конфигурация.
	* @return Журнальная запись отправки обмена.
	* @throws Exception
	*/
   protected VisExchangeAttempt doExchangeAttempt(VisExchange visExchange, VisExchangeConfig visExchangeConfig)
	   throws Exception {
	  // Создаю журнальную запись отправки обмена
	  VisExchangeAttempt visExchangeAttempt = createExchangeAttempt(visExchange.getId(),
		  VisExchangeStates.TO_PROCESS.getId());

	  // произвожу обмен
	  if (VisExchangeOperations.IMPORT.getId().equals(visExchangeConfig.getExchangeOperationId())) {
		 if (VisTransportTypes.SOAP.getId().equals(visExchangeConfig.getTransportTypeId())) {
			doImportSoapExchange(visExchange, visExchangeConfig, visExchangeAttempt);
		 } else if (VisTransportTypes.FTP.getId().equals(visExchangeConfig.getTransportTypeId())) {
			doImportFtpExchange(visExchange, visExchangeConfig, visExchangeAttempt);
		 } else if (VisTransportTypes.FILE_SYSTEM.getId().equals(visExchangeConfig.getTransportTypeId())) {
			doImportFileSystemExchange(visExchange, visExchangeConfig, visExchangeAttempt);
		 } else if (VisTransportTypes.HTTP.getId().equals(visExchangeConfig.getTransportTypeId())) {
			doImportHttpExchange(visExchange, visExchangeConfig, visExchangeAttempt);
		 }
	  } else if (VisExchangeOperations.EXPORT.getId().equals(visExchangeConfig.getExchangeOperationId())) {
		 if (VisTransportTypes.SOAP.getId().equals(visExchangeConfig.getTransportTypeId())) {
			doExportSoapExchange(visExchange, visExchangeConfig, visExchangeAttempt);
		 } else if (VisTransportTypes.FTP.getId().equals(visExchangeConfig.getTransportTypeId())) {
			doExportFtpExchange(visExchange, visExchangeConfig, visExchangeAttempt);
		 } else if (VisTransportTypes.FILE_SYSTEM.getId().equals(visExchangeConfig.getTransportTypeId())) {
			doExportFileSystemExchange(visExchange, visExchangeConfig, visExchangeAttempt);
		 } else if (VisTransportTypes.HTTP.getId().equals(visExchangeConfig.getTransportTypeId())) {
			doExportHttpExchange(visExchange, visExchangeConfig, visExchangeAttempt);
		 }
	  }
	  return visExchangeAttempt;
   }

   @Transactional
   protected void doImportSoapExchange(VisExchange visExchange, VisExchangeConfig visExchangeConfig,
									   VisExchangeAttempt visExchangeAttempt)
	   throws Exception {

	  updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.IN_PROCESS.getId());

	  VisExchangeResult visExchangeResult = createExchangeResult(visExchangeAttempt.getId(),
		  VisExchangeStates.IN_PROCESS.getId());

	  try {
		 BaseSoapClientParameters parameters = new BaseSoapClientParameters();
		 parameters.setServiceAddress(visExchangeConfig.getUri());
		 parameters.setServiceUsername(visExchangeConfig.getLogin());
		 parameters.setServicePassword(visExchangeConfig.getPassword());
		 parameters.setLogExchangeToFiles(true);
		 parameters.setLogExchangeDir("./soap-logs/");
		 logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null, "Установка соединения с ВИС", null);
		 DefaultSoapClient client = createSoapClient(parameters);
		 importSoap(visExchange, client);

		 VisFile visFile = createVisFile(visExchangeConfig, visExchange, "_soap.xml",
			 "Файл импортирован по протоколу soap", client.getRawResponse().getBytes("UTF-8"));

		 updateExchangeResult(visExchangeResult, VisExchangeStates.SUCCESS.getId(), visFile,
			 String.format("Ответ успешно обработан"));

		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.SUCCESS.getId(), null,
			 String.format("Импорт успешно завершен"));
	  } catch (SoapClientException e) {
		 updateExchangeResult(visExchangeResult, VisExchangeStates.FAIL.getId(), null,
			 String.format("Ошибка обработки ответа"));
		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка импорта по протоколу soap: " + e.getMessage());
		 if (SoapClientException.TIMEOUT == e.getSysCode()
			 || SoapClientException.TIMEOUT_CONNECT == e.getSysCode()) {
			throw new VisSoapConnectionException(e);
		 }
		 throw new VisSoapException(e.getMessage(), e);
	  } catch (Exception e) {
		 updateExchangeResult(visExchangeResult, VisExchangeStates.FAIL.getId(), null,
			 String.format("Ошибка обработки ответа"));
		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка импорта по протоколу soap: " + e.getMessage());
		 throw e;
	  }
   }

   @Transactional
   protected void doImportHttpExchange(VisExchange visExchange, VisExchangeConfig visExchangeConfig,
									   VisExchangeAttempt visExchangeAttempt)
	   throws Exception {

	  updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.IN_PROCESS.getId());

	  try {
		 BaseHttpClientParameters parameters = new BaseHttpClientParameters();
		 parameters.setServiceAddress(visExchangeConfig.getUri());
		 parameters.setServiceUsername(visExchangeConfig.getLogin());
		 parameters.setServicePassword(visExchangeConfig.getPassword());
		 parameters.setLogExchangeToFiles(true);
		 parameters.setLogExchangeDir("./http-logs/");
		 logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null, "Установка соединения с ВИС", null);
		 DefaultRestClient client = createRestClient(visExchange, parameters);
		 doRestRequest(visExchange, client);

		 if (processAsZip(visExchange, client)) {

			VisFile visFile = createVisFile(visExchangeConfig, visExchange, ".zip",
				"Файл импортирован по протоколу http", client.getRawResponse());

			String path = unzip(client.getRawResponse(), "./http-logs/unzip");

			FileProvider fileProvider = FileProviderFactory.constructFileSystemProvider(path);
			if (fileProvider == null) {
			   throw new PBKException("fileProvider == null");
			}

			try {
			   File[] listFiles = fileProvider.listFiles("");

			   int processed = 0;
			   int succeed = 0;
			   for (File file : listFiles) {
				  try {
					 InputStream isFile = fileProvider.openFileStream(file.getName());
					 if (isFile != null) {
						++processed;
						doImportFile(visExchangeConfig, visExchange, visExchangeAttempt, isFile, file, visFile);
						++succeed;
					 }
				  } catch (Exception ex) {
					 LOGGER.error(ex);
				  }
			   }
			   if (succeed == 0) {
				  throw new VisNonFileException("Обработано 0 файлов");
			   }
			   updateExchangeAttempt(visExchangeAttempt, succeed == processed ? VisExchangeStates.SUCCESS.getId() : VisExchangeStates.FAIL.getId(),
				   null, String.format("Успешно обработано %d файлов из %d", succeed, processed));
			} finally {
			   fileProvider.closeProvider();
			}

		 } else {
			VisExchangeResult visExchangeResult = createExchangeResult(visExchangeAttempt.getId(),
				VisExchangeStates.IN_PROCESS.getId());
			try {
			   VisFile visFile = createVisFile(visExchangeConfig, visExchange, "_response.raw",
				   "Файл импортирован по протоколу http", client.getRawResponse());

			   updateExchangeResult(visExchangeResult, VisExchangeStates.IN_PROCESS.getId(), visFile, "");

			   importStream(visExchange, new ByteArrayInputStream(client.getRawResponse()));

			   updateExchangeResult(visExchangeResult, VisExchangeStates.SUCCESS.getId(), visFile,
				   String.format("Ответ успешно обработан"));

			   updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.SUCCESS.getId(), null,
				   String.format("Импорт успешно завершен"));
			} catch (Exception e) {
			   updateExchangeResult(visExchangeResult, VisExchangeStates.FAIL.getId(), null,
				   String.format("Ошибка обработки ответа"));
			   throw e;
			}
		 }

	  } catch (HttpClientException e) {
		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка импорта по протоколу http: " + e.getMessage());
		 if (HttpClientException.TIMEOUT == e.getSysCode()
			 || HttpClientException.TIMEOUT_CONNECT == e.getSysCode()) {
			throw new VisHttpConnectionException(e);
		 }
		 throw new VisHttpException(e.getMessage(), e);
	  } catch (Exception e) {
		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка импорта по протоколу http: " + e.getMessage());
		 throw e;
	  }
   }

   @Transactional
   protected void doImportFtpExchange(VisExchange visExchange, VisExchangeConfig visExchangeConfig,
									  VisExchangeAttempt visExchangeAttempt)
	   throws Exception {

	  updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.IN_PROCESS.getId());
	  logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null, "Установка соединения с ВИС", null);

	  FileProvider fileProvider = null;
	  try {
		 fileProvider = FileProviderFactory.constructFtpFileProvider(visExchangeConfig.getUri(),
			 visExchangeConfig.getLogin(), visExchangeConfig.getPassword());
	  } catch (PBKConnectionException e) {
		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка импорта с ФТП: " + e.getMessage());
		 throw new VisFtpConnectionException(e.getCause() != null ? e.getCause() : e);
	  } catch (PBKException e) {
		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка импорта с ФТП: " + e.getMessage());
		 throw new VisFtpException(e.getCause() != null ? e.getCause() : e);
	  }
	  try {
		 Integer[] status = new Integer[] {0, 0};
		 doProcessFiles(fileProvider, visExchange, visExchangeConfig, visExchangeAttempt, "" + SLASH,
			 fileProvider.listFiles(""), status);
		 if (status[0].equals(0)) {
			throw new VisNonFileException("Обработано 0 файлов");
		 }
		 updateExchangeAttempt(visExchangeAttempt, status[0].equals(status[1]) ? VisExchangeStates.SUCCESS.getId() : VisExchangeStates.FAIL.getId(),
			 null, String.format("Успешно обработано %d файлов из %d", status[0], status[1]));
	  } catch (Exception e) {
		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка импорта с ФТП: " + e.getMessage());
		 throw e;
	  } finally {
		 if (fileProvider != null) {
			fileProvider.closeProvider();
		 }
	  }
   }

   protected void doProcessFiles(FileProvider provider, VisExchange visExchange, VisExchangeConfig visExchangeConfig,
								 VisExchangeAttempt visExchangeAttempt, String path, File[] files, Integer[] status)
	   throws IOException {
	  java.io.File dirFile = new java.io.File("./tmp-logs/");
	  if (!dirFile.exists()) {
		 dirFile.mkdir();
	  }
	  for (File file : files) {
		 if (file.isDirectory()) {
			if (!checkFolderName(visExchange, file.getName())) {
			   continue;
			}
			doProcessFiles(provider, visExchange, visExchangeConfig, visExchangeAttempt,
				path + file.getName() + SLASH, provider.listFiles(SLASH + file.getName()), status);
		 } else {
			InputStream isFile = null;
			VisFile visFile = null;
			logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null,
				"Обработка файла обмена с ВИС " + file.getName(), null);
			if (needImportGz(visExchange, file.getName())) {
			   try {
				  visFile = createVisFile(visExchangeConfig, visExchange, "_" + file.getName(),
					  "Файл импортирован с файловой системы", IOUtils.toByteArray(new FileInputStream(dirFile.getAbsolutePath() + java.io.File.separator + file.getName())));
			   } catch (Throwable t) {
				  LOGGER.error(t);
			   }
			   OutputStream out = new FileOutputStream(dirFile.getAbsolutePath() + java.io.File.separator + file.getName());
			   IOUtils.copy(provider.openFileStream(path + file.getName()), out);
			   out.close();
			   isFile = new FileInputStream(dirFile.getAbsolutePath() + java.io.File.separator + ungzip(dirFile.getAbsolutePath(), file.getName()));
			} else if (needImport(visExchange, file.getName())) {
			   isFile = provider.openFileStream(path + file.getName());
			}
			if (isFile != null) {
			   ++status[0];
			   doImportFile(visExchangeConfig, visExchange, visExchangeAttempt, isFile, file, visFile);
			   ++status[1];
			}

		 }
	  }
   }

   @Transactional
   protected void doImportFileSystemExchange(VisExchange visExchange, VisExchangeConfig visExchangeConfig,
											 VisExchangeAttempt visExchangeAttempt)
	   throws Exception {

	  updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.IN_PROCESS.getId());
	  logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null, "Получение доступа к папке обмена с ВИС", null);

	  FileProvider fileProvider = null;
	  try {
		 fileProvider = FileProviderFactory.constructFileSystemProvider(visExchangeConfig.getUri());
	  } catch (PBKConnectionException e) {
		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка импорта с файловой системы: " + e.getMessage());
		 throw new VisFileSysConnectionException(e);
	  }
	  try {
		 Integer[] status = new Integer[] {0, 0};
		 doProcessFiles(fileProvider, visExchange, visExchangeConfig, visExchangeAttempt, "" + SLASH,
			 fileProvider.listFiles(""), status);
		 if (status[0].equals(0)) {
			throw new VisNonFileException("Обработано 0 файлов");
		 }
		 updateExchangeAttempt(visExchangeAttempt,
			 status[0].equals(status[1]) ? VisExchangeStates.SUCCESS.getId() : VisExchangeStates.FAIL.getId(), null,
			 String.format("Успешно обработано %d файлов из %d", status[0], status[1]));
	  } catch (Exception e) {
		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка импорта с файловой системы: " + e.getMessage());
		 throw e;
	  } finally {
		 fileProvider.closeProvider();
	  }
   }

   protected void doImportFile(VisExchangeConfig visExchangeConfig, VisExchange visExchange,
							   VisExchangeAttempt visExchangeAttempt, InputStream isFile, File file, VisFile visFile)
	   throws IOException {
	  logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null,
		  Thread.currentThread().getName() + String.format("Начался импорт файла %s", file.getName()), null);
	  VisExchangeResult visExchangeResult = createExchangeResult(visExchangeAttempt.getId(),
		  VisExchangeStates.IN_PROCESS.getId());
	  try {
		 byte[] bytes = IOUtils.toByteArray(isFile);

		 if (visFile == null) {
			visFile = createVisFile(visExchangeConfig, visExchange, "_" + file.getName(),
				"Файл импортирован с файловой системы", bytes);
		 }
		 updateExchangeResult(visExchangeResult, VisExchangeStates.IN_PROCESS.getId(), visFile, "");

		 ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		 ImportResult<?> importResult = importStream(visExchange, bais);
		 bais.close();
		 if (importResult.getAllCount() != importResult.getProcessingCount()) {
			throw new PBKException(
				String.format("Обработка файла %s завершена с ошибкой. Обработано %d из %d строк",
					file.getName(), importResult.getProcessingCount(), importResult.getAllCount()));
		 }
		 String responseMessage = String.format("Импорт файла %s успешно завершен", file.getName()) + String.format(
			 ", импортировано %d из %d строк", importResult.getSuccessCount(), importResult.getAllCount());

		 updateExchangeResult(
			 visExchangeResult, importResult.getAllCount() == importResult.getSuccessCount()
				 ? VisExchangeStates.SUCCESS.getId() : VisExchangeStates.FAIL.getId(),
			 visFile, responseMessage);
		 logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null, Thread.currentThread().getName()
			 + String.format("Файл от ВИС %s успешно обработан", file.getName()), null);
	  } catch (Exception e) {
		 updateExchangeResult(visExchangeResult, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка обработки файла: " + e.getMessage());
		 logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null, Thread.currentThread().getName()
			 + String.format("Файл от ВИС %s обработан с ошибкой", file.getName()), e);
		 throw e;
	  } finally {
		 isFile.close();
	  }
   }

   @Transactional
   protected void doExportSoapExchange(VisExchange visExchange, VisExchangeConfig visExchangeConfig,
									   VisExchangeAttempt visExchangeAttempt) {
	  AuditObjOperation auditObjOperation = AuditObjOperation.EXPORT;

	  logAudit(AuditLevel.WARNING, auditType, auditObjOperation, null,
		  Thread.currentThread().getName() + "Экспорт по SOAP не реализован", null);
	  throw new UnsupportedOperationException(
		  "Метод doExportSoapExchange для класса " + this.getClass().getName() + " не реализован.");
   }

   @Transactional
   protected void doExportHttpExchange(VisExchange visExchange, VisExchangeConfig visExchangeConfig,
									   VisExchangeAttempt visExchangeAttempt) {
	  AuditObjOperation auditObjOperation = AuditObjOperation.EXPORT;

	  logAudit(AuditLevel.WARNING, auditType, auditObjOperation, null,
		  Thread.currentThread().getName() + "Экспорт по HTTP не реализован", null);
	  throw new UnsupportedOperationException(
		  "Метод doExportHttpExchange для класса " + this.getClass().getName() + " не реализован.");
   }

   @Transactional
   protected void doExportFtpExchange(VisExchange visExchange, VisExchangeConfig visExchangeConfig,
									  VisExchangeAttempt visExchangeAttempt) {
	  AuditObjOperation auditObjOperation = AuditObjOperation.EXPORT;

	  logAudit(AuditLevel.WARNING, auditType, auditObjOperation, null,
		  Thread.currentThread().getName() + "Экспорт по FTP не реализован", null);
	  throw new UnsupportedOperationException(
		  "Метод doExportFtpExchange для класса " + this.getClass().getName() + " не реализован.");
   }

   @Transactional
   protected void doExportFileSystemExchange(VisExchange visExchange, VisExchangeConfig visExchangeConfig,
											 VisExchangeAttempt visExchangeAttempt) {
	  AuditObjOperation auditObjOperation = AuditObjOperation.EXPORT;

	  logAudit(AuditLevel.WARNING, auditType, auditObjOperation, null,
		  Thread.currentThread().getName() + "Экспорт по FileSystem не реализован", null);
	  throw new UnsupportedOperationException(
		  "Метод doExportFileSystemExchange для класса " + this.getClass().getName() + " не реализован.");
   }

   protected boolean needImport(VisExchange visExchange, String name) {
	  return checkFileName(visExchange, name);
   }

   protected boolean needImportGz(VisExchange visExchange, String name) {
	  return checkFileNameGz(visExchange, name);
   }

   protected boolean checkFileName(VisExchange visExchange, String name) {
	  throw new UnsupportedOperationException(
		  "Метод checkFileName для класса " + this.getClass().getName() + " не реализован.");
   }

   protected boolean checkFileNameGz(VisExchange visExchange, String name) {
	  return false;
   }

   protected boolean checkFolderName(VisExchange visExchange, String name) {
	  throw new UnsupportedOperationException(
		  "Метод checkFolderName для класса " + this.getClass().getName() + " не реализован.");
   }

   protected ImportResult<?> importStream(VisExchange visExchange, InputStream is) {
	  return importStream(is);
   }

   protected ImportResult<?> importStream(InputStream is) {
	  throw new UnsupportedOperationException(
		  "Метод importFile для класса " + this.getClass().getName() + " не реализован.");
   }

   protected DefaultSoapClient createSoapClient(BaseSoapClientParameters parameters) {
	  return new DefaultSoapClient(parameters);
   }

   protected DefaultRestClient createRestClient(VisExchange visExchange, BaseHttpClientParameters parameters) {
	  return new DefaultRestClient(parameters);
   }

   protected void importSoap(VisExchange visExchange, BaseSoapClient client)
	   throws SoapClientException {
	  throw new UnsupportedOperationException(
		  "Метод importSoap для класса " + this.getClass().getName() + " не реализован.");
   }

   protected void doRestRequest(VisExchange visExchange, BaseRestClient client)
	   throws HttpClientException {
	  client.callRest(null, null);
   }

   protected boolean processAsZip(VisExchange visExchange, BaseRestClient client) {
	  return false;
   }

   protected VisExchange createExchange(Long configId, Date workDate, Long stateId, String parameter) {
	  VisExchange visExchange = new VisExchange();
	  visExchange.setConfigId(configId);
	  visExchange.setStateId(stateId);
	  visExchange.setWorkDate(workDate);
	  visExchange.setIsDayFail(1);
	  visExchange.setParameter(parameter);
	  visExchange = visExchangeRepository.save(visExchange);
	  return visExchange;
   }

   protected void setExchangeState(Long exchangeId, Long stateId, String message) {
	  VisExchange visExchange = visExchangeRepository.getById(exchangeId);
	  visExchange.setStateId(stateId);
	  visExchange.setErrorContent(message);

	  visExchangeRepository.save(visExchange);
   }

   protected VisExchangeAttempt createExchangeAttempt(Long exchangeId, Long exchangeStateId) {
	  VisExchangeAttempt visExchangeAttempt = new VisExchangeAttempt();
	  visExchangeAttempt.setExchangeId(exchangeId);
	  visExchangeAttempt.setExchangeStateId(exchangeStateId);
	  visExchangeAttempt.setAttemptDate(new Date());

	  visExchangeAttemptRepository.save(visExchangeAttempt);

	  return visExchangeAttempt;
   }

   protected void updateExchangeAttempt(VisExchangeAttempt visExchangeAttempt, Long exchangeStateId) {

	  visExchangeAttempt.setExchangeStateId(exchangeStateId);
	  visExchangeAttemptRepository.save(visExchangeAttempt);
   }

   protected void updateExchangeAttempt(VisExchangeAttempt visExchangeAttempt, Long exchangeStateId, VisFile file,
										String comment) {

	  visExchangeAttempt.setExchangeStateId(exchangeStateId);
	  if (file != null) {
		 visExchangeAttempt.setFileId(file.getId());
	  }
	  visExchangeAttempt.setComment(comment);
	  visExchangeAttemptRepository.save(visExchangeAttempt);
   }

   protected VisExchangeResult createExchangeResult(Long exchangeAttemptId, Long exchangeStateId) {
	  VisExchangeResult visExchangeResult = new VisExchangeResult();
	  visExchangeResult.setExchangeAttemptId(exchangeAttemptId);
	  visExchangeResult.setExchangeStateId(exchangeStateId);
	  visExchangeResult.setResultDate(new Date());

	  visExchangeResultRepository.save(visExchangeResult);

	  return visExchangeResult;
   }

   protected void updateExchangeResult(VisExchangeResult visExchangeResult, Long exchangeStateId) {

	  visExchangeResult.setExchangeStateId(exchangeStateId);
	  visExchangeResultRepository.save(visExchangeResult);
   }

   protected void updateExchangeResult(VisExchangeResult visExchangeResult, Long exchangeStateId, VisFile file,
									   String comment) {

	  visExchangeResult.setExchangeStateId(exchangeStateId);
	  if (file != null) {
		 visExchangeResult.setFileId(file.getId());
	  }
	  visExchangeResult.setComment(comment);
	  visExchangeResultRepository.save(visExchangeResult);
   }

   protected synchronized VisFile createVisFile(VisExchangeConfig visExchangeConfig, VisExchange visExchange,
												String suffix, String description, byte[] content) {
	  VisFile visFile = new VisFile();
	  try {
		 DateFormat df = new SimpleDateFormat("yyyyMMdd");
		 visFile.setName(
			 visExchangeObjectRepository.getById(visExchangeConfig.getExchangeObjectId()).getName()
				 + "_" + df.format(visExchange.getWorkDate()) + suffix);
	  } catch (Throwable t) {
		 visFile.setName(suffix);
	  }
	  try {
		 visFile.setSize(new Long(content.length));
	  } catch (Throwable t) {
		 LOGGER.error(t);
	  }
	  visFile.setDescription(description);
	  try {
		 visFileRepository.createFile(visFile, content);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + String.format("Не удалось сохранить файл в БД: %s", suffix), e);
	  }

	  return visFile;
   }

   protected String ungzip(String path, String filename)
	   throws IOException {

	  byte[] buffer = new byte[1024];
	  String newFileName = filename.substring(0, filename.length() - 3);

	  GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(path + java.io.File.separator + filename));
	  FileOutputStream out = new FileOutputStream(path + java.io.File.separator + newFileName);
	  int len;
	  while ((len = gzis.read(buffer)) > 0) {
		 out.write(buffer, 0, len);
	  }

	  gzis.close();
	  out.close();

	  return newFileName;
   }

   protected String unzip(byte[] zip, String path)
	   throws IOException {

	  byte[] buffer = new byte[1024];

	  java.io.File folder = new java.io.File(path);
	  if (!folder.exists()) {
		 folder.mkdir();
	  }

	  folder = new java.io.File(path + java.io.File.separator + System.currentTimeMillis());
	  if (folder.exists()) {
		 throw new FileAlreadyExistsException(path);
	  }
	  folder.mkdir();

	  ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zip));
	  ZipEntry ze = zis.getNextEntry();

	  while (ze != null) {
		 String fileName = ze.getName();
		 java.io.File newFile = new java.io.File(folder.getAbsolutePath() + java.io.File.separator + fileName);
		 new java.io.File(newFile.getParent()).mkdirs();
		 FileOutputStream fos = new FileOutputStream(newFile);

		 int len;
		 while ((len = zis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		 }
		 fos.close();
		 ze = zis.getNextEntry();
	  }

	  zis.closeEntry();
	  zis.close();

	  return folder.getAbsolutePath() + java.io.File.separator;
   }
}
