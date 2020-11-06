package armd.core;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 */
public final class ConverterEntity {


   public ConverterEntity(Class<?> cl, Marshaller marshaller, Unmarshaller unmarshaller) {
	  this.cl = cl;
	  this.marshaller = marshaller;
	  this.unmarshaller = unmarshaller;
   }

   private Class<?> cl;
   private Marshaller marshaller;
   private Unmarshaller unmarshaller;

   public Class<?> getCl() {
	  return cl;
   }

   public Marshaller getMarshaller() {
	  return marshaller;
   }

   public Unmarshaller getUnmarshaller() {
	  return unmarshaller;
   }
}
