package ru.armd.pbk.views.nsi.department;

import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.views.BaseVersionView;

/**
 * Представление для работы с парками депортаментов.
 */
public class DepartmentParkView
	extends BaseVersionView
	implements IHasName {

   private String name;

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public void setName(String name) {
	  this.name = name;
   }
}
