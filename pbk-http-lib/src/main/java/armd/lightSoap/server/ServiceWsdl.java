package armd.lightSoap.server;


import armd.core.StringMap;
import armd.lightHttp.common.XmlDocument;
import org.springframework.web.util.UrlPathHelper;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMResult;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.w3c.dom.Node.ELEMENT_NODE;

public class ServiceWsdl {

   private boolean isPlaneWsdl = true;

   private URL baseAddress = null;
   private Class<?> serviceClass = null;
   private XmlDocument wsdl = null;
   private Method[] methods = null;
   private ServiceSchema[] serviceSchemas = new ServiceSchema[] {};


   private HashMap<Class<?>, WsdlSchemaClassName> serviceSchemasClassNames = new HashMap<Class<?>, WsdlSchemaClassName>();

   private static final String xsdNamespace = "http://www.w3.org/2001/XMLSchema";
   private static final String xsdPrefix = "xsd";
   private static final String wsdlNamespace = "http://schemas.xmlsoap.org/wsdl/";
   private static final String wsdlPrefix = "wsdl";
   private static final String wsdlSoapNamespace = "http://schemas.xmlsoap.org/wsdl/soap/";
   private static final String wsdlSoapPrefix = "wsdl-soap";

   private static final String soapHttpBindingNamespace = "http://schemas.xmlsoap.org/soap/http";

   private static final String tnsPrefix = "tns";

   private static final String xsdExtension = "xsd";


   private static final String xsdPath = "schemas";


   protected Method[] getMethods() {
	  if (methods == null) {

		 List<Method> methodList = new ArrayList<Method>();

		 Method[] allMethods = serviceClass.getMethods();

		 for (Method m : allMethods) {
			ServiceAction serviceAction = m.getAnnotation(ServiceAction.class);
			if (serviceAction != null) {

			   Class[] argumentTypes = m.getParameterTypes();
			   if (argumentTypes.length != 1) {
				  throw new IllegalArgumentException(String.format("Count of arguments of method '%s' must one", serviceClass.getCanonicalName() + "." + m.getName()));
			   }

			   methodList.add(m);
			}
		 }

		 methods = methodList.toArray(new Method[0]);

	  }
	  return methods;
   }

   protected Class<?>[] getMethodsClasses() {
	  HashSet<Class<?>> res = new HashSet<Class<?>>();

	  Method[] methods = getMethods();

	  for (Method m : methods) {
		 res.add(m.getReturnType());
		 res.add(m.getParameterTypes()[0]);
	  }

	  return res.toArray(new Class<?>[0]);
   }


   protected String getMessageName(Method mi) {
	  return mi.getName();
   }

   protected String getClassNameWithPrefix(Class<?> cl) {

	  WsdlSchemaClassName schemaClassName = serviceSchemasClassNames.get(cl);
	  if (schemaClassName == null) {
		 return cl.getName();
	  }

	  String prefix = wsdl.resolveNamespacePrefix(schemaClassName.getNamespace());


	  return prefix + ":" + schemaClassName.getName();
   }


   protected String getPortTypeName() {
	  ServiceDefinition serviceDefinition = serviceClass.getAnnotation(ServiceDefinition.class);
	  if (serviceDefinition != null) {
		 if (serviceDefinition.name() != null && !serviceDefinition.name().isEmpty()) {
			return serviceDefinition.name() + "PortType";
		 }
	  }
	  return serviceClass.getSimpleName() + "PortType";
   }

   protected String getPortName() {
	  ServiceDefinition serviceDefinition = serviceClass.getAnnotation(ServiceDefinition.class);
	  if (serviceDefinition != null) {
		 if (serviceDefinition.name() != null && !serviceDefinition.name().isEmpty()) {
			return serviceDefinition.name() + "Port";
		 }
	  }
	  return serviceClass.getSimpleName() + "Port";
   }

   protected String getServiceName() {
	  ServiceDefinition serviceDefinition = serviceClass.getAnnotation(ServiceDefinition.class);
	  if (serviceDefinition != null) {
		 if (serviceDefinition.name() != null && !serviceDefinition.name().isEmpty()) {
			return serviceDefinition.name();
		 }
	  }
	  return serviceClass.getSimpleName();
   }

   protected String getBindingName() {
	  ServiceDefinition serviceDefinition = serviceClass.getAnnotation(ServiceDefinition.class);
	  if (serviceDefinition != null) {
		 if (serviceDefinition.name() != null && !serviceDefinition.name().isEmpty()) {
			return serviceDefinition.name() + "Binding";
		 }
	  }
	  return serviceClass.getSimpleName() + "Binding";
   }

   protected String getActionName(Method method) {
	  return method.getName();
   }

