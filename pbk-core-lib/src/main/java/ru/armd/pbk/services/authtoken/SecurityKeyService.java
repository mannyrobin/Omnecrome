package ru.armd.pbk.services.authtoken;

import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.armd.pbk.repositories.authtoken.SecurityKeyRepository;

import java.security.Key;

/**
 * Сервис для получения ключа.
 */
@Service
@Scope("singleton")
public class SecurityKeyService {

   Key key;

   @Autowired
   public SecurityKeyService(SecurityKeyRepository keyRepo) {
	  key = keyRepo.getRecentKey();
	  if (key == null) {
		 key = MacProvider.generateKey();
		 keyRepo.setNewKey(key);
	  }
   }

   /**
	* Возвращает ключ новый или из хранилища.
	*
	* @return ключ.
	*/
   public Key retriveKey() {
	  return key;
   }
}
