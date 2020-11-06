package ru.armd.pbk.dto.core;

import ru.armd.pbk.core.dto.BaseDTO;

/**
 *
 */
public class RolePermissionDTO
	extends BaseDTO {

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
