package ru.armd.pbk.core.domain;

import java.util.Date;


/**
 * Домен файла.
 */
public class FileStream {

   private String id;
   private byte[] stream;
   private String name;
   private String type;
   private Long size;
   private Date creationTime;
   private Date lastWriteTime;
   private Date lastAccessTime;
   private boolean isDirectory;
   private boolean isOffline;
   private boolean isHidden;
   private boolean isReadonly;
   private boolean isArchive;
   private boolean isSystem;
   private boolean isTemporary;

   public String getId() {
	  return id;
   }

   public void setId(String id) {
	  this.id = id;
   }

   public byte[] getStream() {
	  return stream;
   }

   public void setStream(byte[] stream) {
	  this.stream = stream;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getType() {
	  return type;
   }

   public void setType(String type) {
	  this.type = type;
   }

   public Long getSize() {
	  return size;
   }

   public void setSize(Long size) {
	  this.size = size;
   }

   public Date getCreationTime() {
	  return creationTime;
   }

   public void setCreationTime(Date creationTime) {
	  this.creationTime = creationTime;
   }

   public Date getLastWriteTime() {
	  return lastWriteTime;
   }

   public void setLastWriteTime(Date lastWriteTime) {
	  this.lastWriteTime = lastWriteTime;
   }

   public Date getLastAccessTime() {
	  return lastAccessTime;
   }

   public void setLastAccessTime(Date lastAccessTime) {
	  this.lastAccessTime = lastAccessTime;
   }

   public boolean isDirectory() {
	  return isDirectory;
   }

   public void setDirectory(boolean isDirectory) {
	  this.isDirectory = isDirectory;
   }

   public boolean isOffline() {
	  return isOffline;
   }

   public void setOffline(boolean isOffline) {
	  this.isOffline = isOffline;
   }

   public boolean isHidden() {
	  return isHidden;
   }

   public void setHidden(boolean isHidden) {
	  this.isHidden = isHidden;
   }

   public boolean isReadonly() {
	  return isReadonly;
   }

   public void setReadonly(boolean isReadonly) {
	  this.isReadonly = isReadonly;
   }

   public boolean isArchive() {
	  return isArchive;
   }

   public void setArchive(boolean isArchive) {
	  this.isArchive = isArchive;
   }

   public boolean isSystem() {
	  return isSystem;
   }

   public void setSystem(boolean isSystem) {
	  this.isSystem = isSystem;
   }

   public boolean isTemporary() {
	  return isTemporary;
   }

   public void setTemporary(boolean isTemporary) {
	  this.isTemporary = isTemporary;
   }
}
