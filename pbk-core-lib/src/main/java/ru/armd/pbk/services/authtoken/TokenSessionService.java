package ru.armd.pbk.services.authtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseService;
import ru.armd.pbk.domain.authtoken.TokenSession;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.repositories.authtoken.TokenSessionsRepository;
import ru.armd.pbk.services.core.AuditService;

import java.util.Date;
import java.util.List;

/**
 * Сервис проверки актуальности и продления токенов.
 */
@Service
public class TokenSessionService
	extends BaseService {

   private static final Long MAX_SESSION_INACTIVITY_TIME = 30 * 60 * 1000L;

   @Autowired
   private TokenSessionsRepository sessionRepo;

   @Autowired
   private AuditService auditService;

   /**
	* Проверяет сессию, если сессия валидна продлевает её действие.
	*
	* @param sessionId идентификатор сессии.
	* @return
	*/
   public boolean checkAndProlongToken(String sessionId) {
		/* 
		Код вынесен в хранимку
		try {
			TokenSession session = sessionRepo.getSessionBySessionId(sessionId);
			if (session == null) {
				return false;
			}
			if (checkTokenSession(session)) {
				session.setUpdateDate(new Date());
				sessionRepo.updateSession(session);
				return true;
			} else {
				sessionRepo.deleteSessionBySessionId(sessionId);
				return false;
			}
		} catch (PBKException e) {
			throw e;
		} catch (Exception e) {
			logAudit(AuditLevel.ERROR, CoreAuditType.CORE_SESSION, AuditObjOperation.UPDATE, null,
					"Не удалось проверить и продлить сессию для токена. Ошибка:" + e.getMessage(), e);
			throw new PBKException("Не удалось проверить и продлить сессию для токена. Ошибка:" + e.getMessage(), e);
		}*/

	  Boolean ok = sessionRepo.checkAndProlongToken(sessionId);
	  if (ok == null) {
		 // Сессия отсутствует
		 return false;
	  } else if (!ok) {
		 // Сессия протухла
		 return false;
	  } else {
		 return true;
	  }
   }

   private boolean checkTokenSession(TokenSession session) {
	  return (new Date().getTime() - session.getUpdateDate().getTime()) < MAX_SESSION_INACTIVITY_TIME;
   }

   /**
	* Создаёт новую сессию.
	*
	* @param userDetails информация о пользователе.
	* @param sessionId   идентификатор сессии.
	* @param ipAddress   адресс клиента.
	* @param token       токен связанный с сессией.
	*/
   @Transactional
   public void createNewSession(UserDetails userDetails, String sessionId, String ipAddress, String token) {
	  try {
		 TokenSession session = new TokenSession();
		 session.setCreateDate(new Date());
		 session.setUpdateDate(new Date());
		 session.setIpAddress(ipAddress);
		 session.setUserLogin(userDetails.getUsername());
		 session.setSessionId(sessionId);
		 session.setSessionToken(token);
		 sessionRepo.addSession(session);
	  } catch (PBKException e) {
		 throw e;
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_SESSION, AuditObjOperation.INSERT, null,
			 "Не удалось создать сессию для токена. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось создать сессию для токена. Ошибка:" + e.getMessage(), e);
	  }
   }

   /**
	* Удаляет сессию.
	*
	* @param sessionId идентификатор сессии.
	*/
   @Transactional
   public void deleteSession(String sessionId) {
	  auditService.logLogout(sessionRepo.getSessionBySessionId(sessionId).getUserLogin());
	  sessionRepo.deleteSessionBySessionId(sessionId);
   }

   /**
	* Удаляет старые сессии.
	*/
   @Transactional
   public void cleanOldSessions() {
	  Date aliveSessionsCriteriaDate = new Date(System.currentTimeMillis() - MAX_SESSION_INACTIVITY_TIME);
	  List<TokenSession> sessions = sessionRepo.getOldSessions(aliveSessionsCriteriaDate);
	  sessionRepo.cleanOldSessions(aliveSessionsCriteriaDate);
	  for (TokenSession session : sessions) {
		 auditService.logLogout(session.getUserLogin());
	  }
   }

}
