package ru.armd.pbk.utils;

import org.apache.log4j.Logger;
import ru.armd.pbk.aspose.core.IReportType;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

public class AttachReportFileUtils
	extends AttachFileUtils {

   private static final Logger LOG = Logger.getLogger(AttachReportFileUtils.class);


   /**
	* Поместить файлы типа reportType в http-ответ как attachment.
	*
	* @param files      Набор файлов
	* @param response   http-ответ
	* @param reportType Тип файлов
	*/
   public static void composeReportFiles(Map<String, File> files, HttpServletResponse response, IReportType reportType, boolean isInline) {
	  if (files == null || files.isEmpty() || response == null
		  || reportType == null) {
		 return;
	  }

	  if (files.size() > 1) {
		 // Возвращаем zip-архив на клиент.
		 if (!composeZipReportFile(files, response, reportType)) {
			return;
		 }
	  } else {
		 // Файл один, нет смысла помещать его в zip-архив.
		 Entry<String, File> entry = files.entrySet().iterator().next();
		 writeFileToResponse(response, entry.getValue(), entry.getKey(), reportType.getReportFormat().getMimeType(), isInline);
	  }

	  // Удаляем ненужные файлы.
	  for (File file : files.values()) {
		 try {
			Files.delete(Paths.get(file.getPath()));
		 } catch (IOException e) {
			LOG.warn("Невозможно удалить файл отчёта", e);
		 }
	  }
   }

   /**
	* Создать zip-архив из переданных файлов типа reportType и поместить в http-ответ как attachment.
	*
	* @param files      Набор файлов для помещения в архив
	* @param response   http-ответ
	* @param reportType Тип файлов, помещаемых в архив
	* @return флаг успешности операции
	*/
   public static boolean composeZipReportFile(Map<String, File> files, HttpServletResponse response, IReportType reportType) {
	  try {
		 ByteArrayOutputStream out = IOUtils.zipFiles(files);
		 byte[] bytes;
		 if (out == null || (bytes = out.toByteArray()) == null) {
			return false;
		 }
		 ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		 writeStreamToResponse(response, in, reportType.getZipName(),
			 ZIP_CONTENT_TYPE, false);
	  } catch (IOException e) {
		 LOG.error(e);
	  }
	  return true;
   }

   /**
	* Поместить входные потоки типа reportType в http-ответ как attachment.
	*
	* @param streams    входные потоки
	* @param response   http-ответ
	* @param reportType Тип входных потоков
	*/
   public static void composeStreams(Map<String, InputStream> streams, HttpServletResponse response, IReportType reportType) {
	  if (streams == null || streams.isEmpty() || response == null
		  || reportType == null) {
		 return;
	  }

	  if (streams.size() > 1) {
		 // Возвращаем zip-архив на клиент.
		 try {
			ByteArrayOutputStream out = IOUtils.zipStreams(streams);
			byte[] bytes;
			if (out == null || (bytes = out.toByteArray()) == null) {
			   return;
			}
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			writeStreamToResponse(response, in, reportType.getZipName(),
				ZIP_CONTENT_TYPE, false);
		 } catch (IOException e) {
			LOG.error(e);
		 }
	  } else {
		 Entry<String, InputStream> entry = streams.entrySet().iterator()
			 .next();
		 writeStreamToResponse(response, entry.getValue(), entry.getKey(),
			 reportType.getReportFormat().getMimeType(), false);
	  }
   }

}
