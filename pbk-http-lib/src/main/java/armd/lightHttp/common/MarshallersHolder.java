package armd.lightHttp.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.HashMap;


public class MarshallersHolder {
   /*
   private static MarshallersHolder _instance = new MarshallersHolder();

   public static MarshallersHolder getInstance() {
	   return _instance;
   }
   */
   private static final Object objSync = new Object();

   private static final HashMap<String, Unmarshaller> unmarshallersStorage = new HashMap<String, Unmarshaller>();
   private static final HashMap<String, Marshaller> marshallersStorage = new HashMap<String, Marshaller>();

   public static final Unmarshaller getUnmarshaller(String context)
	   throws JAXBException {
	  synchronized (objSync) {
		 if (unmarshallersStorage.containsKey(context)) {
			return unmarshallersStorage.get(context);
		 } else {
			JAXBContext jaxbContext = JAXBContext.newInstance(context);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			unmarshallersStorage.put(context, unmarshaller);
			return unmarshaller;
		 }
	  }
   }

   public static final Marshaller getMarshaller(String context)
	   throws JAXBException {
	  synchronized (objSync) {
		 if (marshallersStorage.containsKey(context)) {
			return marshallersStorage.get(context);
		 } else {
			JAXBContext jaxbContext = JAXBContext.newInstance(context);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshallersStorage.put(context, marshaller);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			return marshaller;
		 }
	  }
   }
}
