package armd.data;

/**
 */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;


public class SqlParameter<T> {

   private SqlParameterDirection direction = SqlParameterDirection.In;
   private String name;
   private SqlParameterType type;
   private int size = -1;
   private int precision = -1;
   private int scale = -1;
   private T value = null;
   private Class<T> targetType = null;

   public SqlParameter(Class<T> targetType) {

   }

   public SqlParameter(Class<T> targetType, SqlParameterDirection direction, String name, SqlParameterType type, T value, int size, int precision, int scale) {

	  this.targetType = targetType;
	  this.direction = direction;
	  this.name = name;
	  this.type = type;
	  this.value = value;
	  this.size = size;
	  this.precision = precision;
	  this.scale = scale;
   }

   public SqlParameter(Class<T> targetType, SqlParameterDirection direction, String name, SqlParameterType type) {

	  this.targetType = targetType;
	  this.direction = direction;
	  this.name = name;
	  this.type = type;
	  this.size = -1;
	  this.precision = -1;
	  this.scale = -1;
   }

   public Class<T> getTargetType() {
	  return targetType;
   }

   public SqlParameterDirection getDirection() {
	  return direction;
   }

   public String getName() {
	  return name;
   }

   public SqlParameterType getType() {
	  return type;
   }

   public int getSize() {
	  return size;
   }

   public int getPrecision() {
	  return precision;
   }

   public int getScale() {
	  return scale;
   }

   public T getValue() {
	  return value;
   }

   public void setValue(T value) {
	  this.value = value;
   }

   public void setObjectValue(Object obj) {
	  this.value = this.targetType.cast(obj);
   }

   public <TEntity> TEntity readObject(Class<TEntity> cl)
	   throws SQLException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
	  if (Cursor.class != targetType) {
		 throw new IllegalArgumentException();
	  }

	  if (value == null) {
		 return null;
	  }

	  Cursor cursor = (Cursor) value;

	  return cursor.ReadObject(cl);
   }

   public <TEntity> List<TEntity> readList(Class<TEntity> cl)
	   throws SQLException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
	  if (Cursor.class != targetType) {
		 throw new IllegalArgumentException();
	  }

	  if (value == null) {
		 return null;
	  }

	  Cursor cursor = (Cursor) value;

	  return cursor.ReadList(cl);
   }
}