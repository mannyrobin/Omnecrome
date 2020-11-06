package ru.armd.pbk.domain.tasks;

import ru.armd.pbk.views.tasks.TaskView;

import java.util.List;

/**
 * Домен заданий для отображения в таблице.
 */
public class Tasks {

   private List<TaskView> tasks;
   private List<TaskRoute> routes;

   public List<TaskView> getTasks() {
	  return tasks;
   }

   public void setTasks(List<TaskView> tasks) {
	  this.tasks = tasks;
   }

   public List<TaskRoute> getRoutes() {
	  return routes;
   }

   public void setRoutes(List<TaskRoute> routes) {
	  this.routes = routes;
   }
}
