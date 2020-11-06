package ru.armd.pbk.domain.core;

public class UserExtended
	extends User {
   private static final long serialVersionUID = -5806581831915932593L;

   String permission;
   String role;
   Long departmentId;

   public static long getSerialVersionUID() {
	  return serialVersionUID;
   }

   public String getPermission() {
	  return permission;
   }

   public void setPermission(String permission) {
	  this.permission = permission;
   }

   public String getRole() {
	  return role;
   }

   public void setRole(String role) {
	  this.role = role;
   }

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
   }
}
