package ru.armd.pbk.domain.core;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 *
 */
public class RolePermission
	extends BaseDomain {
   private Long roleId;

   private Long permId;

   public Long getRoleId() {
	  return roleId;
   }

   public void setRoleId(Long roleId) {
	  this.roleId = roleId;
   }

   public Long getPermId() {
	  return permId;
   }

   public void setPermId(Long permId) {
	  this.permId = permId;
   }
}
