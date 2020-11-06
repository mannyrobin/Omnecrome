package armd.core;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 */
public class Converter {

   private static final Object converterSync = new Object();
   protected static final String dateFormat = "yyyy-MM-dd";
   protected static final String russianDateFormat = "dd.MM.yyyy";
   private static final HashMap<Class<?>, ConverterEntity> converterEntityHashMap = new HashMap<Class<?>, ConverterEntity>();

   private static final GsonBuilder gsonBuilder;

   static {
	  JsonDeserializer<Date> deserDate = new JsonDeserializer<Date>() {
		 @Override
		 public Date deserialize(JsonElement json, Type typeOfT,
								 JsonDeserializationContext context)
			 throws JsonParseException {
			if (json == null) {
			   return null;
			}

			if (json.getAsString().isEmpty()) {
			   return null;
			} else if (json.getAsString().indexOf('-') != -1) {
			   return DateUtils.parseSqlDate(json.getAsString(), dateFormat);
			} else {
			   return DateUtils.parseSqlDate(json.getAsString(), russianDateFormat);
			}
		 }
	  };
	  JsonDeserializer<Integer> deserInteger = new JsonDeserializer<Integer>() {
		 @Override
		 public Integer deserialize(JsonElement json, Type typeOfT,
									JsonDeserializationContext context)
			 throws JsonParseException {
			if (json == null) {
			   return null;
			}

			if (json.getAsString().isEmpty()) {
			   return null;
			} else {
			   return Integer.parseInt(json.getAsString());
			}
		 }
	  };
	  JsonDeserializer<Long> deserLong = new JsonDeserializer<Long>() {
		 @Override
		 public Long deserialize(JsonElement json, Type typeOfT,
								 JsonDeserializationContext context)
			 throws JsonParseException {
			if (json == null) {
			   return null;
			}

			if (json.getAsString().isEmpty()) {
			   return null;
			} else {
			   return Long.parseLong(json.getAsString());
			}
		 }
	  };
	  gsonBuilder = new GsonBuilder();
	  gsonBuilder.registerTypeAdapter(Date.class, deserDate);
	  gsonBuilder.registerTypeAdapter(Integer.class, deserInteger);
	  gsonBuilder.registerTypeAdapter(Long.class, deserLong);
   }

   private final static ConverterEntity get(Class<?> cl)
	   throws Exception {

	  synchronized (converterSync) {
		 ConverterEntity converterEntity = converterEntityHashMap.get(cl);
		 if (converterEntity != null) {
			return converterEntity;
		 }
	  }

	  JAXBContext jaxbContext = JAXBContext.newInstance(cl);
	  Marshaller marshaller = jaxbContext.createMarshaller();
	  Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


	  ConverterEntity converterEntity = new ConverterEntity(cl, marshaller, unmarshaller);
	  synchronized (converterSync) {
		 converterEntityHashMap.put(cl, converterEntity);
	  }

	  return converterEntity;
   }

   private final static ConverterEntity getByPackage(Class<?> cl)
	   throws Exception {

	  synchronized (converterSync) {
		 ConverterEntity converterEntity = converterEntityHashMap.get(cl);
		 if (converterEntity != null) {
			return converterEntity;
		 }
	  }

	  JAXBContext jaxbContext = JAXBContext.newInstance(cl.getPackage().getName());
	  Marshaller marshaller = jaxbContext.createMarshaller();
	  Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


	  ConverterEntity converterEntity = new ConverterEntity(cl, marshaller, unmarshaller);
	  synchronized (converterSync) {
		 converterEntityHashMap.put(cl, converterEntity);
	  }

	  return converterEntity;
   }

   public final static <T> T toObject(String xml, Class<T> cl)
	   throws Exception {

	  StringReader reader = new StringReader(xml);
	  try {

		 @SuppressWarnings("unchecked")
		 T res = (T) get(cl).getUnmarshaller().unmarshal(reader);
		 return res;

	  } finally {
		 reader.close();
	  }
   }

   public final static <T> List<T> toObjects(List<String> xmls, Class<T> cl)
	   throws Exception {

	  ArrayList<T> res = new ArrayList<T>(xmls.size());

	  Unmarshaller unmarshaller = get(cl).getUnmarshaller();

	  for (String xml : xmls) {
		 StringReader reader = new StringReader(xml);
		 try {

			@SuppressWarnings("unchecked")
			T elem = (T) unmarshaller.unmarshal(reader);
			res.add(elem);

		 } finally {
			reader.close();
		 }
	  }

	  return res;
   }

   public final static String toXml(Object obj, Class<?> cl)
	   throws Exception {

	  StringWriter writer = new StringWriter();
	  try {

		 get(cl).getMarshaller().marshal(obj, writer);
		 return writer.toString();

	  } finally {
		 writer.close();
	  }
   }

   public final static String toXmlByPackage(Object obj, Class<?> cl)
	   throws Exception {

	  StringWriter writer = new StringWriter();
	  try {

		 getByPackage(cl).getMarshaller().marshal(obj, writer);
		 return writer.toString();

	  } finally {
		 writer.close();
	  }
   }

   public static Object parseString(String content, Class<?> cl, RefVal<Boolean> success) {
	  if (success == null) {
		 success = new RefVal<>();
	  }
	  success.set(false);
	  if (content == null) {
		 return null;
	  }
	  content = content.trim();
	  if (content.isEmpty()) {
		 return null;
	  }

	  if (String.class.isAssignableFrom(cl)) {
		 return content;
	  }

	  try {
		 if (Date.class.isAssignableFrom(cl)) {
			return DateUtils.parseSqlDate(content, "dd.MM.yyyy");
		 } else if (XMLGregorianCalendar.class.isAssignableFrom(cl)) {

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(DateUtils.parseSqlDate(content, "dd.MM.yyyy"));

			return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		 } else {
			if (cl.isEnum()) {

			   try {
				  Method method = cl.getMethod("valueOf", new Class<?>[] {Integer.class});
				  if (!Modifier.isStatic(method.getModifiers())) {
					 return null;
				  }
				  Object res = method.invoke(null, new Object[] {Integer.parseInt(content)});

				  success.set(true);

				  return res;

			   } catch (NoSuchMethodException ex) {

				  Object[] values = cl.getEnumConstants();
				  for (Object v : values) {
					 if (v.toString().toLowerCase().trim().equals(content.toLowerCase().trim())) {
						return v;
					 }
				  }
				  return null;
			   }

			} else {

			   Method method = cl.getMethod("valueOf", new Class<?>[] {String.class});
			   if (!Modifier.isStatic(method.getModifiers())) {
				  return null;
			   }

			   Object res = method.invoke(null, new Object[] {content});

			   success.set(true);

			   return res;
			}
		 }

	  } catch (Exception ex) {
		 return null;
	  }
   }

   public static final <T> T fromJson(String content, Class<T> cl) {
	  return gsonBuilder.create().fromJson(content, cl);
   }

   public static final String toJson(Object object) {
	  return gsonBuilder.create().toJson(object);
   }

   public static final String encodeStringUTF8(String s) {
	  StringBuilder sb = new StringBuilder();
	  for (int i = 0; i < s.length(); i++) {
		 char c = s.charAt(i);
		 sb.append(String.format("\\u%04x", (int) c));
	  }

	  return sb.toString();
   }
}
