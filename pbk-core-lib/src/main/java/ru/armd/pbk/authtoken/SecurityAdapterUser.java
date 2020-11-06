package ru.armd.pbk.authtoken;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.armd.pbk.domain.core.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Адаптер наших сущностей пользвателя к представлению spring security.
 */
public class SecurityAdapterUser
	implements UserDetails {

   private static final long serialVersionUID = -3888024115682171440L;

   private User user;

   private Set<String> userPermissions;

   private Set<String> roleNames;

   private Long departmentId;

   /**
	* Конструктор адаптера принимает пользователя и его пермишены.
	*
	* @param user            пользователь в терминах нашей системы.
	* @param roleNames       роли.
	* @param userPermissions пермишены.
	*/
   public SecurityAdapterUser(User user, Set<String> userPermissions, Set<String> roleNames, Long departmentId) {
	  this.user = user;
	  this.userPermissions = userPermissions;
	  this.roleNames = roleNames;
	  this.departmentId = departmentId;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
	  if (userPermissions == null) {
		 return Collections.emptyList();
	  }
	  Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	  for (String role : userPermissions) {
		 authorities.add(new SimpleGrantedAuthority(role));
	  }
	  return authorities;
   }

   public Set<String> getRoleNames() {
	  return roleNames;
   }

   public void setRoleNames(Set<String> roleNames) {
	  this.roleNames = roleNames;
   }

   public Long getUserId() {
	  return user.getId();
   }

   public Boolean isLdapUser() {
	  return user.getIsLdap() != null && user.getIsLdap();
   }

   @Override
   public String getPassword() {
	  return user.getPassword();
   }

   @Override
   public String getUsername() {
	  return user.getLogin();
   }

   public String getFullName() {
	  return user.getName();
   }

   @Override
   public boolean isAccountNonExpired() {
	  return user.getExpirationDate().after(new Date());
   }

   @Override
   public boolean isAccountNonLocked() {
	  return user.getIsActive();
   }

   @Override
   public boolean isCredentialsNonExpired() {
	  return true;
   }

   @Override
   public boolean isEnabled() {
	  return user.getIsDelete() == 0;// user.getExpirationDate().after(new
	  // Date());
   }

   public String getName() {
	  return user.getName();
   }

   public Long getDepartmentId() {
	  return departmentId;
   }

   public String getReportsAuth() {
	  return user.getReportsAuth();
   }
}
