package ru.armd.pbk.views.report;

import ru.armd.pbk.core.views.SelectItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * View для связки контролёра и его расписания (для использования в отчёте "График работы контролеров
 * по территориальному подразделению").
 */
public class So1EmployeeScheduleView
	extends SoView {

   private So1EmployeeView employee;
   private List<So1ShiftView> schedule;
   private ShiftModes shiftMode;
   private Date dateStart;
   private Date dateEnd;

   /**
	* Конструктор по контролёру и его расписанию + даты начала и конца расписания.
	*
	* @param employee  контролёр
	* @param schedule  расписание
	* @param dateStart дата начала расписания
	* @param dateEnd   дата конца расписания
	*/
   public So1EmployeeScheduleView(So1EmployeeView employee, List<So1ShiftView> schedule, Date dateStart, Date dateEnd) {
	  this.setCnt(employee.getCnt());
	  this.setId(employee.getId());
	  this.employee = employee;
	  this.schedule = schedule;
	  this.dateStart = dateStart;
	  this.dateEnd = dateEnd;
   }

   public So1EmployeeView getEmployee() {
	  return employee;
   }

   public void setEmployee(So1EmployeeView employee) {
	  this.employee = employee;
   }

   public List<So1ShiftView> getSchedule() {
	  return schedule;
   }

   public void setSchedule(List<So1ShiftView> schedule) {
	  this.schedule = schedule;
   }

   public Date getDateStart() {
	  return dateStart;
   }

   public void setDateStart(Date dateStart) {
	  this.dateStart = dateStart;
   }

   public Date getDateEnd() {
	  return dateEnd;
   }

   public void setDateEnd(Date dateEnd) {
	  this.dateEnd = dateEnd;
   }

   public ShiftModes getShiftMode() {
	  return shiftMode;
   }

   public void setShiftMode(ShiftModes shiftMode) {
	  this.shiftMode = shiftMode;
   }

   /**
	* Смены.
	*/
   public static enum ShiftModes {

	  H_2_2(0L, "ДНВВ"),
	  H_5_6(1L, "Пятница и суббота"),
	  H_6_7(2L, "Суббота и воскресенье"),
	  H_7_1(3L, "Воскресенье и понедельник");

	  private Long id;
	  private String name;

	  private ShiftModes(Long id, String name) {
		 this.setId(id);
		 this.setName(name);
	  }

	  public Long getId() {
		 return id;
	  }

	  private void setId(Long id) {
		 this.id = id;
	  }

	  public String getName() {
		 return name;
	  }

	  private void setName(String name) {
		 this.name = name;
	  }

	  /**
	   * Получение элемента по id.
	   *
	   * @param id id.
	   * @return
	   */
	  public static ShiftModes getEnumById(Long id) {
		 for (ShiftModes value : values()) {
			if (value.getId().equals(id)) {
			   return value;
			}
		 }
		 return null;
	  }

	  /**
	   * Получает список элементов для отображения в выпадающем списке.
	   *
	   * @return список элементов
	   */
	  public static List<SelectItem> getSelectList() {
		 List<SelectItem> result = new ArrayList<>();
		 for (ShiftModes value : values()) {
			result.add(new SelectItem(value.getId(), value.getName()));
		 }
		 return result;
	  }

	  /**
	   * Получает список элементов для отображения в выпадающем списке дневных смен (первая или вторая).
	   *
	   * @return список элементов
	   */
	  public static List<SelectItem> getDaySelectList() {
		 List<SelectItem> result = new ArrayList<>();
		 for (ShiftModes value : values()) {
			if (!value.getId().equals(0L)) {
			   result.add(new SelectItem(value.getId(), value.getName()));
			}
		 }
		 return result;
	  }
   }

}
