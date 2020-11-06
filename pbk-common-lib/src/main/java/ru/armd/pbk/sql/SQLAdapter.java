package ru.armd.pbk.sql;

/**
 * SQL адаптер.
 */
public class SQLAdapter {
   String sql;

   /**
	* Конструктор.
	*
	* @param sql строка sql.
	*/
   public SQLAdapter(String sql) {
	  this.sql = sql;
   }

   public String getSql() {
	  return sql;
   }

   public void setSql(String sql) {
	  this.sql = sql;
   }
}
