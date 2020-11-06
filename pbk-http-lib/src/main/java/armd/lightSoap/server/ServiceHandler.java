package armd.lightSoap.server;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

public interface ServiceHandler {

   String getPath();

   void setPath(String path);

   void setConfiguration(ServiceConfiguration config);

   void processRequest(HttpServletRequest request, HttpServletResponse response)
	   throws IllegalAccessException, IOException, UnsupportedEncodingException, InvocationTargetException;
}
