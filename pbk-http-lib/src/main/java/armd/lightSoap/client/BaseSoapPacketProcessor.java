package armd.lightSoap.client;

import armd.lightHttp.client.IHttpPacketProcessor;
import armd.lightHttp.client.IPostprocessor;
import armd.lightHttp.common.MarshallersHolder;
import armd.lightHttp.common.XmlDocument;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;

public abstract class BaseSoapPacketProcessor
	implements IHttpPacketProcessor {

   private static final Logger LOGGER = Logger.getLogger(BaseSoapPacketProcessor.class);

   protected static final String soapEnvNamespace = "http://schemas.xmlsoap.org/soap/envelope/";
   protected static final String soapEnvPrefix = "soap";
   protected String defaultEncoding = "UTF-8";

   protected IPostprocessor postprocessor = null;

   public void setPostprocessor(IPostprocessor postprocessor) {
	  this.postprocessor = postprocessor;
   }

   protected String contextPathRequest;
   protected String contextPathResponse;

   public BaseSoapPacketProcessor(String contextPathRequest, String contextPathResponse) {
	  this.contextPathRequest = contextPathRequest;
	  this.contextPathResponse = contextPathResponse;
   }

   protected Marshaller getMarshaller()
	   throws JAXBException {
	  return MarshallersHolder.getMarshaller(contextPathRequest);
   }

   protected Unmarshaller getUnmarshaller()
	   throws JAXBException {
	  return MarshallersHolder.getUnmarshaller(contextPathResponse);
   }

   @Override
   public String prepareRequestPacket(Object in)
	   throws Exception {
	  XmlDocument document = new XmlDocument("Envelope", soapEnvNamespace, soapEnvPrefix);
	  document.addElement(document.getRootElement(), "Header", soapEnvNamespace, soapEnvPrefix);
	  org.w3c.dom.Element responseBody = document.addElement(document.getRootElement(), "Body", soapEnvNamespace, soapEnvPrefix);
	  if (in != null) {
		 Marshaller marshaller = getMarshaller();
		 synchronized (marshaller) {
			marshaller.marshal(in, responseBody);
		 }
	  }
	  StringWriter w = new StringWriter();
	  document.write(w);
	  return w.toString();
   }


   protected void beforeResponsePacketLoad(String in)
	   throws SoapClientException {
   }

   protected void responsePacketLoaded(XmlDocument requestXml)
	   throws SoapClientException {
   }

   protected Object resultObject(Object res)
	   throws SoapClientException {
	  Object rp;
	  if (res != null && res instanceof JAXBElement) {
		 rp = ((JAXBElement) res).getValue();
	  } else {
		 rp = res;
	  }
	  return rp;
   }

   @Override
   public Object parseResponsePacket(String in)
	   throws SoapClientException {
	  Object rp;
	  try {
		 Unmarshaller um = getUnmarshaller();
		 Object res = null;
		 XmlDocument requestXml;
		 try {
			beforeResponsePacketLoad(in);
			requestXml = XmlDocument.load(new ByteArrayInputStream(in.getBytes(defaultEncoding)));
			responsePacketLoaded(requestXml);
			org.w3c.dom.Element body = requestXml
				.getElement("Envelope", soapEnvNamespace)
				.getElement("Body", soapEnvNamespace)
				.getFirstElement()
				.getElement();
			if (body == null) {
			   throw new SoapClientException("No SOAP Body in packet", SoapClientException.SOAP_FAULT);
			}

			if (body.getLocalName().equalsIgnoreCase("Fault")) {
			   tryParseFault(in);
			}
			synchronized (um) {
			   res = um.unmarshal(body);
			}
		 } catch (SoapClientException e) {
			//beforeResponsePacketLoad verification fails
			//try to find Fault node
			tryParseFault(in);
		 } catch (IOException | SAXException | ParserConfigurationException | URISyntaxException e) {
			//unusual problems
			LOGGER.error(e);
			tryParseFault(in);
		 }
		 rp = resultObject(res);
	  } catch (JAXBException e) {
		 LOGGER.error(e);
		 throw new SoapClientException(e, SoapClientException.PROCESSING_EXCEPTION);
	  }
	  return rp;
   }

   protected void tryParseFault(String in)
	   throws SoapClientException {
	  try {
		 XmlDocument requestXml = XmlDocument.load(new ByteArrayInputStream(in.getBytes(defaultEncoding)));
		 if (requestXml != null) {
			org.w3c.dom.Element fault = requestXml
				.getElement("Envelope", soapEnvNamespace)
				.getElement("Body", soapEnvNamespace)
				.getElement("Fault", soapEnvNamespace).getElement();
			String faultDesc = "SOAP ERROR";
			if (fault != null) {
			   faultDesc = fault.getTextContent();
			}
			throw new SoapClientException(faultDesc, SoapClientException.SOAP_FAULT);
		 }
	  } catch (ParserConfigurationException | SAXException | IOException | URISyntaxException e) {
		 LOGGER.error(e);
		 throw new SoapClientException(e, SoapClientException.PROCESSING_EXCEPTION);
	  }
   }
}
