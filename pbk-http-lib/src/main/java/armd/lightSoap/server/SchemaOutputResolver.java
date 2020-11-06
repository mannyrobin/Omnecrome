package armd.lightSoap.server;

import armd.core.StringMap;

import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class SchemaOutputResolver
	extends javax.xml.bind.SchemaOutputResolver {

   private StringMap schemas = new StringMap();

   private String xsdExtension = "";

   public SchemaOutputResolver(String xsdExtension) {
	  this.xsdExtension = xsdExtension;
   }

   private List<SchemaOutputEntity> results = new ArrayList<SchemaOutputEntity>();

   public SchemaOutputEntity[] getResults() {
	  return results.toArray(new SchemaOutputEntity[] {});
   }

   @Override
   public Result createOutput(String namespaceUri, String suggestedFileName)
	   throws IOException {

	  DOMResult result = new DOMResult();

	  String name = null;
	  if (schemas.containsKey(suggestedFileName)) {
		 name = schemas.get(suggestedFileName);
	  } else {
		 name = String.format("%03d." + xsdExtension, schemas.size() + 1);
		 schemas.put(suggestedFileName, name);
	  }


	  result.setSystemId(name);


	  results.add(new SchemaOutputEntity(name, namespaceUri, result));

	  return result;
   }
}
