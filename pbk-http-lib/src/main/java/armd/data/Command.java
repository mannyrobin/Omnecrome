package armd.data;

/**
 */


import armd.core.ArrayUtils;

import javax.naming.NamingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Command {

   private static final String resultParameterName = "resultAf6677a9f024aab1fdb2";

   private HashMap<String, SqlParameter<?>> sqlParameters = new HashMap<String, SqlParameter<?>>();

   private Source source;

   private String sqlText;

   private CommandType commandType = CommandType.SqlText;

   public String getSqlText() {
	  return this.sqlText;
   }

   public void setSqlText(String sqlText) {
	  this.sqlText = sqlText;
   }

   public CommandType getCommandType() {
	  return this.commandType;
   }

   public void setCommandType(CommandType commandType) {
	  this.commandType = commandType;
   }

   public void setSource(Source source) {
	  if (this.source != null) {
		 if (this.source != source) {
			throw new RuntimeException("The Data Source has been set");
		 }
	  }
	  this.source = source;
   }

   public Source getSource() {
	  return this.source;
   }

   public <T> SqlParameter<T> addParameter(Class<T> cl, SqlParameter<T> parameter) {
	  String name = parameter.getName();
	  if (name == null) {
		 name = "";
	  }
	  name = name.trim().toLowerCase();
	  if (name.length() == 0) {
		 throw new RuntimeException("The name of parameter cannot be null");
	  }

	  sqlParameters.put(name, parameter);

	  return parameter;
   }

   public <T> SqlParameter<T> addIn(Class<T> cl, String name, SqlParameterType type, T value) {

	  return addParameter(cl, new SqlParameter<T>(cl, SqlParameterDirection.In, name, type, value, -1, -1, -1));
   }

   public <T> SqlParameter<T> addIn(Class<T> cl, String name, T value) {

	  return addParameter(cl, new SqlParameter<T>(cl, SqlParameterDirection.In, name, SqlParameterType.Value, value, -1, -1, -1));
   }

   public SqlParameter<String> addInClob(String name, String value) {
	  return addParameter(String.class, new SqlParameter<String>(String.class, SqlParameterDirection.In, name, SqlParameterType.Clob, value, -1, -1, -1));
   }

   public SqlParameter<byte[]> addInBlob(String name, byte[] value) {
	  return addParameter(byte[].class, new SqlParameter<byte[]>(byte[].class, SqlParameterDirection.In, name, SqlParameterType.Blob, value, -1, -1, -1));
   }


   public <T> SqlParameter<T> addOut(Class<T> cl, String name, SqlParameterType type) {
	  return addParameter(cl, new SqlParameter<T>(cl, SqlParameterDirection.Out, name, type));
   }

   public <T> SqlParameter<T> addOut(Class<T> cl, String name) {

	  return addParameter(cl, new SqlParameter<T>(cl, SqlParameterDirection.Out, name, SqlParameterType.Value));
   }


   public SqlParameter<Cursor> addOutCursor(String name) {
	  return addParameter(Cursor.class, new SqlParameter<Cursor>(Cursor.class, SqlParameterDirection.Out, name, SqlParameterType.RefCursor));
   }


   public SqlParameter<String> addOutClob(String name) {
	  return addParameter(String.class, new SqlParameter<String>(String.class, SqlParameterDirection.Out, name, SqlParameterType.Clob));
   }

   public SqlParameter<byte[]> addOutBlob(String name) {
	  return addParameter(byte[].class, new SqlParameter<byte[]>(byte[].class, SqlParameterDirection.Out, name, SqlParameterType.Blob));
   }


   public <T> SqlParameter<T> addReturn(Class<T> cl)
	   throws IllegalArgumentException {

	  if (getParametersByDirection(SqlParameterDirection.Return).length > 0) {
		 throw new IllegalArgumentException("Too many return parameters");
	  }

	  return addParameter(cl, new SqlParameter<T>(cl, SqlParameterDirection.Return, resultParameterName, SqlParameterType.Value));
   }

   public SqlParameter<Cursor> addReturnCursor() {
	  if (getParametersByDirection(SqlParameterDirection.Return).length > 0) {
		 throw new IllegalArgumentException("Too many return parameters");
	  }

	  return addParameter(Cursor.class, new SqlParameter<Cursor>(Cursor.class, SqlParameterDirection.Return, resultParameterName, SqlParameterType.RefCursor));
   }

   public SqlParameter<?>[] getParametersByDirection(SqlParameterDirection direction) {
	  ArrayList<SqlParameter<?>> res = new ArrayList<SqlParameter<?>>();
	  for (SqlParameter<?> p : sqlParameters.values()) {
		 if (p.getDirection() == direction) {
			res.add(p);
		 }
	  }


	  return ArrayUtils.toArray(SqlParameter.class, res);
   }

   public <TEntity> TEntity getObject(Class<TEntity> cl)
	   throws SQLException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException, NamingException {
	  return source.getObject(this, cl);
   }

   public <TEntity> List<TEntity> getList(Class<TEntity> cl)
	   throws SQLException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException, NamingException {
	  return source.getList(this, cl);
   }

   public void execute()
	   throws SQLException, IOException, NamingException, IllegalAccessException, InvocationTargetException {
	  source.execute(this);
   }

   public void close() {
	  source.closeCommand(this);
   }
}
