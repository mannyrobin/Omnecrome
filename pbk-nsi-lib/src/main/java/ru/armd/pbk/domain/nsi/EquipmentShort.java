package ru.armd.pbk.domain.nsi;


/**
 * Домен бортового оборудования.
 */
public class EquipmentShort {

   private Long headId;
   private String asduEquipmentId;

   public Long getHeadId() {
	  return headId;
   }

   public void setHeadId(Long headId) {
	  this.headId = headId;
   }

   public String getAsduEquipmentId() {
	  return asduEquipmentId;
   }

   public void setAsduEquipmentId(String asduEquipmentId) {
	  this.asduEquipmentId = asduEquipmentId;
   }
}
