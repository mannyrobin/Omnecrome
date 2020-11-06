package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов подразделений.
 */
public class DepartmentReportView
	implements IReportView {

   private Long id;
   private String easuFhdId;
   private String name;
   private String parentDeptId;
   private String fullName;

   public String getEasuFhdId() {
	  return easuFhdId;
   }

   public void setEasuFhdId(String easuFhdId) {
	  this.easuFhdId = easuFhdId;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getParentDeptId() {
	  return parentDeptId;
   }

   public void setParentDeptId(String parentDeptId) {
	  this.parentDeptId = parentDeptId;
   }

   public String getFullName() {
	  return fullName;
   }

   public void setFullName(String fullName) {
	  this.fullName = fullName;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Long getId() {
	  return id;
   }

   @Override
   public List<Object> getReportList() {
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(easuFhdId);
	  result.add(name);
	  result.add(fullName);
	  result.add(parentDeptId);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "ID в ЕАСУ ФХД ", "Название", "Полное название", "Родительское подразделение");
   }

}
