package ru.armd.pbk.components;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.armd.pbk.authtoken.AuthenticationManager;
import ru.armd.pbk.authtoken.SecurityAdapterUser;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.exceptions.PBKException;

import java.util.Map;

/**
 * Аспект для реализации частичного доступа к информации, относящейся к департаменту.
 * Подставяет в параметр запроса идентификатор департамента к которому относится залогиненный пользователь,
 * ну и учитывает привилегии.
 */
@Aspect
@Component
public class DepartmentAspect {

   @SuppressWarnings("unchecked")
   @Around(" @annotation(ru.armd.pbk.authtoken.DepartmentAuthorization)")
   public Object onSelectDomainList(ProceedingJoinPoint pjp)
	   throws Throwable {
	  try {
		 Object[] args = pjp.getArgs();
		 Map<String, Object> filters = null;
		 if (args[0] instanceof BaseGridViewParams) {
			filters = ((BaseGridViewParams) args[0]).getFilter();
		 } else if (args[0] instanceof BaseSelectListParams) {
			filters = ((BaseSelectListParams) args[0]).getFilter();
		 } else if (args[0] instanceof Map) {
			filters = (Map<String, Object>) args[0];
		 } else {
			throw new PBKException("Некорректный тип параметра в DepartmentAspect: " + args[0] == null ? "null" : args[0].getClass().toString());
		 }
		 //Если окажется, что этот пользователь админ, то параметр не нужно заполнять параметр deptId
		 //а если не админ, то пускай смотрит только свой департамент. И да у пользователя может быть не задан департамент,
		 // тогда лучше подсунуть ему идентификатор -1, а то он как админ всё увидит.
		 SecurityAdapterUser sau = AuthenticationManager.getUserInfo();
		 if (checkAllDepartmentPrivilege(sau)) {
			filters.put("deptAuthId", null);
		 } else {
			Long depId = sau.getDepartmentId() != null ? sau.getDepartmentId() : -1;
			filters.put("deptAuthId", depId);
		 }
		 return pjp.proceed();
	  } catch (Throwable e) {
		 throw e;
	  }
   }

   private boolean checkAllDepartmentPrivilege(SecurityAdapterUser sau) {
	  return sau.getAuthorities() != null && (
		  sau.getAuthorities().contains(new SimpleGrantedAuthority("DEBUG_TO_REPLACE"))
			  || sau.getAuthorities().contains(new SimpleGrantedAuthority("PBK_ALL_DEPARTMENTS")));
   }
}
