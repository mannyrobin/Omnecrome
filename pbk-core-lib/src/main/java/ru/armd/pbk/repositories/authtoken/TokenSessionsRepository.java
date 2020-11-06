package ru.armd.pbk.repositories.authtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.domain.authtoken.TokenSession;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.authtoken.TokenSessionsMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Репозиторий для пользовательских сессий.
 */
@Repository
public class TokenSessionsRepository
	extends BaseComponent {

   @Autowired
   private TokenSessionsMapper mapper;

   /**
	* Возвращает сессию по идентификатору.
	*
	* @param sessionId идентификатор сессии.
	* @return
	*/
   public TokenSession getSessionBySessionId(String sessionId) {
	  try {
		 return mapper.getSessionBySessionId(sessionId);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_SESSION, AuditObjOperation.SELECT, null,
			 "Не удалось получить сессию для токена. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось получить сессию для токена. Ошибка:" + e.getMessage(), e);
	  }
   }

   /**
	* Добавляет новую сессию.
	*
	* @param session новая сессия.
	*/
   public void addSession(TokenSession session) {
	  try {
		 mapper.addSession(session);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_SESSION, AuditObjOperation.INSERT, null,
			 "Не удалось создать сессию. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось создать сессию. Ошибка:" + e.getMessage(), e);
	  }
   }

   /**
	* Обновляет сессию, не даёт ей протухнуть.
	*
	* @param session сессия.
	*/
   public void updateSession(TokenSession session) {
	  try {
		 mapper.updateSession(session);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_SESSION, AuditObjOperation.UPDATE, null,
			 "Не удалось обновить сессию. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось обновить сессию. Ошибка:" + e.getMessage(), e);
	  }
   }

   /**
	* Удаляет ненужную больше сессию.
	*
	* @param sessionId идентификатор сессии.
	*/
   public void deleteSessionBySessionId(String sessionId) {
	  try {
		 mapper.deleteSessionBySessionId(sessionId);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_SESSION, AuditObjOperation.DELETE, null,
			 "Не удалось удалить сессию. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось удалить сессию. Ошибка:" + e.getMessage(), e);
	  }
   }

   /**
	* Удаляет все сесссии.
	*/
   public void cleanSessions() {
	  try {
		 mapper.cleanSessions();
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_SESSION, AuditObjOperation.DELETE, null,
			 "Не удалось очистить старые сессию. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось очистить старые сессии. Ошибка:" + e.getMessage(), e);
	  }
   }

   /**
	* Удаляет сесси старше переданной даты.
	*
	* @param criteriaDate критерий.
	*/
   public void cleanOldSessions(Date criteriaDate) {
	  try {
		 mapper.cleanOldSessions(criteriaDate);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_SESSION, AuditObjOperation.DELETE, null,
			 "Не удалось очистить старые сессию. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось очистить старые сессии. Ошибка:" + e.getMessage(), e);
	  }
   }

   /**
	* Получает сессии старше переданной даты.
	*
	* @param criteriaDate критерий.
	*/
   public List<TokenSession> getOldSessions(Date criteriaDate) {
	  try {
		 return mapper.getOldSessions(criteriaDate);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_SESSION, AuditObjOperation.DELETE, null,
			 "Не удалось получить старые сессию. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось очистить старые сессии. Ошибка:" + e.getMessage(), e);
	  }
   }

   /**
	* Проверяет сессию, если сессия валидна продлевает её действие.
	*
	* @param sessionId идентификатор сессии.
	*/
   public Boolean checkAndProlongToken(String sessionId) {
	  try {
		 HashMap<String, Object> params = new HashMap<String, Object>();
		 params.put("sessionId", sessionId);
		 params.put("updateDate", new Date());
		 params.put("result", null);
		 mapper.checkAndProlongToken(params);
		 if (!params.containsKey("result") || !(params.get("result") instanceof Boolean)) {
			return null;
		 } else {
			return (Boolean) params.get("result");
		 }
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_SESSION, AuditObjOperation.DELETE, null,
			 "Не удалось проверить и продлить сессию для токена. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось проверить и продлить сессию для токена. Ошибка:" + e.getMessage(), e);
	  }
   }
}
