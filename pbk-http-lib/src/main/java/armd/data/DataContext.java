package armd.data;

/**
 */


public interface DataContext {

   Source getConnection();

   Source getConnection(String name);
}

