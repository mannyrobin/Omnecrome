package ru.armd.pbk.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.web.util.UriUtils;
import ru.armd.pbk.utils.http.HttpContentTypes;
import ru.armd.pbk.utils.http.HttpHeaderUtils;
import ru.armd.pbk.utils.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Утилита для работы с файлами.
 * Реализованы методы записи файла в ответ.
 */
public class AttachFileUtils {

    protected static final Logger LOG = Logger.getLogger(AttachFileUtils.class);
    protected static final String ZIP_CONTENT_TYPE = "application/zip";
    protected static final String CONTENT_DISPOSITION_HEADER = "Content-Disposition";
    protected static final String CONTENT_DISPOSITION_HEADER_VALUE_PATTERN = "attachment; filename*=UTF-8''%s";
	protected static final String CONTENT_DISPOSITION_HEADER_VALUE_PATTERN_FOR_DOWNLOADING = "attachment; filename=";
	protected static final String CONTENT_DISPOSITION_HEADER_VALUE_PATTERN_FOR_INLINE_DOWNLOADING = "inline; filename=";
	private static final int DEFAULT_BUFFER_SIZE = 20480;
	private static final long DEFAULT_EXPIRE_TIME = 604800000L;


   /**
	* Записать файл в ответ.
	*
	* @param file     файл.
	* @param request  запрос.
	* @param response ответ.
	* @throws IOException
	*/
   public static void writeFileToResponse(File file, HttpServletRequest request, HttpServletResponse response)
	   throws IOException {
	  if (file != null) {
		 String fileName = file.getName();
		 try {
			URI uri = new URI(null, null, fileName, null);
			fileName = uri.toASCIIString();
		 } catch (URISyntaxException e) {
			LOG.error("error write file to response", e);
		 }

		 response.setCharacterEncoding("UTF-8");
		 response.setHeader("Content-Disposition",
			 String.format("attachment; filename=\"%s\"", fileName));
		 response.setHeader("Content-Length", String.valueOf(file.length()));

		 OutputStream out = response.getOutputStream();
		 FileInputStream in = new FileInputStream(file);
		 IOUtils.copyStream(in, out);
	  }
   }

