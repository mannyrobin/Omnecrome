package armd.integration;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 */
public interface IntegrationStorageEntity {

   BigInteger getId();

   Timestamp getCreateDate();

   BigInteger getCreateUserId();

   BigInteger getDepartmentId();

   Integer getIntegrationRequestTypeId();

   Integer getIntegrationRequestStatusId();

   String getAsyncKey();

   String getRequest();

   String getResponse();
}