   protected String getServiceNamespace() {
	  ServiceDefinition serviceDefinition = serviceClass.getAnnotation(ServiceDefinition.class);
	  if (serviceDefinition != null) {
		 if (serviceDefinition.namespace() != null && !serviceDefinition.namespace().isEmpty()) {
			return serviceDefinition.namespace();
		 }
	  }
	  return "#" + serviceClass.getSimpleName();
   }

   protected XmlDocument createWsdlDocument()
	   throws ParserConfigurationException {
	  XmlDocument res = new XmlDocument("definitions", wsdlNamespace, wsdlPrefix);

	  res.getDocument().setXmlStandalone(true);

	  res.resolveNamespacePrefix(XMLConstants.W3C_XML_SCHEMA_NS_URI, "xsd");
	  res.resolveNamespacePrefix(wsdlSoapNamespace, wsdlSoapPrefix);
	  res.resolveNamespacePrefix(getServiceNamespace(), tnsPrefix);

	  res.addAttr(res.getRootElement(), "targetNamespace", getServiceNamespace());

	  return res;
   }


   public ServiceWsdl(boolean isPlaneWsdl, Class<?> cl, String baseUrl)
	   throws IOException, JAXBException, ParserConfigurationException, URISyntaxException, TransformerException {


	  this.serviceClass = cl;
	  this.baseAddress = new URL(baseUrl);
	  this.isPlaneWsdl = isPlaneWsdl;

	  this.wsdl = createWsdlDocument();

	  resolveSchemas();

	  defineTypes();

	  defineMessages();

	  definePortType();

	  defineBinding();

	  defineService();
   }

   protected StringMap parseQueryParameters(HttpServletRequest request) {

	  UrlPathHelper helper = new UrlPathHelper();
	  String qs = request.getQueryString();
	  StringMap qm = new StringMap();
	  if (qs == null) {
		 return qm;
	  }


	  String contentEncoding = request.getCharacterEncoding();
	  if (contentEncoding == null || contentEncoding.isEmpty()) {
		 contentEncoding = "utf-8";
	  }

	  String[] keyValues = qs.split("&");
	  for (String keyValue : keyValues) {
		 if (keyValue == null) {
			continue;
		 }
		 String key = keyValue;
		 String value = "";
		 int firstIndex = keyValue.indexOf('=');
		 if (!((firstIndex == -1) || (firstIndex == keyValue.length() - 1))) {
			key = keyValue.substring(0, firstIndex);
			value = keyValue.substring(firstIndex + 1);
		 }


		 qm.put(key, value);
	  }


	  return qm;
   }

   protected String extractLastPath(String baseAddress)
	   throws MalformedURLException {
	  URL pathURL = new URL(baseAddress);
	  String path = pathURL.getPath();

	  String[] parts = path.split("[/\\\\]");

	  return parts[parts.length - 1];
   }

   protected ServiceSchema getSchemaByRequest(HttpServletRequest request)
	   throws MalformedURLException {
	  URL requestUrl = new URL(request.getRequestURL().toString());
	  String requestPath = requestUrl.getPath();
	  for (ServiceSchema serviceSchema : serviceSchemas) {

		 URL schemaLocation = new URL(baseAddress + "/" + xsdPath + '/' + serviceSchema.getName());

		 if (schemaLocation.getPath().equalsIgnoreCase(requestPath)) {
			return serviceSchema;
		 }
	  }
	  return null;
   }


   protected boolean isWsdlRequest(RequestBuffer requestBuffer)
	   throws MalformedURLException {
	  if (parseQueryParameters(requestBuffer.getRequest()).containsKey("wsdl")) {
		 return true;
	  }

	  if (requestBuffer.getRequest().getServletPath().matches(".*[/]" + xsdPath + "[/].*[.]" + xsdExtension + "")) {
		 return true;
	  }

	  return false;
   }


   protected void write(RequestBuffer requestBuffer, ResponseBuffer responseBuffer)
	   throws TransformerException, IOException {
	  if (parseQueryParameters(requestBuffer.getRequest()).containsKey("wsdl")) {
		 wsdl.write(responseBuffer);
	  } else {
		 ServiceSchema serviceSchema = getSchemaByRequest(requestBuffer.getRequest());
		 if (serviceSchema != null) {

			serviceSchema.write(responseBuffer);

		 } else {
			responseBuffer.setStatus(404);
		 }
	  }
   }


