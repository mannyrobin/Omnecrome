package ru.armd.pbk.enums.core;


public enum EmbeddedUser {

   SYSTEM("SYSTEM", 0L),
   ADMIN("ADMIN", 1L);

   private String login;
   private Long id;

   EmbeddedUser(String login, Long id) {
	  this.login = login;
	  this.id = id;
   }

   public String getLogin() {
	  return login;
   }

   public void setLogin(String login) {
	  this.login = login;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }
}
