package armd.integration;

import armd.lightHttp.common.IRequestListenerContext;

import java.math.BigInteger;

/**
 * @author Alexey Kiselev (kiselev@relex.ru))
 */
public interface IntegratedSoapClient {
   public int asyncPoll(Integer integrationRequestTypeId, BigInteger integrationStorageEntityId, String asyncKey);

   public IntegrationStorageEntity getIntegrationStorageEntityAsyncInit(int requestType, IRequestListenerContext requestListenerContext);

   public IntegrationStorageEntity getIntegrationStorageEntityAsyncEnd(int requestType, BigInteger integrationStorageEntityId, IRequestListenerContext requestListenerContext);

   public AsyncRequestStage getStageByResponse(IRequestListenerContext requestListenerContext);
}
