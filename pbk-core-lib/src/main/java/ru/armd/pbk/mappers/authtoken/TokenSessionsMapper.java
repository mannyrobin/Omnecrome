package ru.armd.pbk.mappers.authtoken;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.domain.authtoken.TokenSession;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Маппер для сессий токенов.
 */
@IsMapper
public interface TokenSessionsMapper {

   /**
	* Возвращает сессию по идентификатору.
	*
	* @param sessionId идентификатор сессии.
	* @return
	*/
   TokenSession getSessionBySessionId(String sessionId);

   /**
	* Добавляет новую сессию.
	*
	* @param session сессия
	*/
   void addSession(TokenSession session);

   /**
	* Обновляет состояние сессии.
	*
	* @param session сессия
	*/
   void updateSession(TokenSession session);

   /**
	* Удаляет протухшую сессию по идентификатору.
	*
	* @param sessionId идентификатор сессии.
	*/
   void deleteSessionBySessionId(String sessionId);

   /**
	* Удаляет все сессии.
	*/
   void cleanSessions();

   /**
	* Удаляет сесси старше заданной даты.
	*
	* @param criteriaDate критейрий.
	*/
   void cleanOldSessions(Date criteriaDate);

   /**
	* Выбирает сессии старше заданой даты.
	*
	* @param criteriaDate дата.
	* @return
	*/
   List<TokenSession> getOldSessions(Date criteriaDate);

   /**
	* Проверяет сессию, если сессия валидна продлевает её действие.
	*
	* @param map параметры.
	*/
   void checkAndProlongToken(HashMap<String, Object> map);

}
