package armd.lightHttp.common;

import armd.integration.IntegrationException;

import java.io.File;
import java.io.FileOutputStream;


public class FileLoggerAction
	implements IRequestLoggerAction {

   public static final int logRequest = 1;
   public static final int logResponse = 2;

   protected static String defaultEncoding = "UTF-8";

   protected String dir = "./";
   protected String filePrefix = "packet";
   protected int whatToLog = 0;

   public FileLoggerAction(String dir, String filePrefix, int whatToLog) {
	  this.dir = dir;
	  this.filePrefix = filePrefix;
	  this.whatToLog = whatToLog;
   }

   protected static void string2File(String filename, String content) {
	  try {
		 FileOutputStream fos = new FileOutputStream(filename);
		 fos.write(content.getBytes(defaultEncoding));
		 fos.close();
	  } catch (Throwable t) {
		 t.printStackTrace();
	  }
   }

   @Override
   public void action(IRequestListenerContext requestLogger)
	   throws IntegrationException {
	  if (requestLogger == null) {
		 return;
	  }
	  File dirFile = new File(dir);
	  if (!dirFile.exists()) {
		 dirFile.mkdir();
	  }
	  String filename = dir + filePrefix + System.currentTimeMillis() + ".xml";
	  if (requestLogger.getRawRequest() != null && (whatToLog & logRequest) != 0) {
		 string2File(filename, requestLogger.getRawRequest());
	  }
	  if (requestLogger.getRawResponse() != null && (whatToLog & logResponse) != 0) {
		 string2File(filename, requestLogger.getRawResponse());
	  }
   }
}
