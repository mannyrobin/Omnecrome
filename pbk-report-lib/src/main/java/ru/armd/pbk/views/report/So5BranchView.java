package ru.armd.pbk.views.report;

/**
 * View для филиала (для использования в отчёте "Посменная численность контролёров ГУП "Мосгортранс"
 * и среднее значение численности за период").
 */
public class So5BranchView
	extends SoView {

   private String name;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }
}
