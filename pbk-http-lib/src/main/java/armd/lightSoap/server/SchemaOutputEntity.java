package armd.lightSoap.server;


import javax.xml.transform.dom.DOMResult;

public class SchemaOutputEntity {
   private String name;
   private String ns;
   private DOMResult domResult;

   public SchemaOutputEntity(String name, String ns, DOMResult domResult) {
	  this.name = name;
	  this.ns = ns;
	  this.domResult = domResult;
   }

   public String getName() {
	  return name;
   }

   public String getNs() {
	  return ns;
   }

   public DOMResult getDomResult() {
	  return domResult;
   }
}
