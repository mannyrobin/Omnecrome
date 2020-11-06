package ru.armd.pbk.repositories.tasks;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.tasks.Task;
import ru.armd.pbk.domain.tasks.TaskRoute;
import ru.armd.pbk.domain.tasks.Tasks;
import ru.armd.pbk.dto.tasks.TaskDTO;
import ru.armd.pbk.views.nsi.route.RouteView;
import ru.armd.pbk.views.tasks.TaskView;
import ru.armd.pbk.views.tasks.TaskViewDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Маппер для сопоставления различных типов сущности "задание".
 */
@Mapper
public abstract class TaskMatcher
	implements IDomainMatcher<Task, TaskDTO> {

   public static final TaskMatcher INSTANCE = Mappers.getMapper(TaskMatcher.class);

   /**
	* Базовый ко view.
	*
	* @param task задание.
	* @return
	*/
   public abstract TaskView baseToView(TaskViewDomain task);

   /**
	* Получает из домена маршрута view.
	*
	* @param route домен маршрута
	* @return view view маршрута
	*/
   abstract RouteView getRouteView(TaskRoute route);

   /**
	* Домен ко вью.
	*
	* @param task задание.
	* @return
	*/
   public TaskView toView(TaskViewDomain task) {
	  TaskView viewTask = baseToView(task);
	  if (task.getChangeEmployeeName() != null) {
		 viewTask.setEmployeeName(task.getChangeEmployeeName());
	  }
	  if (task.getChangeShiftName() != null) {
		 viewTask.setShiftName(task.getChangeShiftName());
	  }
	  return viewTask;
   }

   /**
	* Домены ко вью.
	*
	* @param tasks задания.
	* @return
	*/
   public List<TaskView> toViewList(List<TaskViewDomain> tasks) {
	  List<TaskView> result = new ArrayList<>();
	  if (tasks == null || tasks.isEmpty()) {
		 return result;
	  }
	  for (TaskViewDomain task : tasks) {
		 result.add(toView(task));
	  }
	  return result;
   }

   /**
	* Получает из домена заданий view для отображения в таблице.
	*
	* @param tasks домен заданий
	* @return view список view заданий
	*/
   public List<TaskView> getPlanTasksViewForTable(Tasks tasks) {
	  if (tasks != null) {
		 List<TaskView> taskViewList = initTaskViewList(tasks.getTasks());
		 if (tasks.getRoutes() != null && tasks.getRoutes().size() > 0) {
			for (TaskRoute route : tasks.getRoutes()) {
			   for (TaskView view : taskViewList) {
				  if (view.getId().equals(route.getTaskId())) {
					 view.getRoutes().add(getRouteView(route));
					 continue;
				  }
			   }
			}
		 }
		 return taskViewList;
	  }
	  return null;
   }

   /**
	* Начальная инициализация view заданий.
	*
	* @param taskViews список views
	* @return список проинициализированных views
	*/
   private List<TaskView> initTaskViewList(List<TaskView> taskViews) {
	  if (taskViews == null) {
		 return null;
	  }
	  for (TaskView planTaskView : taskViews) {
		 planTaskView.setRoutes(new ArrayList<RouteView>());
	  }
	  return taskViews;
   }
}
