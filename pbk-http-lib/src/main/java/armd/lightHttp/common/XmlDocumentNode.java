package armd.lightHttp.common;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URISyntaxException;

/**
 */
public class XmlDocumentNode {

   private Element element = null;

   private XmlDocumentNode() {

   }


   public static final XmlDocumentNode empty = new XmlDocumentNode();

   public XmlDocumentNode(Element element) {
	  this.element = element;
   }

   public Element getElement() {
	  return element;
   }

   public XmlDocumentNode getElement(String name)
	   throws URISyntaxException {
	  return getElement(name, null);
   }


   public XmlDocumentNode getElement(String name, String ns)
	   throws URISyntaxException {

	  if (element == null) {
		 return empty;
	  }

	  return XmlDocument.getElementFromList(element.getChildNodes(), name, ns);
   }

   public XmlDocumentNode getFirstElement() {
	  if (element == null) {
		 return empty;
	  }

	  NodeList list = element.getChildNodes();

	  for (int i = 0; i < list.getLength(); i++) {
		 Node child = list.item(i);

		 if (Element.class.isInstance(child)) {
			return new XmlDocumentNode((Element) child);
		 }
	  }

	  return empty;
   }
}