   private void resolveSchemas()
	   throws JAXBException, IOException, URISyntaxException, TransformerException {

	  Class<?>[] methodClasses = getMethodsClasses();

	  for (Class<?> cl : methodClasses) {
		 if ("void".equals(cl.getName())) {
			continue;
		 }

		 XmlRootElement rootAnnotation = cl.getAnnotation(XmlRootElement.class);
		 XmlSchema xmlSchema = cl.getPackage().getAnnotation(XmlSchema.class);

		 if (rootAnnotation == null) {
			throw new IllegalArgumentException(String.format("Class '%s' must contains attribute XmlRootElement", cl.getCanonicalName()));
		 }

		 String targetNamespace = rootAnnotation.namespace();
		 String elementName = rootAnnotation.name();
		 String prefix = null;

		 if (targetNamespace == null || targetNamespace.isEmpty() || targetNamespace.equals("##default")) {

			if (xmlSchema == null) {
			   throw new IllegalArgumentException(String.format("The package of class '%s' must contains attribute XmlSchema to resolve namespace", cl.getCanonicalName()));
			}

			targetNamespace = xmlSchema.namespace();

			if (targetNamespace == null || targetNamespace.isEmpty()) {
			   throw new IllegalArgumentException(String.format("Cannot resolve namespace for class '%s'", cl.getCanonicalName()));
			}

		 }


		 serviceSchemasClassNames.put(cl, new WsdlSchemaClassName(cl, elementName, targetNamespace));

		 if (xmlSchema.xmlns() != null) {
			for (XmlNs xmlNs : xmlSchema.xmlns()) {
			   wsdl.resolveNamespacePrefix(xmlNs.namespaceURI(), xmlNs.prefix());
			}
		 }

	  }


	  JAXBContext jaxbContext = JAXBContext.newInstance(methodClasses);
	  SchemaOutputResolver schemaOutputResolver = new SchemaOutputResolver(xsdExtension);
	  jaxbContext.generateSchema(schemaOutputResolver);

	  SchemaOutputEntity[] schemaOutputEntities = schemaOutputResolver.getResults();

	  ArrayList<ServiceSchema> serviceSchemaList = new ArrayList<ServiceSchema>();
	  for (SchemaOutputEntity schemaOutputEntity : schemaOutputEntities) {
		 DOMResult domResult = schemaOutputEntity.getDomResult();

		 XmlDocument doc = new XmlDocument((Document) domResult.getNode());


		 ServiceSchema serviceSchema = new ServiceSchema(schemaOutputEntity.getNs(), domResult.getSystemId(), doc);

		 serviceSchemaList.add(serviceSchema);
	  }

	  this.serviceSchemas = serviceSchemaList.toArray(new ServiceSchema[] {});
   }


   private void defineTypes()
	   throws IOException {

	  Element types = wsdl.addElement(wsdl.getRootElement(), "types", wsdlNamespace, wsdlPrefix);


	  for (ServiceSchema serviceSchema : serviceSchemas) {


		 if (isPlaneWsdl) {

			XmlDocument schemaDocument = serviceSchema.getContent();

			Element element = schemaDocument.getRootElement();

			NodeList childNodes = element.getChildNodes();

			List<Node> importElements = new ArrayList<Node>();


			for (int i = 0; i < childNodes.getLength(); i++) {

			   Node node = childNodes.item(i);
			   if (node.getNodeType() == ELEMENT_NODE) {
				  if (node.getLocalName().equals("import")) {
					 if (XMLConstants.W3C_XML_SCHEMA_NS_URI.equals(node.getNamespaceURI().trim())) {

						//  importElements.add(node);

						Element importElement = Element.class.cast(node);
						Attr schemaLocation = importElement.getAttributeNode("schemaLocation");
						if (schemaLocation != null) {
						   importElement.removeAttributeNode(schemaLocation);
						}
					 }
				  }
			   }
			}

			for (Node node : importElements) {
			   //   schemaDocument.getRootElement().removeChild(node);
			}

			Node importedNode = wsdl.getDocument().importNode(schemaDocument.getRootElement(), true);
			types.appendChild(importedNode);

		 } else {
			Element importElement = wsdl.addElement(types, "import", xsdNamespace, xsdPrefix);

			String schemaLocation = extractLastPath(baseAddress.toString()) + "/" + xsdPath + '/' + serviceSchema.getName();

			wsdl.addAttr(importElement, "namespace", serviceSchema.getNamespace());
			wsdl.addAttr(importElement, "schemaLocation", schemaLocation);
		 }

	  }

   }

