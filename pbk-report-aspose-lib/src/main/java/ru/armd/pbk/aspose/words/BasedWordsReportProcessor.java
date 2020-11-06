package ru.armd.pbk.aspose.words;

import com.aspose.words.Bookmark;
import com.aspose.words.BookmarkCollection;
import com.aspose.words.CustomDocumentProperties;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.DocumentProperty;
import com.aspose.words.Field;
import com.aspose.words.IFieldMergingCallback;
import com.aspose.words.MailMergeCleanupOptions;
import com.aspose.words.Node;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.Run;
import com.aspose.words.RunCollection;
import org.apache.log4j.Logger;
import ru.armd.pbk.aspose.aspose.ColoringRegionsHandler;
import ru.armd.pbk.aspose.aspose.EmptyRegionsHandler;
import ru.armd.pbk.aspose.aspose.EmptyRegionsHandlerType;
import ru.armd.pbk.aspose.aspose.MailMergeDataSet;
import ru.armd.pbk.aspose.aspose.MailMergeDataSource;
import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.IReportNameData;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.tag.IfTag;
import ru.armd.pbk.aspose.tag.IfTagProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Custom properties based processor. Faster even on single calls. Cacheable, since entries to be
 * changed are name-value in nature.
 */
public class BasedWordsReportProcessor
	extends AbstractReportProcessor {

   /**
	* Префикс предобработанных шаблонов.
	*/
   public static final String PREPROCESSED_TEMPLATE_PREFIX = "PREPROCESSED_";

   private static final Logger logger = Logger.getLogger(BasedWordsReportProcessor.class);

   private static final String SEARCH_PREFIX = "#$";
   private static final String SEARCH_POSTFIX = "$#";

   private static final EmptyRegionsHandlerType DEFAULT_HANDLER_TYPE = EmptyRegionsHandlerType.REMOVE_TABLE;

   private Document doc = null;
   private LinkedList<IfTag> ifTags = null;

   @Override
   public void cleanup() {
	  doc = null;
   }

   /**
	* Performs pre-processing of document template. Replaces occurences of placeholders formed as:
	* SEARCH_PREFIX + item from templateData + SEARCH_POSTFIX
	* e.g.
	* #$Собственник$#
	* onto Custom Property fields with same name and default value
	*
	* @throws Exception
	*/
   @Override
   public void preprocessTemplate()
	   throws Exception {
	  long t1 = 0L;
	  if (TRACE_PREPROCESS_TEMPLATE) {
		 t1 = System.currentTimeMillis();
	  }

	  Document doc = getDocument();
	  doc.joinRunsWithSameFormatting();
	  convertTagsToFields(doc);
	  savePreprocessedTemplate(doc);

	  long t2 = 0L;
	  if (TRACE_PREPROCESS_TEMPLATE) {
		 t2 = System.currentTimeMillis();
		 logger.debug("\n\tPreprocessing done in " + (t2 - t1) + "ms, result is " + templateName);
	  }
   }

   public void savePreprocessedTemplate()
	   throws Exception {
	  Document doc = getDocument();
	  savePreprocessedTemplate(doc);
   }

   protected void savePreprocessedTemplate(Document doc)
	   throws Exception {
	  //switch processor to use preprocessed template
	  templateName = PREPROCESSED_TEMPLATE_PREFIX + templateName;
	  //save preprocessed template
	  saveDocumentToFile(doc, templateName);
   }

   protected Document createDocument()
	   throws Exception {
	  String dataDir = rootDirectory.getAbsolutePath();
	  return new Document(dataDir + File.separator + templateName);
   }

   public Map<ReportFormat, File> processTemplate(String filename, ReportFormat... formats)
	   throws Exception {
	  long t1 = 0L;
	  if (TRACE_PROCESS_TEMPLATE) {
		 t1 = System.currentTimeMillis();
	  }

	  Document doc = getDocument();
	  processTags(doc);
	  if (templateData != null) {
		 updateDocFields(doc);
	  }
	  if (dataSet != null || (callbacks != null && !callbacks.isEmpty())) {
		 //необходимо работать с копией (уже полученной после обработки custom fields)
		 //так как структура документа после обработки merge fields может поменяться
		 Document copy = doc.deepClone();
		 if (dataSet != null) {
			processMailMerge(copy);
		 }
		 if (callbacks != null && !callbacks.isEmpty()) {
			processCallbacks(copy);
		 }
		 doc = copy;
	  }

	  List<ReportFormat> formatsList = new ArrayList<>();
	  if (formats == null || formats.length == 0) {
		 formatsList.add(ReportFormat.MSWORD);
	  } else {
		 for (ReportFormat format : formats) {
			if (!formatsList.contains(format)) {
			   formatsList.add(format);
			}
		 }
	  }

	  Map<ReportFormat, File> result = saveDocument(doc, formatsList, filename);

	  if (TRACE_PROCESS_TEMPLATE) {
		 long t2 = System.currentTimeMillis();

		 logger.debug(System.lineSeparator() + "\tReady:");
		 if (result != null) {
			for (Entry<ReportFormat, File> entry : result.entrySet()) {
			   logger.debug(String.format("%s\t%s: %s", System.lineSeparator(),
				   entry.getKey().getFileExtension(false).toUpperCase(), entry.getValue().getAbsolutePath()));
			}
		 }

		 logger.debug(String.format("%d ms", t2 - t1));
	  }

	  return result;
   }

   @Override
   public File merge(List<File> reports, String mergedReportName, ReportFormat format) {
	  throw new UnsupportedOperationException("Слияние DOC(X) файлов не реализовано.");
   }

   @Override
   public IReportNameData getResultReportNameData(List<ReportData> reportData) {
	  throw new UnsupportedOperationException("Получение итогового имени отчёта после слияния DOC(X) файлов не реализовано.");
   }

   private void processTags(Document doc)
	   throws Exception {
	  LinkedList<IfTag> ifTags = getIfTags();
	  IfTagProcessor.processIfTags(ifTags, templateData, doc);
	  IfTagProcessor.removeEmptyRowsWithIfTags(ifTags, doc);
   }

   private void convertTagsToFields(Document doc)
	   throws Exception {
	  CustomDocumentProperties props = doc.getCustomDocumentProperties();
	  DocumentBuilder builder = new DocumentBuilder(doc);
	  int callbackBookmarkId = 1;
	  int formatBookmarkId = 1;
	  boolean inTableBlock = false;
	  @SuppressWarnings("unchecked")
	  NodeCollection<Paragraph> paragraphs = doc.getChildNodes(NodeType.PARAGRAPH, true);
	  //noinspection unchecked
	  for (Paragraph paragraph : paragraphs) {

		 String nodeText = paragraph.getText();
		 int idxStart = nodeText.indexOf(SEARCH_PREFIX);
		 int idxEnd = nodeText.indexOf(SEARCH_POSTFIX);
		 while (idxStart != -1 && idxEnd != -1 && idxStart < idxEnd) {
			//logger.trace("nodeText (1)"+nodeText);
			//in that Paragraph we do have string to replace to custom property
			String fieldName = nodeText.substring(idxStart + SEARCH_PREFIX.length(), idxEnd);

			//if templateData are provided, check existence of fieldName against it
			//otherwise, process all snippets that matched the pattern
			if (templateData != null &&
				!templateData.containsKey(fieldName)) {
			   //this is unknown property
			   break;
			}

			if (!props.contains(fieldName)) {
			   props.add(fieldName, fieldName);
			}

			RunCollection rc = paragraph.getRuns();
			for (Run r : rc) {
			   String runText = r.getText();
			   int idxRunStart = runText.indexOf(SEARCH_PREFIX);
			   //the only limitation - SEARCH_PREFIX must reside in one run
			   if (idxRunStart >= 0) {
				  //left side of placeholder is found.
				  //let's insert field here instead
				  Run runForField = splitRun(r, idxRunStart);
				  builder.moveTo(runForField);
				  if (fieldName.startsWith("TableStart:")) {
					 inTableBlock = true;
				  }
				  if (fieldName.startsWith("Callback:")) {
					 String bookmarkName = "TEMPLATE_CALLBACK_" + String.valueOf(callbackBookmarkId);
					 builder.startBookmark(bookmarkName);
					 builder.write(fieldName);
					 builder.endBookmark(bookmarkName);
					 callbackBookmarkId++;
					 break;
				  }
				  if (fieldName.startsWith("Format:")) {
					 String bookmarkName = "TEMPLATE_FORMAT_" + String.valueOf(formatBookmarkId);
					 builder.startBookmark(bookmarkName);
					 builder.write(fieldName);
					 builder.endBookmark(bookmarkName);
					 formatBookmarkId++;
					 break;
				  }
				  if (inTableBlock) {
					 if (fieldName.startsWith("colorif")) {
						int idx1 = fieldName.indexOf("(");
						int idx2 = fieldName.indexOf(")");
						if (idx1 > -1 && idx2 > -1 && idx2 > idx1 + 1) {
						   String dependant = fieldName.substring(idx1 + 1, idx2);
						   String fieldNameRefined = fieldName.substring(0, idx1);
						   Field inserted = builder.insertField("MERGEFIELD  " + fieldNameRefined);
						   builder.moveTo(inserted.getSeparator());
						   builder.insertField("DOCPROPERTY " + dependant);
						}
					 } else {
						builder.insertField("MERGEFIELD  " + fieldName + " ");
					 }
					 if (fieldName.startsWith("TableEnd:")) {
						inTableBlock = false;
					 }
				  } else {
					 if (fieldName.contains(" ")) {
						builder.insertField("DOCPROPERTY  \"" + fieldName + "\"  \\* MERGEFORMAT");
					 } else {
						builder.insertField("DOCPROPERTY  " + fieldName + "  \\* MERGEFORMAT");
					 }
				  }
				  break;
			   }
			   //logger.trace(r.getText());
			}
			//finally, remove placeholder. Replace does good even if styles are mixed inside paragraph
			paragraph.getRange().replace(SEARCH_PREFIX + fieldName + SEARCH_POSTFIX, "", true, false);

			//in one paragraph we can define more than one placeholder
			nodeText = paragraph.getText();
			//logger.trace("nodeText (2)"+nodeText);
			idxStart = nodeText.indexOf(SEARCH_PREFIX);
			idxEnd = nodeText.indexOf(SEARCH_POSTFIX);
		 }
	  }
   }

   private static Run splitRun(Run run, int position) {
	  Run afterRun = (Run) run.deepClone(true);
	  afterRun.setText(run.getText().substring(position));
	  run.setText(run.getText().substring(0, position));
	  run.getParentNode().insertAfter(afterRun, run);
	  return afterRun;
   }

   private void updateDocFields(Document doc)
	   throws Exception {
	  boolean changed = false;
	  for (int i = 0; i < doc.getCustomDocumentProperties().getCount(); i++) {
		 DocumentProperty prop = doc.getCustomDocumentProperties().get(i);

		 String name = prop.getName();
		 String val = templateData.getProperty(name);
		 if (val != null) {
			prop.setValue(val);
			changed = true;
		 }
	  }

	  if (changed) {
		 doc.updateFields();
	  }
   }

   private void processMailMerge(Document doc)
	   throws Exception {
	  doc.getMailMerge().setCleanupOptions(doc.getMailMerge().getCleanupOptions() & ~MailMergeCleanupOptions.REMOVE_UNUSED_REGIONS);
	  //copy.getMailMerge().setCleanupOptions(copy.getMailMerge().getCleanupOptions() & MailMergeCleanupOptions.REMOVE_UNUSED_FIELDS);

	  doc.getMailMerge().setFieldMergingCallback(new ColoringRegionsHandler());
	  doc.getMailMerge().executeWithRegions(dataSet);
	  //copy.getMailMerge().setFieldMergingCallback(null);

	  Collection<MailMergeDataSource<?>> tables = dataSet.getDataSources();
	  if (tables == null) {
		 executeCustomLogicOnEmptyRegions(doc, new EmptyRegionsHandler());
	  } else {
		 Map<EmptyRegionsHandlerType, Set<String>> handlerTablesMap = new HashMap<>();
		 for (MailMergeDataSource<?> table : tables) {
			EmptyRegionsHandlerType type = table.getEmptyRegionsHandlerType();
			if (type == null) {
			   type = DEFAULT_HANDLER_TYPE;
			}

			Set<String> names = handlerTablesMap.get(type);
			if (names == null) {
			   handlerTablesMap.put(type, names = new HashSet<>());
			}
			names.add(table.getTableName());
		 }

		 for (Entry<EmptyRegionsHandlerType, Set<String>> entry : handlerTablesMap.entrySet()) {
			executeCustomLogicOnEmptyRegions(doc, new EmptyRegionsHandler(entry.getKey()), entry.getValue());
		 }
	  }
   }

   private void processCallbacks(Document doc) {
	  DocumentBuilder builder = new DocumentBuilder(doc);
	  BookmarkCollection bookmarks = doc.getRange().getBookmarks();
	  Map<String, Node> cells = new HashMap<String, Node>();

	  for (Bookmark bookmark : bookmarks) {
		 String bookmarkName = bookmark.getName();
		 if (bookmarkName.startsWith("TEMPLATE_FORMAT_")) {
			try {
			   String text = bookmark.getText();
			   String name = text.substring(text.indexOf(":") + 1);
			   builder.moveToBookmark(bookmarkName);
			   cells.put(name, builder.getCurrentNode());
			} catch (Exception e) {

			}
		 }
	  }

	  for (Bookmark bookmark : bookmarks) {
		 String bookmarkName = bookmark.getName();
		 if (bookmarkName.startsWith("TEMPLATE_CALLBACK_")) {
			try {
			   String text = bookmark.getText();
			   String callback = text.substring(text.indexOf(":") + 1);
			   bookmark.setText("");
			   if (callbacks.containsKey(callback)) {
				  builder.moveToBookmark(bookmarkName);
				  callbacks.get(callback).callback(doc, builder, cells);
			   }
			   bookmark.remove();
			} catch (Exception e) {

			}
		 }
	  }
   }

   private static void executeCustomLogicOnEmptyRegions(Document doc, IFieldMergingCallback handler)
	   throws Exception {
	  executeCustomLogicOnEmptyRegions(doc, handler, null);
   }

   private static void executeCustomLogicOnEmptyRegions(Document doc, IFieldMergingCallback handler, Set<?> regionsList)
	   throws Exception {
	  // Certain regions can be skipped from applying logic to by not adding the table name inside the CreateEmptyDataSource method.
	  // Enable this cleanup option so any regions which are not handled by the user's logic are removed automatically.
	  doc.getMailMerge().setCleanupOptions(MailMergeCleanupOptions.REMOVE_UNUSED_REGIONS);

	  // Set the user's handler which is called for each unmerged region.
	  doc.getMailMerge().setFieldMergingCallback(handler);
	  // Execute mail merge using the dummy dataset. The dummy data source contains the table names of
	  // each unmerged region in the document (excluding ones that the user may have specified to be skipped). This will allow the handler
	  // to be called for each field in the unmerged regions.
	  doc.getMailMerge().executeWithRegions(createDataSourceFromDocumentRegions(doc, regionsList));
   }

   public static class NonMappedField {
	  public NonMappedField(String field) {
		 this.field = field;
	  }

	  String field;
   }

   private static MailMergeDataSet createDataSourceFromDocumentRegions(Document doc, Set<?> regionsSet)
	   throws Exception {
	  final String TABLE_START_MARKER = "TableStart:";
	  MailMergeDataSet dataSet = new MailMergeDataSet();
	  String tableName = null;
	  for (String fieldName : doc.getMailMerge().getFieldNames()) {
		 if (fieldName.contains(TABLE_START_MARKER)) {
			tableName = fieldName.substring(TABLE_START_MARKER.length());
		 } else if (tableName != null) {
			// Only add the table as a new DataTable if it doesn't already exists in the DataSet and not skipped by the caller.
			if (!dataSet.hasKey(tableName) && (regionsSet == null || regionsSet.contains(tableName))) {
			   ArrayList<NonMappedField> list = new ArrayList<>();
			   MailMergeDataSource<NonMappedField> ds = new MailMergeDataSource<>(tableName, list);
			   // We only need to add the first field for the handler to be called for the fields in the region.
			   list.add(new NonMappedField("FirstField"));

			   dataSet.addDataSource(tableName, ds);
			}
			tableName = null;
		 }
	  }
	  return dataSet;
   }

   protected Document getDocument()
	   throws Exception {
	  if (doc == null) {
		 doc = createDocument();
	  }
	  return doc;
   }

   private void saveDocumentToFile(Document doc, String name)
	   throws Exception {
	  if (doc != null) {
		 String dataDir = rootDirectory.getAbsolutePath();
		 doc.save(dataDir + File.separator + name);
	  }
   }

   private Map<ReportFormat, File> saveDocument(Document doc, List<ReportFormat> formats, String filename) {
	  if (doc == null ||
		  formats == null) {
		 return null;
	  }

	  Map<ReportFormat, File> result = new HashMap<>();
	  resultFiles = new HashMap<>();

	  for (ReportFormat format : formats) {
		 try {
			File tempFile = File.createTempFile(filename + "_", format.getFileExtension(true), rootDirectory);
			String absolutePath = tempFile.getAbsolutePath();
			doc.save(absolutePath);

			result.put(format, tempFile);
			resultFiles.put(format, absolutePath);
		 } catch (Throwable e) {
			logger.error(String.format("Cannot save report in %s format", format.getFileExtension(true)), e);
		 }
	  }

	  return result;
   }

   private LinkedList<IfTag> getIfTags()
	   throws Exception {
	  if (ifTags == null) {
		 ifTags = IfTagProcessor.createIfTags(getDocument());
	  }
	  return ifTags;
   }
}
