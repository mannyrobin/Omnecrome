package armd.core;

/**
 */
public class RefVal<T> {
   private T target;

   public void set(T v) {
	  target = v;
   }

   public T get() {
	  return target;
   }
}
