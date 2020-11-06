package ru.armd.pbk.views.core;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View объект для отображения прав пользователя.
 */
public class RolesPermissionEditView
	extends BaseGridView {

   private Long id;
   private String name;
   private Integer canRead;
   private Integer canUpdate;
   private Integer canDelete;
   private Integer canCreate;
   private Integer canReadChange;
   private Integer canUpdateChange;
   private Integer canDeleteChange;
   private Integer canCreateChange;
   private Long createPermId;
   private Long updatePermId;
   private Long readPermId;
   private Long deletePermId;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Integer getCanRead() {
	  return canRead;
   }

   public void setCanRead(Integer canRead) {
	  this.canRead = canRead;
   }

   public Integer getCanUpdate() {
	  return canUpdate;
   }

   public void setCanUpdate(Integer canUpdate) {
	  this.canUpdate = canUpdate;
   }

   public Integer getCanDelete() {
	  return canDelete;
   }

   public void setCanDelete(Integer canDelete) {
	  this.canDelete = canDelete;
   }

   public Integer getCanCreate() {
	  return canCreate;
   }

   public void setCanCreate(Integer canCreate) {
	  this.canCreate = canCreate;
   }

   public Integer getCanReadChange() {
	  return canReadChange;
   }

   public void setCanReadChange(Integer canReadChange) {
	  this.canReadChange = canReadChange;
   }

   public Integer getCanUpdateChange() {
	  return canUpdateChange;
   }

   public void setCanUpdateChange(Integer canUpdateChange) {
	  this.canUpdateChange = canUpdateChange;
   }

   public Integer getCanDeleteChange() {
	  return canDeleteChange;
   }

   public void setCanDeleteChange(Integer canDeleteChange) {
	  this.canDeleteChange = canDeleteChange;
   }

   public Integer getCanCreateChange() {
	  return canCreateChange;
   }

   public void setCanCreateChange(Integer canCreateChange) {
	  this.canCreateChange = canCreateChange;
   }

   public Long getCreatePermId() {
	  return createPermId;
   }

   public void setCreatePermId(Long createPermId) {
	  this.createPermId = createPermId;
   }

   public Long getUpdatePermId() {
	  return updatePermId;
   }

   public void setUpdatePermId(Long updatePermId) {
	  this.updatePermId = updatePermId;
   }

   public Long getReadPermId() {
	  return readPermId;
   }

   public void setReadPermId(Long readPermId) {
	  this.readPermId = readPermId;
   }

   public Long getDeletePermId() {
	  return deletePermId;
   }

   public void setDeletePermId(Long deletePermId) {
	  this.deletePermId = deletePermId;
   }

}
