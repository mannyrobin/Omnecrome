package ru.armd.pbk.domain.plans.brigades;

/**
 * Домен для сопоставления бригады с кол-вом заданий, назначенных на нее.
 */
public class BrigadeTaskCount {

   private Long id;
   private int count;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public int getCount() {
	  return count;
   }

   public void setCount(int count) {
	  this.count = count;
   }
}
