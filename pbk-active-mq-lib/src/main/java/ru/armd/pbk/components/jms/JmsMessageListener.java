package ru.armd.pbk.components.jms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Компонент, слушатель JMS сообщений.
 */
@Component
public class JmsMessageListener {

   public static final Logger LOGGER = Logger.getLogger(JmsMessageListener.class);

   @Autowired
   JmsTemplate jmsTemplate;

   /**
	* Конструктор по умолчанию.
	*/
   public JmsMessageListener() {
	  // Устанавливаем разрешение на пакет для сериализации
	  System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
   }

   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Обработать тестовое сообщение.
	*
	* @param pbkMessage сообщение.
	* @return
	*/
   @JmsListener(destination = "test")
   public PbkMessage processMessage(PbkMessage pbkMessage) {
	  getLogger().info("processMessage " + pbkMessage.getMessage());
	  return pbkMessage;
   }

   /**
	* Отправить сообщение.
	*
	* @param pbkMessage сообщение.
	*/
   public void sendMessage(PbkMessage pbkMessage) {
	  try {
		 jmsTemplate.convertAndSend("test", pbkMessage);
	  } catch (Exception e) {
		 getLogger().error(e.getMessage(), e);
	  }
	  getLogger().info("Send message" + pbkMessage.getMessage());
   }

}
