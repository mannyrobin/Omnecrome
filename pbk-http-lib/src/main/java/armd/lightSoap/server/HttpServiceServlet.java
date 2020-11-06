package armd.lightSoap.server;


import armd.core.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.web.util.UriTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class HttpServiceServlet
	extends HttpServlet {

   private static final long serialVersionUID = 0;

   private static final Logger logger = Logger.getLogger(HttpServiceServlet.class);


   private static final Object soapHandlerSync = new Object();

   private static Configuration configuration = null;

   private static ServiceHandler getHandler(String requestPath)
	   throws ClassNotFoundException, InstantiationException, IllegalAccessException {

	  if (configuration == null) {
		 synchronized (soapHandlerSync) {
			if (configuration == null) {
			   configuration = armd.core.ContextFactory.get(Configuration.class);
			}
		 }
	  }

	  if (configuration == null) {
		 return null;
	  }


	  List<ServiceConfiguration> serviceConfigurations = configuration.getServices();

	  for (int i = 0; i < serviceConfigurations.size(); i++) {

		 ServiceConfiguration serviceConfiguration = serviceConfigurations.get(i);

		 String servicePath = serviceConfiguration.getPath();
		 if (servicePath == null || servicePath.isEmpty()) {
			continue;
		 }

		 UriTemplate template = new UriTemplate(servicePath + "{path:.*}");

		 if (template.matches(requestPath)) {

			String typeName = serviceConfiguration.getTypeName();

			@SuppressWarnings("unchecked")
			ServiceHandler res = (ServiceHandler) Class.forName(typeName).newInstance();
			res.setPath(servicePath);
			res.setConfiguration(serviceConfiguration);
			return res;
		 }
	  }


	  return null;
   }


   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp)
	   throws ServletException, IOException {

	  try {


		 resp.setContentType("text/xml");
		 resp.setHeader("Cache-Control", "no-cache");
		 resp.setDateHeader("Last-Modified", DateUtils.now().getTime());
		 resp.setDateHeader("Expires", 0);


		 ServiceHandler serviceHandler = getHandler(req.getServletPath());


		 if (serviceHandler != null) {
			serviceHandler.processRequest(req, resp);
		 } else {
			resp.setStatus(404);
		 }

	  } catch (Exception ex) {
		 logger.error(ex.getMessage(), ex);
		 resp.setStatus(500);
	  }
   }

}

