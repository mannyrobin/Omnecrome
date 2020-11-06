package armd.lightSoap.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class RequestContext
	implements Map<String, Object> {

   private HashMap<String, Object> innerMap = new HashMap<String, Object>();

   @Override
   public int size() {
	  return innerMap.size();
   }

   @Override
   public boolean isEmpty() {
	  return innerMap.isEmpty();
   }

   @Override
   public boolean containsKey(Object key) {
	  if (String.class.isInstance(key)) {
		 return innerMap.containsKey(key.toString().toLowerCase().trim());
	  } else {
		 return innerMap.containsKey(key);
	  }
   }

   @Override
   public boolean containsValue(Object value) {
	  return innerMap.containsValue(value);
   }

   @Override
   public Object get(Object key) {
	  if (String.class.isInstance(key)) {
		 return innerMap.get(key.toString().toLowerCase().trim());
	  } else {
		 return innerMap.get(key);
	  }
   }

   @Override
   public Object put(String key, Object value) {
	  return innerMap.put(key.toLowerCase().trim(), value);
   }

   @Override
   public Object remove(Object key) {
	  if (String.class.isInstance(key)) {
		 return innerMap.remove(key.toString().toLowerCase().trim());
	  } else {
		 return innerMap.remove(key);
	  }
   }

   @Override
   public void putAll(Map<? extends String, ?> m) {

	  for (String key : m.keySet()) {
		 innerMap.put(key.toLowerCase().trim(), m.get(key));
	  }

   }

   @Override
   public void clear() {
	  innerMap.clear();
   }

   @Override
   public Set<String> keySet() {
	  return innerMap.keySet();
   }

   @Override
   public Collection<Object> values() {
	  return innerMap.values();
   }

   @Override
   public Set<Entry<String, Object>> entrySet() {
	  return innerMap.entrySet();
   }
}
