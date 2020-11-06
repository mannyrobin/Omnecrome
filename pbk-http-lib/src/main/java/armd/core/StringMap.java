package armd.core;

import java.util.HashMap;

/**
 */
public class StringMap
	extends HashMap<String, String> {

   private static final long serialVersionUID = 0;

   @Override
   public String put(String key, String value) {
	  if (key == null) {
		 key = "";
	  }
	  return super.put(key.toLowerCase().trim(), value);
   }

   @Override
   public String get(Object key) {
	  String sKey = (key == null) ? "" : key.toString().toLowerCase().trim();
	  return super.get(sKey);
   }
}