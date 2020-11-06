package armd.core;


import java.lang.reflect.Array;
import java.util.List;

public class ArrayUtils {


   @SuppressWarnings("unchecked")
   public static <T> T[] toArray(Class<?> cl, List<T> list) {

	  T[] res = (T[]) Array.newInstance(cl, list.size());

	  for (int i = 0; i < list.size(); i++) {
		 res[i] = list.get(i);
	  }

	  return res;
   }
}
