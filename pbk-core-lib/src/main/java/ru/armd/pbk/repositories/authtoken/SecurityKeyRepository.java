package ru.armd.pbk.repositories.authtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.domain.authtoken.SecurityKey;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.authtoken.SecurityKeyMapping;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.util.Date;

/**
 * Репозиторий для ключей алгоритма шифрования токенов.
 */
@Repository
public class SecurityKeyRepository {

   @Autowired
   private SecurityKeyMapping keyMapping;

   /**
	* Возвращает актуальный ключ.
	*
	* @return
	*/
   public Key getRecentKey() {
	  try {
		 SecurityKey keyDomain = keyMapping.getRecentKey();
		 return keyDomain != null ? deserializeKey(keyDomain) : null;
	  } catch (PBKException e) {
		 throw e;
	  } catch (Exception e) {
		 throw new PBKException("Не удалось прочитать ключ из БД. Ошибка: " + e.getMessage(), e);
	  }
   }

   /**
	* Задаёт новый ключ. Используется при первом запуске.
	*
	* @param key объект ключа.
	*/
   public void setNewKey(Key key) {
	  if (key == null) {
		 throw new PBKException("Не задано значение ключа.");
	  }
	  try {
		 SecurityKey domain = new SecurityKey();
		 domain.setCreateDate(new Date());
		 domain.setKeyContent(serializeKey(key));
		 keyMapping.setNewKey(domain);
	  } catch (PBKException e) {
		 throw e;
	  } catch (Exception e) {
		 throw new PBKException("Не удалось записать ключ в БД. Ошибка: " + e.getMessage(), e);
	  }
   }

   private Key deserializeKey(SecurityKey keyDomain) {
	  try (ByteArrayInputStream byteIn = new ByteArrayInputStream(keyDomain.getKeyContent());
		   ObjectInputStream objIn = new ObjectInputStream(byteIn);) {
		 Key key = (Key) objIn.readObject();
		 return key;
	  } catch (IOException | ClassNotFoundException e) {
		 throw new PBKException("Не удалось прочитать ключ. Ошибка: " + e.getMessage(), e);
	  }
   }

   private byte[] serializeKey(Key key) {
	  try (ByteArrayOutputStream out = new ByteArrayOutputStream();
		   ObjectOutputStream objOut = new ObjectOutputStream(out);) {
		 objOut.writeObject(key);
		 return out.toByteArray();
	  } catch (IOException e) {
		 throw new PBKException("Не смогли сериализовать ключ", e);
	  }
   }
}
