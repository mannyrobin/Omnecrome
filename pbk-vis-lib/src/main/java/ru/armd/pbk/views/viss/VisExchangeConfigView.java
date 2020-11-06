package ru.armd.pbk.views.viss;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View конфигурации взаимодействия.
 */
public class VisExchangeConfigView
	extends BaseGridView {

   private Long id;
   private boolean isDelete;
   private String code;
   private String name;
   private String description;
   private String exchangeOperation;
   private String exchangeObject;
   private String vis;
   private boolean isActive;
   private String transportType;
   private String uri;
   private String login;
   private String password;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }

   public String getCode() {
	  return code;
   }

   public void setCode(String code) {
	  this.code = code;
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

   public String getExchangeOperation() {
	  return exchangeOperation;
   }

   public void setExchangeOperation(String exchangeOperation) {
	  this.exchangeOperation = exchangeOperation;
   }

   public String getExchangeObject() {
	  return exchangeObject;
   }

   public void setExchangeObject(String exchangeObject) {
	  this.exchangeObject = exchangeObject;
   }

   public String getVis() {
	  return vis;
   }

   public void setVis(String vis) {
	  this.vis = vis;
   }

   public boolean isActive() {
	  return isActive;
   }

   public void setActive(boolean isActive) {
	  this.isActive = isActive;
   }

   public String getTransportType() {
	  return transportType;
   }

   public void setTransportType(String transportType) {
	  this.transportType = transportType;
   }

   public String getUri() {
	  return uri;
   }

   public void setUri(String uri) {
	  this.uri = uri;
   }

   public String getLogin() {
	  return login;
   }

   public void setLogin(String login) {
	  this.login = login;
   }

   public String getPassword() {
	  return password;
   }

   public void setPassword(String password) {
	  this.password = password;
   }
}
