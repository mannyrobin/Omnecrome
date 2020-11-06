package ru.armd.pbk.enums.nsi;

/**
 * Типы маршрутов.
 */
public enum VenueRouteType {

   TO_BASIC(1L, "К основным"),
   FROM_BASIC(2L, "От основных");

   private Long id;
   private String name;

   private VenueRouteType(Long id, String name) {
	  this.id = id;
	  this.name = name;
   }

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

}
