package ru.armd.pbk.enums.core;

/**
 * Created by Yakov Volkov.
 */
public enum Legitimate {

   UNDEFINED(null, "Не указан"),
   LEGITIMATE(true, "Легитимный"),
   NOT_LEGITIMATE(false, "Не лигитимный");

   private final String caption;
   private final Boolean value;

   Legitimate(Boolean value, String caption) {
	  this.value = value;
	  this.caption = caption;
   }

   public String getCaption() {
	  return caption;
   }

   public Boolean getValue() {
	  return value;
   }
}
