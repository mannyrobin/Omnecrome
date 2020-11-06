package armd.lightSoap.server;

/**
 */
public class WsdlSchemaClassName {
   private String namespace;
   private String name;

   private Class<?> cl;

   public WsdlSchemaClassName(Class<?> cl, String name, String namespace) {
	  this.name = name;
	  this.namespace = namespace;
	  this.cl = cl;
   }

   public String getNamespace() {
	  return namespace;
   }

   public String getName() {
	  return name;
   }


   public Class<?> getCl() {
	  return cl;
   }

   @Override
   public boolean equals(Object o) {
	  if (this == o) {
		 return true;
	  }
	  if (!(o instanceof WsdlSchemaClassName)) {
		 return false;
	  }

	  WsdlSchemaClassName that = (WsdlSchemaClassName) o;

	  if (!cl.equals(that.cl)) {
		 return false;
	  }
	  if (!name.equals(that.name)) {
		 return false;
	  }
	  if (!namespace.equals(that.namespace)) {
		 return false;
	  }

	  return true;
   }

   @Override
   public int hashCode() {
	  int result = cl.hashCode();
	  return result;
   }
}
