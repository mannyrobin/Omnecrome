package armd.data;

/**
 */

import javax.naming.NamingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface Source {

   Command createSql(String sqlText);

   Command createStoredProcedure(String name);

   void close();

   void closeCommand(Command command);

   void execute(Command command)
	   throws SQLException, IOException, NamingException, IllegalAccessException, InvocationTargetException;

   <TEntity> List<TEntity> getList(Command command, Class<TEntity> cl)
	   throws SQLException, NamingException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException;

   <TEntity> TEntity getObject(Command command, Class<TEntity> cl)
	   throws SQLException, NamingException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
