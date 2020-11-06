package armd.utils;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class DocumentUtils {

   public static final String ENCODING_UTF_8 = "UTF-8";


   public static String PrettyDocumentToString(Document doc, String encoding)
	   throws UnsupportedEncodingException {
	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  ElementToStream(doc.getDocumentElement(), baos);
	  return new String(baos.toByteArray(), encoding);
   }

   public static void ElementToStream(Element element, OutputStream out) {
	  try {
		 DOMSource source = new DOMSource(element);
		 StreamResult result = new StreamResult(out);
		 TransformerFactory transFactory = TransformerFactory.newInstance();
		 Transformer transformer = transFactory.newTransformer();
		 transformer.transform(source, result);
	  } catch (Exception e) {
		 e.printStackTrace();
	  }
   }

}
