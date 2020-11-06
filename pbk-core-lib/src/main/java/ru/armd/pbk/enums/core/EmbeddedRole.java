package ru.armd.pbk.enums.core;

/**
 * Специальные роли системы.
 */
public enum EmbeddedRole {

   PBK_USERS_ROLE(0L),
   PBK_DEBUG(1L),
   PBK_ADMINS(2L),
   PBK_NSI_ADMINS(3L),
   PBK_SDIK_USERS(4L),
   PBK_TOSDIK_USERS(5L),
   PBK_MGT_USERS(6L);

   private final Long id;

   private EmbeddedRole(Long id) {
	  this.id = id;
   }

   public Long getId() {
	  return id;
   }

}
