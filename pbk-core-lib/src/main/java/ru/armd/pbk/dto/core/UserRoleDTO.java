package ru.armd.pbk.dto.core;

import ru.armd.pbk.core.dto.BaseDTO;

/**
 * Dto для роли пользователя.
 */
public class UserRoleDTO
	extends BaseDTO {
   private Long userId;

   private Long roleId;

   public Long getUserId() {
	  return userId;
   }

   public void setUserId(Long userId) {
	  this.userId = userId;
   }

   public Long getRoleId() {
	  return roleId;
   }

   public void setRoleId(Long roleId) {
	  this.roleId = roleId;
   }
}
