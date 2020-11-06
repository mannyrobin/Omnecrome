package armd.lightSoap.server;


import armd.core.DateUtils;
import armd.core.RefVal;
import armd.lightHttp.common.XmlDocument;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpression;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;

public abstract class Service
	implements ServiceHandler {


   private static final Logger logger = Logger.getLogger(Service.class);

   private static final Object serviceSync = new Object();

   protected static final String soapEnvNamespace = "http://schemas.xmlsoap.org/soap/envelope/";
   private static final String soapWsdlNamespace = "wsdl";
   private static final String xsdNamespace = "http://www.w3.org/2001/XMLSchema";

   protected static final String soapEnvPrefix = "soapEnv";
   private static final String soapWsdlPrefix = "wsdl";
   private static final String xsdPrefix = "xsd";

   private static final String encodingName = "utf-8";

   private String path = null;
   protected ServiceConfiguration config;

   private Timestamp requestStart = null;
   private Timestamp requestEnd = null;

   private static XPathExpression bodyPath = null;


   private static final HashMap<Class<?>, Unmarshaller> unmarshallersStorage = new HashMap<Class<?>, Unmarshaller>();
   private static final HashMap<Class<?>, Marshaller> marshallersStorage = new HashMap<Class<?>, Marshaller>();

   private static final HashMap<Class<?>, ServiceWsdl> wsdlStorage = new HashMap<Class<?>, ServiceWsdl>();


   public static final Unmarshaller getUnmarshaller(Class<?> cl)
	   throws JAXBException {
	  synchronized (serviceSync) {
		 if (unmarshallersStorage.containsKey(cl)) {
			return unmarshallersStorage.get(cl);
		 } else {
			JAXBContext jaxbContext = JAXBContext.newInstance(cl);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			unmarshallersStorage.put(cl, unmarshaller);
			return unmarshaller;
		 }
	  }
   }

   protected static final Marshaller getMarshaller(Class<?> cl)
	   throws JAXBException {
	  synchronized (serviceSync) {
		 if (marshallersStorage.containsKey(cl)) {
			return marshallersStorage.get(cl);
		 } else {
			JAXBContext jaxbContext = JAXBContext.newInstance(cl);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshallersStorage.put(cl, marshaller);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			return marshaller;
		 }
	  }
   }


   private static final ServiceWsdl getWsdl(boolean isPlaneWsdl, Class<?> cl, String baseAddress)
	   throws JAXBException, TransformerException, IOException, URISyntaxException, ParserConfigurationException {
	  synchronized (serviceSync) {

		 ServiceWsdl wsdl = wsdlStorage.get(cl);

		 if (wsdl == null) {
			wsdl = new ServiceWsdl(isPlaneWsdl, cl, baseAddress);
			wsdlStorage.put(cl, wsdl);
		 }

		 return wsdl;
	  }
   }

   private ResponseBuffer responseBuffer = null;
   private RequestBuffer requestBuffer = null;

   private String currentAction = null;

   private RequestContext requestContext = new RequestContext();

   protected Object getParameter(String name) {
	  return requestContext.get(name);
   }

   protected void setParameter(String name, Object value) {
	  requestContext.put(name, value);
   }

   protected ResponseBuffer getResponseBuffer() {
	  return responseBuffer;
   }

   protected String getCurrentAction() {
	  return currentAction;
   }

   protected RequestBuffer getRequestBuffer() {
	  return requestBuffer;
   }

   @Override
   public String getPath() {
	  return path;
   }

   @Override
   public void setPath(String path) {
	  this.path = path;
   }

   public void setConfiguration(ServiceConfiguration config) {
	  this.config = config;
   }


   protected String extractAction(HttpServletRequest request) {
	  String action = request.getHeader("SOAPAction");
	  if (action == null) {
		 action = "";
	  }
	  action = action.trim();

	  if (action.startsWith("\"") && action.endsWith("\"")) {
		 action = action.substring(1, action.length() - 1);
	  }

	  return action;
   }


   protected void writeFault(String faultCode, String faultString, String details)
	   throws IOException, TransformerException, URISyntaxException, ParserConfigurationException {
	  writeFault(500, faultCode, faultString, details);
   }

   protected void writeFault(int httpCode, String faultCode, String faultString, String details)
	   throws IOException, TransformerException, URISyntaxException, ParserConfigurationException {


	  responseBuffer.setStatus(httpCode);

	  XmlDocument document = new XmlDocument("Envelope", soapEnvNamespace, soapEnvPrefix);

	  Element body = document.addElement(document.getRootElement(), "Body", soapEnvNamespace, soapEnvPrefix);
	  Element fault = document.addElement(body, "Fault", soapEnvNamespace, soapEnvPrefix);

	  document.addTextElement(fault, "faultcode", faultCode);
	  document.addTextElement(fault, "faultstring", faultString);
	  document.addCDataElement(fault, "details", details);


	  document.write(responseBuffer);

   }


   private void writeTechnicalError(Exception ex) {


	  try {

		 if (SoapException.class.isInstance(ex)) {
			SoapException soapException = (SoapException) ex;

			writeFault(500, soapException.getCode(), soapException.getMessage(), soapException.getDetails());
		 } else if (SoapSecurityException.class.isInstance(ex)) {
			SoapSecurityException soapException = (SoapSecurityException) ex;

			writeFault(soapException.getHttpCode(), soapException.getCode(),
				soapException.getMessage(), soapException.getDetails());
		 } else {
			StringWriter sw = new StringWriter();

			sw.write(ex.toString() + "\n");
			StackTraceElement[] elements = ex.getStackTrace();
			for (StackTraceElement element : elements) {
			   sw.write(element.toString() + "\n");
			}

			String faultDetails = sw.toString();


			writeFault("Generic error", ex.getMessage(), sw.toString());

		 }

		 responseBuffer.setStatus(500);

	  } catch (Exception lex) {
		 logger.error(lex.getMessage(), lex);
	  }
   }


   protected boolean isPlaneWsdl() {
	  return false;
   }

   public String getBaseAddress()
	   throws MalformedURLException {
	  URL url = new URL(getRequestBuffer().getRequest().getRequestURL().toString());
	  return url.getProtocol() + "://" + url.getAuthority() + getPath();
   }


   protected RequestBuffer readRequest(HttpServletRequest request)
	   throws IOException, SoapSecurityException {
	  return new RequestBuffer(request);
   }

   protected void writeResponse(HttpServletResponse response) {

	  RefVal<String> content = new RefVal<String>();

	  try {
		 responseBuffer.write(response, content);
	  } catch (Exception ex) {
		 logger.error(ex.getMessage(), ex);
	  }
   }

   protected Timestamp getRequestStart() {
	  return requestStart;
   }

   protected Timestamp getRequestEnd() {
	  return requestEnd;
   }

   private String extractBodyContent(Node body)
	   throws ParserConfigurationException, TransformerException {
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  factory.setNamespaceAware(true);
	  DocumentBuilder documentBuilder = factory.newDocumentBuilder();
	  Document document = documentBuilder.newDocument();

	  body = document.importNode(body, true);
	  document.appendChild(body);
	  XmlDocument bodyDocument = new XmlDocument(document);

	  StringWriter bodyWriter = new StringWriter();
	  bodyDocument.write(bodyWriter);

	  return bodyWriter.toString();
   }


   @Override
   public void processRequest(HttpServletRequest request, HttpServletResponse response)
	   throws IllegalAccessException, UnsupportedEncodingException, InvocationTargetException {


	  this.requestStart = DateUtils.now();

	  try {

		 this.currentAction = extractAction(request);

		 this.responseBuffer = new ResponseBuffer();
		 this.requestBuffer = readRequest(request);

		 String baseAddress = getBaseAddress();


		 ServiceWsdl wsdl = getWsdl(isPlaneWsdl(), this.getClass(), getBaseAddress());

		 if (wsdl.isWsdlRequest(getRequestBuffer())) {
			wsdl.write(getRequestBuffer(), getResponseBuffer());
		 } else {
			String action = getCurrentAction();
			if (action == null) {
			   action = "";
			}


			Method[] methods = this.getClass().getMethods();

			boolean isHandled = false;

			for (Method m : methods) {
			   ServiceAction serviceAction = m.getAnnotation(ServiceAction.class);
			   if (serviceAction != null) {


				  if (action.equalsIgnoreCase(serviceAction.value())) {

					 String serviceActionValue = serviceAction.value();
					 if (serviceActionValue == null || serviceActionValue.isEmpty()) {
						continue;
					 }

					 Class[] argumentTypes = m.getParameterTypes();
					 if (argumentTypes.length != 1) {
						throw new IllegalArgumentException(String.format("Count of arguments of method '%s' must one", this.getClass().getCanonicalName() + "." + m.getName()));
					 }

					 XmlDocument requestXml = XmlDocument.load(this.getRequestBuffer());
					 Element body = requestXml.getElement("Envelope", soapEnvNamespace).getElement("Body", soapEnvNamespace).getFirstElement().getElement();


					 Object requestObject = null;
					 if (body != null) {

						requestBuffer.setBodyContent(extractBodyContent(body));

						Unmarshaller requestUnmarshaller = getUnmarshaller(argumentTypes[0]);
						requestObject = requestUnmarshaller.unmarshal(body);

					 }

					 Object responseObject = null;
					 try {
						responseObject = m.invoke(this, requestObject);
					 } catch (InvocationTargetException ex) {
						Throwable targetException = ex.getTargetException();

						if (SoapException.class.isInstance(targetException)) {
						   throw (Exception) targetException;
						} else {
						   throw new Exception("Unknown error", targetException);
						}

					 }

					 writeObject(responseObject, responseBuffer);

					 isHandled = true;

					 break;
				  }

			   }
			}

			if (!isHandled) {
			   responseBuffer.setStatus(404);
			}
		 }
	  } catch (Exception ex) {
		 logger.error(ex.getMessage(), ex);
		 writeTechnicalError(ex);
	  }

	  requestEnd = DateUtils.now();

	  writeResponse(response);
   }

   protected void writeObject(Object responseObject, ResponseBuffer responseBuffer)
	   throws Exception {
	  XmlDocument document = new XmlDocument("Envelope", soapEnvNamespace, soapEnvPrefix);
	  Element responseHeader = document.addElement(document.getRootElement(), "Header", soapEnvNamespace, soapEnvPrefix);
	  Element responseBody = document.addElement(document.getRootElement(), "Body", soapEnvNamespace, soapEnvPrefix);
	  if (responseObject != null) {
		 Marshaller responseMarshaller = getMarshaller(responseObject.getClass());
		 responseMarshaller.marshal(responseObject, responseBody);
	  }
	  document.write(responseBuffer);
   }
}
