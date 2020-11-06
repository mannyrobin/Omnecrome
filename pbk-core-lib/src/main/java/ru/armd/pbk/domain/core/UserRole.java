package ru.armd.pbk.domain.core;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Роль пользователя.
 */
public class UserRole
	extends BaseDomain {

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
