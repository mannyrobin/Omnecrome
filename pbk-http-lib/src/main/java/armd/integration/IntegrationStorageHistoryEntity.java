package armd.integration;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 */
public interface IntegrationStorageHistoryEntity {
   BigInteger getId();


   BigInteger getIntegrationRequestId();


   BigInteger getSoapClientLogId();


   Integer getIntegrationRequestHistoryStId();


   BigInteger getUserId();

   BigInteger getDepartmentId();


   Timestamp getActionDate();

}
