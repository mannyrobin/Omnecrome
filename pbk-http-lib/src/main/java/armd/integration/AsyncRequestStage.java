package armd.integration;

/**
 *
 */
public enum AsyncRequestStage {
   INIT(0),
   POLL(1),
   END(2),
   POLL_OR_END(3);

   private final int value;

   private AsyncRequestStage(int value) {
	  this.value = value;
   }

   public int getValue() {
	  return value;
   }
}
