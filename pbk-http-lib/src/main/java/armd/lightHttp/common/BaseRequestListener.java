package armd.lightHttp.common;

public class BaseRequestListener
	implements IRequestListener {
   @Override
   public IRequestListenerContext createContext(Long clientId, IRequestLoggerAction afterRegisterRequest, IRequestLoggerAction afterRegisterResponse, IRequestLoggerAction afterRegisterBeginCall, IRequestLoggerAction afterRegisterEndCall) {
	  return new BaseRequestListenerContext(clientId, afterRegisterRequest, afterRegisterResponse, afterRegisterBeginCall, afterRegisterEndCall);
   }
}
