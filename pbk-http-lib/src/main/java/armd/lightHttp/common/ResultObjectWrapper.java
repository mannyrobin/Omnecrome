package armd.lightHttp.common;


public class ResultObjectWrapper {

   protected Object obj;
   protected String MessageId;

   public ResultObjectWrapper(Object obj, String messageId) {
	  setObj(obj);
	  setMessageId(messageId);
   }

   public Object getObj() {
	  return obj;
   }

   public void setObj(Object obj) {
	  this.obj = obj;
   }

   public String getMessageId() {
	  return MessageId;
   }

   public void setMessageId(String messageId) {
	  MessageId = messageId;
   }
}