   /**
	* Метод генерации архива по списку файлов.
	*
	* @param files    файлы.
	* @param filename название архива.
	* @return файл архива.
	* @throws IOException
	*/
   public static File zip(List<File> files, String filename)
	   throws IOException {
	  File zipfile = new File(filename);
	  // Create a buffer for reading the files
	  byte[] buf = new byte[1024];
	  ZipOutputStream out = null;
	  // create the ZIP file
	  out = new ZipOutputStream(new FileOutputStream(zipfile));
	  // compress the files
	  Map<String, Integer> usedNames = new HashMap<>();
	  for (int i = 0; i < files.size(); i++) {
		 FileInputStream in = new FileInputStream(files.get(i).getName());

		 String fName = FilenameUtils.getBaseName(files.get(i).getName());
		 String ext = FilenameUtils.getExtension(files.get(i).getName());
		 if (usedNames.get(fName) != null) {
			Integer curCount = usedNames.get(fName);
			usedNames.put(fName, curCount + 1);
			fName += "(" + curCount + ")";
		 } else {
			usedNames.put(fName, 1);
		 }
		 // add ZIP entry to output stream
		 out.putNextEntry(new ZipEntry(fName + "." + ext));
		 // transfer bytes from the file to the ZIP file
		 int len;
		 while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		 }
		 // complete the entry
		 out.closeEntry();
		 in.close();
	  }
	  // complete the ZIP file
	  out.close();
	  return zipfile;
   }

   /**
	* Записывает файл в response.
	*
	* @param response    Response, в который производится запись.
	* @param file        Файл.
	* @param fileName    Имя файла для отправки на клиент.
	* @param contentType MIME-тип содержимого.
	*/
   public static void writeFileToResponse(HttpServletResponse response, File file, String fileName, String contentType, boolean isInline) {
	  if (response == null || file == null || fileName == null
		  || contentType == null) {
		 return;
	  }

	  FileInputStream in;
	  try {
		 in = new FileInputStream(file);
	  } catch (IOException e) {
		 LOG.error(e);

		 return;
	  }

	  writeStreamToResponse(response, in, fileName, contentType, isInline);
   }

   /**
	* Записывает входной поток в response.
	*
	* @param response    Response, в который производится запись.
	* @param in          Входной поток.
	* @param fileName    Имя файла для отправки на клиент.
	* @param contentType MIME-тип содержимого.
	*/
   public static void writeStreamToResponse(HttpServletResponse response,
											InputStream in, String fileName, String contentType, boolean isInline) {
	  if (response == null || in == null || fileName == null
		  || contentType == null) {

		 return;
	  }
	  try {
		 response.setContentType(contentType);
		 if (isInline) {
			response.setHeader(CONTENT_DISPOSITION_HEADER, CONTENT_DISPOSITION_HEADER_VALUE_PATTERN_FOR_INLINE_DOWNLOADING + UriUtils.encode(fileName, "UTF-8"));
		 } else {
		 	response.setHeader(CONTENT_DISPOSITION_HEADER, CONTENT_DISPOSITION_HEADER_VALUE_PATTERN_FOR_DOWNLOADING + UriUtils.encode(fileName, "UTF-8"));
		 }
		 OutputStream out = response.getOutputStream();
		 IOUtils.copyStream(in, out);
	  } catch (IOException e) {
		 LOG.error(e);
	  }
   }

	public static void writeVideoToResponse(URL url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (url == null) {
			return;
		}
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String fileName = HttpHeaderUtils.filename(conn.getHeaderField(HttpHeaders.CONTENT_DISPOSITION));
		String contentType = HttpContentTypes.VIDEO_MP4;
		writeStreamToResponse(response,conn.getInputStream(),fileName,contentType, false);
	}

   public static void attachBytesToResponse(String fileName, HttpServletRequest request, HttpServletResponse response) {
	  if (fileName == null || request == null || response == null) {
		 return;
	  }

	  String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
	  if (contentType == null) {
		 return;
	  }

	  try {
		 response.setContentType(contentType);
		 response.setHeader(CONTENT_DISPOSITION_HEADER, String.format(CONTENT_DISPOSITION_HEADER_VALUE_PATTERN,
			 UriUtils.encode(fileName, "UTF-8")));
	  } catch (IOException e) {
		 LOG.error(e);
	  }
   }

   /**
	* Записать видео в ответ.
	*
	* @param url      урл на видео.
	* @param request  запрос.
	* @param response ответ.
	* @throws IOException
	*/
   public static void writeVideoToPartResponse(URL url, HttpServletRequest request, HttpServletResponse response)
	   throws IOException {
	  if (url == null) {
		 return;
	  }
	  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	  String fileName = HttpHeaderUtils.filename(conn.getHeaderField(HttpHeaders.CONTENT_DISPOSITION));
	  Long length = Long.valueOf(conn.getHeaderField(HttpHeaders.CONTENT_LENGTH));
	  String ifNoneMatch = request.getHeader(HttpHeaders.IF_NONE_MATCH);
	  if (ifNoneMatch != null && HttpHeaderUtils.matches(ifNoneMatch, fileName)) {
		 response.setHeader(HttpHeaders.ETAG, fileName);
		 response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
		 return;
	  }

	  String ifMatch = request.getHeader(HttpHeaders.IF_MATCH);
	  if (ifMatch != null && !HttpHeaderUtils.matches(ifMatch, fileName)) {
		 response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
		 return;
	  }

	  String range = request.getHeader(HttpHeaders.RANGE);
	  String contentType = HttpContentTypes.VIDEO_MP4;
	  List<Range> ranges = new ArrayList<Range>();
	  Range full = new Range(0, length - 1, length);

	  if (range != null) {
		 if (!range.matches("^bytes=\\d*-\\d*(,\\d*-\\d*)*$")) {
			response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes */" + length);
			response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
			return;
		 }

		 String ifRange = request.getHeader(HttpHeaders.IF_RANGE);
		 if (ifRange != null && !ifRange.equals(fileName)) {
			try {
			   long ifRangeTime = request.getDateHeader(HttpHeaders.IF_RANGE);
			   if (ifRangeTime != -1) {
				  ranges.add(full);
			   }
			} catch (IllegalArgumentException ignore) {
			   ranges.add(full);
			}
		 }

		 if (ranges.isEmpty()) {
			for (String part : range.substring(6).split(",")) {
			   try {
				  ranges.add(new Range(part, length));
			   } catch (IllegalArgumentException e) {
				  response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes */" + length);
				  response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
				  return;
			   }
			}
		 }
	  }

	  String accept = request.getHeader(HttpHeaders.ACCEPT);
	  String disposition = accept != null && HttpHeaderUtils.accepts(accept, contentType) ? "inline" : "attachment";

	  response.reset();
	  response.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
	  response.setHeader(HttpHeaders.CONTENT_DISPOSITION, disposition + ";filename=" + fileName);
	  response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
	  response.setHeader(HttpHeaders.ETAG, fileName);
	  response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + DEFAULT_EXPIRE_TIME);

	  try (InputStream input = conn.getInputStream();
		   OutputStream output = response.getOutputStream()) {

		 if (ranges.isEmpty() || ranges.get(0) == full) {
			response.setContentType(contentType);
			response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes " + full.getStart() + "-" + full.getEnd() + "/" + full.getTotal());
			response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(full.getLength()));
			IOUtils.copyStream(input, output, length, full.getStart(), full.getLength(), DEFAULT_BUFFER_SIZE);
		 } else if (ranges.size() == 1) {
			Range r = ranges.get(0);
			response.setContentType(contentType);
			response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes " + r.getStart() + "-" + r.getEnd() + "/" + r.getTotal());
			response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(r.getLength()));
			response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
			IOUtils.copyStream(input, output, length, r.getStart(), r.getLength(), DEFAULT_BUFFER_SIZE);
		 }
	  }
   }

}
