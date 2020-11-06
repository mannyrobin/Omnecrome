package armd.lightRest.client;

import armd.integration.IntegrationException;
import armd.lightHttp.common.IRequestListenerContext;
import armd.lightHttp.common.IRequestLoggerAction;

import java.io.File;
import java.io.FileOutputStream;


public class RestLoggerAction
	implements IRequestLoggerAction {

   public static final int logRequest = 1;
   public static final int logResponse = 2;

   protected String dir = "./";
   protected String filePrefix = "packet";
   protected int whatToLog = 0;

   public RestLoggerAction(String dir, String filePrefix, int whatToLog) {
	  this.dir = dir;
	  this.filePrefix = filePrefix;
	  this.whatToLog = whatToLog;
   }

   protected static void bytes2File(String filename, byte[] bytes) {
	  try {
		 FileOutputStream fos = new FileOutputStream(filename);
		 fos.write(bytes);
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
	  String filename = dir + filePrefix + System.currentTimeMillis();
	  if (requestLogger.getAnyObjects() != null && requestLogger.getAnyObjects().containsKey("reqBytes") && (whatToLog & logRequest) != 0) {
		 bytes2File(filename, (byte[]) requestLogger.getAnyObjects().get("reqBytes"));
	  }
	  if (requestLogger.getAnyObjects() != null && requestLogger.getAnyObjects().containsKey("respBytes") && (whatToLog & logResponse) != 0) {
		 bytes2File(filename, (byte[]) requestLogger.getAnyObjects().get("respBytes"));
	  }
   }
}