   protected void defineMessages()
	   throws IOException {

	  Method[] methods = getMethods();


	  for (Method m : methods) {
		 Class<?> inputMessage = m.getParameterTypes()[0];

		 Element inputMessageElement = wsdl.addElement(wsdl.getRootElement(), "message", wsdlNamespace, wsdlPrefix);
		 wsdl.addAttr(inputMessageElement, "name", getMessageName(m) + "Input");
		 Element inputPart = wsdl.addElement(inputMessageElement, "part", wsdlNamespace, wsdlPrefix);
		 wsdl.addAttr(inputPart, "name", "body");
		 wsdl.addAttr(inputPart, "element", getClassNameWithPrefix(inputMessage));


		 Class<?> outputMessage = m.getReturnType();
		 if (!"void".equals(outputMessage.getName())) {
			Element outputMessageElement = wsdl.addElement(wsdl.getRootElement(), "message", wsdlNamespace, wsdlPrefix);
			wsdl.addAttr(outputMessageElement, "name", getMessageName(m) + "Output");
			Element outputMessagePartElement = wsdl.addElement(outputMessageElement, "part", wsdlNamespace, wsdlPrefix);
			wsdl.addAttr(outputMessagePartElement, "name", "body");
			wsdl.addAttr(outputMessagePartElement, "element", getClassNameWithPrefix(outputMessage));
		 }

	  }

   }


   protected void definePortType() {
	  Element portTypeElement = wsdl.addElement(wsdl.getRootElement(), "portType", wsdlNamespace, wsdlPrefix);
	  wsdl.addAttr(portTypeElement, "name", getPortTypeName());

	  Method[] methods = getMethods();

	  for (Method m : methods) {


		 Element operation = wsdl.addElement(portTypeElement, "operation", wsdlNamespace, wsdlPrefix);
		 wsdl.addAttr(operation, "name", getActionName(m));

		 Element inputMessageElement = wsdl.addElement(operation, "input", wsdlNamespace, wsdlPrefix);

		 Class<?> inputMessage = m.getParameterTypes()[0];
		 wsdl.addAttr(inputMessageElement, "message", tnsPrefix + ":" + getMessageName(m) + "Input");

		 Class<?> outputMessage = m.getReturnType();
		 if (!"void".equals(outputMessage.getName())) {
			Element outputMessageElement = wsdl.addElement(operation, "output", wsdlNamespace, wsdlPrefix);
			wsdl.addAttr(outputMessageElement, "message", tnsPrefix + ":" + getMessageName(m) + "Output");
		 }

	  }
   }


   protected void defineBinding() {
	  Element portTypeElement = wsdl.addElement(wsdl.getRootElement(), "binding", wsdlNamespace, wsdlPrefix);
	  wsdl.addAttr(portTypeElement, "name", getBindingName());
	  wsdl.addAttr(portTypeElement, "type", tnsPrefix + ":" + getPortTypeName());

	  Element wsdlSoapElement = wsdl.addElement(portTypeElement, "binding", wsdlSoapNamespace, wsdlSoapPrefix);
	  wsdl.addAttr(wsdlSoapElement, "style", "document");
	  wsdl.addAttr(wsdlSoapElement, "transport", soapHttpBindingNamespace);

	  Method[] methods = getMethods();

	  for (Method m : methods) {


		 ServiceAction serviceAction = m.getAnnotation(ServiceAction.class);

		 Element operation = wsdl.addElement(portTypeElement, "operation", wsdlNamespace, wsdlPrefix);
		 wsdl.addAttr(operation, "name", getActionName(m));


		 Element wsdlSoapOperationElement = wsdl.addElement(operation, "operation", wsdlSoapNamespace, wsdlSoapPrefix);
		 wsdl.addAttr(wsdlSoapOperationElement, "soapAction", serviceAction.value());


		 Element inputMessageElement = wsdl.addElement(operation, "input", wsdlNamespace, wsdlPrefix);
		 Element inputSoapMessageElement = wsdl.addElement(inputMessageElement, "body", wsdlSoapNamespace, wsdlSoapPrefix);
		 wsdl.addAttr(inputSoapMessageElement, "use", "literal");

		 Class<?> outputMessage = m.getReturnType();
		 if (!"void".equals(outputMessage.getName())) {
			Element outputMessageElement = wsdl.addElement(operation, "output", wsdlNamespace, wsdlPrefix);
			Element outputSoapMessageElement = wsdl.addElement(outputMessageElement, "body", wsdlSoapNamespace, wsdlSoapPrefix);
			wsdl.addAttr(outputSoapMessageElement, "use", "literal");
		 }
	  }
   }

   protected void defineService() {
	  Element serviceElement = wsdl.addElement(wsdl.getRootElement(), "service", wsdlNamespace, wsdlPrefix);
	  wsdl.addAttr(serviceElement, "name", getServiceName());

	  Element portElement = wsdl.addElement(serviceElement, "port", wsdlNamespace, wsdlPrefix);
	  wsdl.addAttr(portElement, "name", getPortName());
	  wsdl.addAttr(portElement, "binding", tnsPrefix + ":" + getBindingName());

	  Element address = wsdl.addElement(portElement, "address", wsdlSoapNamespace, wsdlSoapPrefix);

	  wsdl.addAttr(address, "location", baseAddress.toString());
   }
}
