package armd.lightHttp.common;

/**
 */
public interface IRequestListener {

   IRequestListenerContext createContext(Long clientId,
										 IRequestLoggerAction afterRegisterRequest,
										 IRequestLoggerAction afterRegisterResponse,
										 IRequestLoggerAction afterRegisterBeginCall,
										 IRequestLoggerAction afterRegisterEndCall);
}
