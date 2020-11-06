package ru.armd.pbk.views.nsi.district;

import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.views.BaseVersionView;

/**
 * Представление мест встреч района.
 */
public class DistrictVenueView
	extends BaseVersionView
	implements IHasCod, IHasName {

   private String name;
   private String cod;

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public void setName(String name) {
	  this.name = name;
   }

   @Override
   public String getCod() {
	  return cod;
   }

   @Override
   public void setCod(String cod) {
	  this.cod = cod;
   }

}
