package ru.armd.pbk.gismgt.parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.OutputStream;

public class GisMgtJaxbUtils {

   protected static String PACKAGE = "ru.armd.pbk.gismgt.schema";

   public static Object getObject(InputStream is)
	   throws JAXBException {
	  try {
		 JAXBContext jc = JAXBContext.newInstance(PACKAGE);
		 Unmarshaller unmarshaller = jc.createUnmarshaller();
		 Object o = unmarshaller.unmarshal(is);
		 return o;
	  } catch (Exception e) {
		 System.out.print(e);
	  }
	  return null;
   }


   public static void getXml(Object o, OutputStream os)
	   throws JAXBException {
	  JAXBContext jc = JAXBContext.newInstance(PACKAGE);
	  Marshaller marshaller = jc.createMarshaller();
	  marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	  marshaller.marshal(o, os);
   }

   public static void main(String[] args)
	   throws Exception {
	  getXml(getObject(ClassLoader.class.getResourceAsStream("/xjc-gen/gismgt/test.xml")), System.out);
   }
}
