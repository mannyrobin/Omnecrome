package ru.armd.pbk.services.authtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.armd.pbk.authtoken.SecurityAdapterUser;
import ru.armd.pbk.domain.core.User;
import ru.armd.pbk.domain.core.UserExtended;
import ru.armd.pbk.repositories.core.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Наша реализация сервиса UserDetailsService для spring-security.
 */
@Service("userDetailsService")
public class UserDetailsPbkService
	implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;

   /**
	* Загружает информацию о пользователе по логину.
	*
	* @param s логин.
	* @return
	* @throws UsernameNotFoundException
	*/
	/*
	Метод пепреписан для большей производительности. Оригинальный метод:
	@Override
	@Transactional 
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		User user = userRepository.getByLogin(s);
		if (user == null || user.getIsDelete() == 1) {
			throw new UsernameNotFoundException("Не зарегистрирован пользователь '" + s + "'");
		}
		
		Set<String> permissions = rolePermissionMapper.getPermissionsByUserLogin(s);
		Set<String> roles = roleMapper.getRoleNamesByUserLogin(s);
		Long departmentId = userRepository.getDepartmentId(s);
		SecurityAdapterUser secUser = new SecurityAdapterUser(user, permissions, roles, departmentId);
		return secUser;
	}*/
   @Override
   public UserDetails loadUserByUsername(String s)
	   throws UsernameNotFoundException {

	  List<UserExtended> userex = userRepository.getByLoginExtended(s);
	  if (userex == null || userex.size() == 0 || userex.get(0).getIsDelete() == 1) {
		 throw new UsernameNotFoundException("Не зарегистрирован пользователь '" + s + "'");
	  }

	  User user = userex.get(0);

	  Set<String> permissions = new HashSet<String>();
	  Set<String> roles = new HashSet<String>();
	  for (UserExtended item : userex) {
		 if (item.getPermission() != null) {
			permissions.add(item.getPermission());
		 }
		 if (item.getRole() != null) {
			roles.add(item.getRole());
		 }
	  }

	  userex.get(0).setRole(null);
	  userex.get(0).setPermission(null);

	  Long departmentId = userex.get(0).getDepartmentId();
	  SecurityAdapterUser secUser = new SecurityAdapterUser(user, permissions, roles, departmentId);
	  return secUser;
   }

}
