package ru.armd.pbk.domain.viss;

import ru.armd.pbk.core.domain.BaseDictionaryDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен конфигурации обмена.
 */
public class VisExchangeConfig
	extends BaseDictionaryDomain {

   private Long exchangeOperationId;
   private Long exchangeObjectId;
   private Long transportTypeId;
   private Integer isActive;
   private String uri;
   private String login;
   private String password;
   private Integer reRequestDepth;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("exchangeOperationId: ").append(StringUtils.nvl(getExchangeOperationId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("exchangeObjectId: ").append(StringUtils.nvl(getExchangeObjectId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("transportTypeId: ").append(StringUtils.nvl(getTransportTypeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isActive: ").append(StringUtils.nvl(getIsActive(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("uri: ").append(StringUtils.nvl(getUri(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("login: ").append(StringUtils.nvl(getLogin(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("password: ").append(StringUtils.nvl(getPassword(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("reRequestDepth: ").append(StringUtils.nvl(getReRequestDepth(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Long getExchangeOperationId() {
	  return exchangeOperationId;
   }

   public void setExchangeOperationId(Long exchangeOperationId) {
	  this.exchangeOperationId = exchangeOperationId;
   }

   public Long getExchangeObjectId() {
	  return exchangeObjectId;
   }

   public void setExchangeObjectId(Long exchangeObjectId) {
	  this.exchangeObjectId = exchangeObjectId;
   }

   public Long getTransportTypeId() {
	  return transportTypeId;
   }

   public void setTransportTypeId(Long transportTypeId) {
	  this.transportTypeId = transportTypeId;
   }

   public boolean isActive() {
	  return isActive != null && isActive.intValue() == 1;
   }

   public Integer getIsActive() {
	  return isActive;
   }

   public void setIsActive(Integer isActive) {
	  this.isActive = isActive;
   }

   public String getUri() {
	  return uri;
   }

   public void setUri(String uri) {
	  this.uri = uri;
   }

   public String getLogin() {
	  return login;
   }

   public void setLogin(String login) {
	  this.login = login;
   }

   public String getPassword() {
	  return password;
   }

   public void setPassword(String password) {
	  this.password = password;
   }

    public Integer getReRequestDepth() {
        return reRequestDepth;
    }

    public void setReRequestDepth(Integer reRequestDepth) {
        this.reRequestDepth = reRequestDepth;
    }
}
