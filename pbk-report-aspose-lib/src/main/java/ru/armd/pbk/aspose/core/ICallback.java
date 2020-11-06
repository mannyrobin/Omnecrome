package ru.armd.pbk.aspose.core;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Node;

import java.util.Map;

public interface ICallback {
   void callback(Document doc, DocumentBuilder builder, Map<String, Node> format)
	   throws Exception;
}
