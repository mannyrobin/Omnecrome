package ru.armd.pbk.views.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

/**
 * Класс юзера.
 */
public class UserView
	extends BaseGridView {

   private Long id;
   private String login;
   private String name;
   private boolean isDelete;
   private Boolean isActive;
   private Boolean isLdap;
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date expirationDate;
   private String roles;

   /**
	* Конструктор по умолчанию.
	*/
   public UserView() {
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getLogin() {
	  return login;
   }

   public void setLogin(String login) {
	  this.login = login;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Date getExpirationDate() {
	  return expirationDate;
   }

   public void setExpirationDate(Date expirationDate) {
	  this.expirationDate = expirationDate;
   }

   public String getRoles() {
	  return roles;
   }

   public void setRoles(String roles) {
	  this.roles = roles;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean delete) {
	  isDelete = delete;
   }

   public Boolean isLdap() {
	  return isLdap;
   }

   public void setLdap(Boolean ldap) {
	  isLdap = ldap;
   }

   public Boolean getActive() {
	  return isActive;
   }

   public void setActive(Boolean active) {
	  isActive = active;
   }
}
