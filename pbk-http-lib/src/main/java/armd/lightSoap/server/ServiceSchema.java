package armd.lightSoap.server;

import armd.lightHttp.common.XmlDocument;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class ServiceSchema {
   private String ns;
   private String name;
   private XmlDocument content;

   public ServiceSchema(String ns, String name, XmlDocument content) {
	  this.ns = ns;
	  this.name = name;
	  this.content = content;
   }

   public String getNamespace() {
	  return ns;
   }

   public String getName() {
	  return name;
   }

   public XmlDocument getContent() {
	  return content;
   }

   public void write(ResponseBuffer responseBuffer)
	   throws TransformerException, IOException {
	  OutputFormat format = new OutputFormat(content.getDocument());
	  format.setIndenting(true);
	  XMLSerializer serializer = new XMLSerializer(responseBuffer, format);
	  serializer.serialize(content.getDocument());
   }

}
