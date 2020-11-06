package ru.armd.pbk.core.domain;

/**
 * Базовый домен для истории устройств.
 */
public class BaseDeviceOwnerHistoryDomain
	extends BaseVersionDomain {

   public Long employeeId;
   public Long deviceId;

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }

   public Long getDeviceId() {
	  return deviceId;
   }

   public void setDeviceId(Long deviceId) {
	  this.deviceId = deviceId;
   }

}
