package ru.armd.pbk.views.report;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * Общие данные для заполнения полей печатной формы стандартного отчёта.
 */
public abstract class SoView
	extends BaseGridView {

   private Long id;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }
}
