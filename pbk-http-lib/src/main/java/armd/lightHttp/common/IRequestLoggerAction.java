package armd.lightHttp.common;

import armd.integration.IntegrationException;

public interface IRequestLoggerAction {

   void action(final IRequestListenerContext requestLogger)
	   throws IntegrationException;
}
