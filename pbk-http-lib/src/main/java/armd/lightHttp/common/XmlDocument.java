package armd.lightHttp.common;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;

import static org.w3c.dom.Node.ELEMENT_NODE;

/**
 */
public class XmlDocument {


   private Document document = null;

   private String encodingName = "utf-8";

   private Element rootElement = null;


   private static final String XMLNAMESPACE = "xmlns";

   private int prefixGeneratorNumber = 1;


   public String resolveNamespacePrefix(String namespace) {
	  return resolveNamespacePrefix(document.getDocumentElement(), namespace);
   }

   public String resolveNamespacePrefix(Element element, String namespace) {

	  String prefix = "nsg" + prefixGeneratorNumber;

	  String res = resolveNamespacePrefix(element, namespace, prefix);
	  if (prefix.equals(res)) {
		 prefixGeneratorNumber = prefixGeneratorNumber + 1;
	  }

	  return res;
   }

   public String resolveNamespacePrefix(String namespace, String prefix) {
	  return resolveNamespacePrefix(document.getDocumentElement(), namespace, prefix);
   }

   public String resolveNamespacePrefix(Element element, String namespace, String prefix) {
	  String pre = getPrefixRecursive(element, namespace);
	  if (pre != null) {
		 return pre;
	  }
	  element.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, XMLNAMESPACE + ":" + prefix, namespace);
	  return prefix;
   }

   public String getPrefixRecursive(Element el, String ns) {
	  String prefix = getPrefix(el, ns);
	  if (prefix == null && el.getParentNode() instanceof Element) {
		 prefix = getPrefixRecursive((Element) el.getParentNode(), ns);
	  }
	  return prefix;
   }

   private String getPrefix(Element el, String ns) {
	  NamedNodeMap atts = el.getAttributes();
	  for (int i = 0; i < atts.getLength(); i++) {
		 Node node = atts.item(i);
		 String name = node.getNodeName();
		 if (ns.equals(node.getNodeValue())
			 && (name != null && (XMLNAMESPACE.equals(name) || name.startsWith(XMLNAMESPACE + ":")))) {
			return node.getLocalName();
		 }
	  }
	  return null;
   }


   protected XmlDocument() {

   }

   public XmlDocument(String name)
	   throws ParserConfigurationException {
	  this(name, null, null);
   }

   public XmlDocument(String name, String ns, String prefix)
	   throws ParserConfigurationException {
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  factory.setNamespaceAware(true);
	  DocumentBuilder documentBuilder = factory.newDocumentBuilder();
	  document = documentBuilder.newDocument();

	  this.rootElement = createElement(name, ns, prefix);
	  document.appendChild(rootElement);
   }

   public XmlDocument(Document document) {
	  this.document = document;
	  this.rootElement = document.getDocumentElement();
   }

   public Document getDocument() {
	  return document;
   }

   public Element getRootElement() {
	  return rootElement;
   }

   public static XmlDocument load(InputStream stream)
	   throws ParserConfigurationException, SAXException, IOException {
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  factory.setNamespaceAware(true);
	  DocumentBuilder documentBuilder = factory.newDocumentBuilder();
	  Document document = documentBuilder.parse(stream);

	  XmlDocument res = new XmlDocument();
	  res.document = document;
	  res.rootElement = document.getDocumentElement();

	  return res;
   }


   public XmlDocumentNode getElement(String name)
	   throws URISyntaxException {
	  return getElement(name, null);
   }

   public XmlDocumentNode getElement(String name, String ns)
	   throws URISyntaxException {
	  return getElementFromList(document.getChildNodes(), name, ns);
   }


   public static XmlDocumentNode getElementFromList(NodeList list, String name, String ns)
	   throws URISyntaxException {
	  if (name == null) {
		 name = "";
	  }

	  name = name.trim();

	  URI nsURI = null;
	  if ((ns != null) && (!ns.isEmpty())) {
		 nsURI = new URI(ns);
	  }

	  for (int i = 0; i < list.getLength(); i++) {

		 Node node = list.item(i);

		 if (node.getNodeType() == ELEMENT_NODE) {
			if (node.getLocalName().equals(name)) {
			   if (nsURI != null) {
				  URI nodeURI = new URI(node.getNamespaceURI());

				  if (nodeURI.equals(nsURI)) {
					 return new XmlDocumentNode(Element.class.cast(node));
				  }
			   } else {
				  return new XmlDocumentNode(Element.class.cast(node));
			   }
			}
		 }
	  }

	  return XmlDocumentNode.empty;
   }


   public Element createElement(String name, String ns, String prefix) {

	  Element element = null;
	  if (ns == null || ns.isEmpty()) {
		 element = document.createElement(name);
	  } else {
		 element = document.createElementNS(ns, name);
		 element.setPrefix(prefix);
	  }

	  return element;
   }

   public Attr createAttr(String name, String ns, String prefix) {

	  Attr attr = null;

	  if (ns == null || ns.isEmpty()) {
		 attr = document.createAttribute(name);
	  } else {
		 attr = document.createAttributeNS(ns, name);
		 attr.setPrefix(prefix);
	  }

	  return attr;
   }

   public Element addElement(Element target, String name, String ns, String prefix) {

	  Element element = createElement(name, ns, prefix);

	  target.appendChild(element);


	  return element;
   }


   public Element addElement(Element target, String name) {

	  Element element = createElement(name, null, null);

	  target.appendChild(element);

	  return element;
   }


   public Attr addAttr(Element target, String name, String ns, String prefix, String value) {

	  Attr attr = createAttr(name, ns, prefix);

	  attr.setValue(value == null ? "" : value);

	  target.getAttributes().setNamedItemNS(attr);

	  return attr;
   }


   public Attr addAttr(Element target, String name, String value) {

	  Attr attr = createAttr(name, null, null);

	  attr.setValue(value == null ? "" : value);

	  target.getAttributes().setNamedItemNS(attr);

	  return attr;
   }


   public Element addTextElement(Element target, String name, String ns, String prefix, String content) {

	  Element element = addElement(target, name, ns, prefix);

	  element.appendChild(document.createTextNode(content == null ? "" : content));

	  return element;
   }

   public Element addTextElement(Element target, String name, String content) {

	  Element element = addElement(target, name);

	  element.appendChild(document.createTextNode(content == null ? "" : content));


	  return element;
   }


   public Element addCDataElement(Element target, String name, String content) {

	  Element element = addElement(target, name);

	  element.appendChild(document.createCDATASection(content == null ? "" : content));


	  return element;
   }


   public Element addCDataElement(Element target, String name, String ns, String prefix, String content) {

	  Element element = addElement(target, name, ns, prefix);

	  element.appendChild(document.createCDATASection(content == null ? "" : content));

	  return element;
   }


   public void write(OutputStream stream)
	   throws TransformerConfigurationException, TransformerException {
	  Transformer transformer = TransformerFactory.newInstance().newTransformer();
	  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	  transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
	  transformer.setOutputProperty(OutputKeys.ENCODING, encodingName);
	  transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

	  StreamResult streamResult = new StreamResult(stream);

	  DOMSource source = new DOMSource(document);
	  transformer.transform(source, streamResult);
   }

   public void write(Writer writer)
	   throws TransformerConfigurationException, TransformerException {
	  Transformer transformer = TransformerFactory.newInstance().newTransformer();
	  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	  transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
	  transformer.setOutputProperty(OutputKeys.ENCODING, encodingName);
	  transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

	  StreamResult streamResult = new StreamResult(writer);

	  DOMSource source = new DOMSource(document);
	  transformer.transform(source, streamResult);
   }
}
