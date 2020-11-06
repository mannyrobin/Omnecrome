package armd.integration;


import java.math.BigInteger;

public interface IntegrationStorage {

   Long saveSyncRequest(Integer userId, IntegrationStorageEntity r, IntegrationStorageHistoryEntity h)
	   throws IntegrationStorageException;

   public Long asyncRequestInit(Integer userId, BigInteger appControlRegisterId, IntegrationStorageEntity r, IntegrationStorageHistoryEntity h)
	   throws IntegrationStorageException;

   public Long asyncRequestPoll(Integer userId, IntegrationStorageEntity r, IntegrationStorageHistoryEntity h)
	   throws IntegrationStorageException;

   public Long asyncRequestEnd(Integer userId, IntegrationStorageEntity r, IntegrationStorageHistoryEntity h)
	   throws IntegrationStorageException;

}
