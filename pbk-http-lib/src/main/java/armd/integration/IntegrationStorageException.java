package armd.integration;

/**
 */
public class IntegrationStorageException
	extends Exception {

   private static final long serialVersionUID = 0L;

   public IntegrationStorageException(Throwable ex) {
	  super("IntegrationStorageException was thrown", ex);
   }
}
