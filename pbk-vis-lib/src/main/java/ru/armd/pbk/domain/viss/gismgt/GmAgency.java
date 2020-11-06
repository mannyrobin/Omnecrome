package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.utils.StringUtils;

public class GmAgency
	extends GmBaseDomain
	implements IHasName {

   private String name;
   private Long organizationFormMuid;
   private String legalAddress;
   private String actualAddress;
   private String contactInformation;

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public void setName(String name) {
	  this.name = name;
   }

   public Long getOrganizationFormMuid() {
	  return organizationFormMuid;
   }

   public void setOrganizationFormId(Long organizationFormMuid) {
	  this.organizationFormMuid = organizationFormMuid;
   }

   public String getLegalAddress() {
	  return legalAddress;
   }

   public void setLegalAddress(String legalAddress) {
	  this.legalAddress = legalAddress;
   }

   public String getActualAddress() {
	  return actualAddress;
   }

   public void setActualAddress(String actualAddress) {
	  this.actualAddress = actualAddress;
   }

   public String getContactInformation() {
	  return contactInformation;
   }

   public void setContactInformation(String contactInformation) {
	  this.contactInformation = contactInformation;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("organizationFormMuid: ").append(StringUtils.nvl(getOrganizationFormMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("legalAddress: ").append(StringUtils.nvl(getLegalAddress(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("actualAddress: ").append(StringUtils.nvl(getActualAddress(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("contactInformation: ").append(StringUtils.nvl(getContactInformation(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
