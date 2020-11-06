package ru.armd.pbk.domain.core;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Класс для ролей системы.
 */
public class Role
	extends BaseDomain {

   private String name;
   private String description;
   private String ldapRole;

   public String getLdapRole() {
	  return ldapRole;
   }

   public void setLdapRole(String ldapRole) {
	  this.ldapRole = ldapRole;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }
}
