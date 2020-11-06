package armd.data;

/**
 */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface Cursor {
   <T> T ReadObject(Class<T> cl)
	   throws SQLException, IOException, InvocationTargetException, IllegalAccessException, InstantiationException;

   <T> List<T> ReadList(Class<T> cl)
	   throws SQLException, IOException, InvocationTargetException, IllegalAccessException, InstantiationException;
}
