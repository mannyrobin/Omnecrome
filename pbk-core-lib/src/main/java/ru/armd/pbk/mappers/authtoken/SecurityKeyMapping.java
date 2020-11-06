package ru.armd.pbk.mappers.authtoken;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.domain.authtoken.SecurityKey;

/**
 * Маппер для работы с ключами.
 */
@IsMapper
public interface SecurityKeyMapping {

   /**
	* Возвращает последний актуальный ключ.
	*
	* @return
	*/
   SecurityKey getRecentKey();

   /**
	* Записывает новый ключ.
	*
	* @param domain объект ключа.
	*/
   void setNewKey(SecurityKey domain);
}
