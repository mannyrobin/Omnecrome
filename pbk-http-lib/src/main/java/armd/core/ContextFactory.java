package armd.core;

/**
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Array;
import java.util.Map;

public class ContextFactory {

   private static final Object ContextFactoryLock = new Object();
   private static ApplicationContext current = null;


   private static ApplicationContext getCurrent() {
	  if (current == null) {
		 synchronized (ContextFactoryLock) {
			if (current == null) {
			   current = new ClassPathXmlApplicationContext("spring/Module.xml");
			}
		 }
	  }
	  return current;
   }


   public static <T> T get(Class<T> cl) {

	  Map<String, T> res = getCurrent().getBeansOfType(cl);
	  if (res.size() == 0) {
		 return null;
	  }

	  String[] keys = res.keySet().toArray(new String[] {});


	  if (res.size() == 1) {
		 for (String key : keys) {
			return res.get(key);
		 }
	  }

	  for (String key : keys) {
		 if (cl.getName().equals(key)) {
			return res.get(key);
		 }
	  }

	  for (String key : keys) {
		 return res.get(key);
	  }

	  return null;
   }


   public static <T> T get(Class<T> cl, String name) {

	  Object res = getCurrent().getBean(name);
	  if (!cl.isInstance(res)) {
		 return null;
	  }

	  return cl.cast(res);
   }

   public static <T> T[] getBeans(Class<T> cl) {
	  Map<String, T> res = (getCurrent().getBeansOfType(cl));

	  @SuppressWarnings("unchecked")
	  T[] resArray = (T[]) Array.newInstance(cl, 0);

	  return res.values().toArray(resArray);
   }
}
