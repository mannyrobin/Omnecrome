package armd.integration;

import java.math.BigInteger;

public interface ICallContextInfo {

   public IntegratedSoapClient getCaller();

   public boolean isAsyncCall();

   public AsyncRequestStage getRequestStage();

   public void setRequestStage(AsyncRequestStage stage);

   public BigInteger getCallerDepartmentId();

   public int getCallerUserId();

   public int getRequestType();

   public void setRequestType(int type);

   public BigInteger getAppControlRegisterId();

   public void setAppControlRegisterId(BigInteger id);

   public BigInteger getIntegrationStorageEntityId();

   public void setIntegrationStorageEntityId(BigInteger id);
}
