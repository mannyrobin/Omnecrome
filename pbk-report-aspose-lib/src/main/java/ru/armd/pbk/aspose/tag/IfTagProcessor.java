package ru.armd.pbk.aspose.tag;

import com.aspose.words.Bookmark;
import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Node;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Row;
import com.aspose.words.Run;
import ru.armd.pbk.aspose.expression.ExpressionContext;
import ru.armd.pbk.aspose.expression.ExpressionProvider;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;

/**
 * Обработчик условныз тэгов в шаблоне отчета.
 * <p>
 * Условный тэг задается в файле шаблона следующим образом:
 * #<if (условие) >#
 * блок - текст, таблицы и т.п.
 * #<end if>#
 * <p>
 * Условие - обычное выражение на языке Java. Примеры:
 * 1. #<if ("Изменение данных".equals(serviceName)) >#
 * 2. #<if (!"Снятие с учета".equals(serviceName) && !"Собственник".equals(declarant)) >#
 * 3. #<if (x > 0) && (y == z + 1) </if>) >#
 * <p>
 * Если условие не выполняется, то соотвествующий блок удаляется из отчета.
 * <p>
 * Допускаются вложенные тэги:
 * #<if (условие1) >#
 * текст, таблицы и т.п.
 * #<if (условие2) >#
 * текст, таблицы и т.п.
 * #<end if>#
 * текст, таблицы и т.п.
 * #<end if>#
 */
public class IfTagProcessor {

   private static final String TAG_IF_PREFIX = "#<if";
   private static final String TAG_IF_POSTFIX = ">#";

   private static final String TAG_END_IF = "#<end if>#";

   public static LinkedList<IfTag> createIfTags(Document doc)
	   throws Exception {
	  DocumentBuilder docBuilder = new DocumentBuilder(doc);
	  @SuppressWarnings("rawtypes")
	  NodeCollection nodes = doc.getChildNodes(NodeType.RUN, true);
	  LinkedList<IfTag> ifTags = new LinkedList<IfTag>();
	  LinkedList<IfTag> ifTagStack = new LinkedList<IfTag>();
	  int bookmarkId = 1;
	  for (int i = 0; i < nodes.getCount(); i++) {
		 Run node = (Run) nodes.get(i);
		 String nodeText = node.getText();
		 int fromIndex = 0;
		 boolean isNodeModified = true;
		 while (isNodeModified) {
			int ifTagStart = nodeText.indexOf(TAG_IF_PREFIX, fromIndex);
			int ifTagEnd = nodeText.indexOf(TAG_IF_POSTFIX, fromIndex);
			int endIfTagStart = nodeText.indexOf(TAG_END_IF, fromIndex);
			if ((0 <= ifTagStart) && (ifTagStart < ifTagEnd)
				&& ((endIfTagStart < 0) || (ifTagEnd < endIfTagStart))) {
			   String ifTagExpression = nodeText.substring(ifTagStart + TAG_IF_PREFIX.length(), ifTagEnd);
			   String ifTagText = TAG_IF_PREFIX + ifTagExpression + TAG_IF_POSTFIX;
			   node.getRange().replace(ifTagText, " ", true, false);
			   docBuilder.moveTo(node);
			   String bookmarkName = String.valueOf(bookmarkId);
			   docBuilder.startBookmark(bookmarkName);
			   IfTag ifTag = new IfTag(bookmarkName, ifTagExpression);
			   ifTags.addFirst(ifTag);
			   ifTagStack.push(ifTag);
			   bookmarkId = bookmarkId + 1;
			   fromIndex = ifTagEnd + TAG_IF_POSTFIX.length();
			   isNodeModified = true;
			} else if ((0 <= endIfTagStart) && !ifTagStack.isEmpty()) {
			   IfTag ifTag = ifTagStack.pop();
			   docBuilder.moveTo(node);
			   docBuilder.endBookmark(ifTag.getBookmarkName());
			   node.getRange().replace(TAG_END_IF, " ", true, false);
			   fromIndex = endIfTagStart + TAG_END_IF.length();
			   isNodeModified = true;
			} else {
			   isNodeModified = false;
			}
		 }
	  }
	  return ifTags;
   }

   public static void processIfTags(LinkedList<IfTag> ifTags, Properties properties, Document doc)
	   throws Exception {
	  ExpressionContext context = ExpressionProvider.createExpressionContext(properties);
	  BookmarkCollection bookmarks = doc.getRange().getBookmarks();
	  for (IfTag ifTag : ifTags) {
		 Bookmark bookmark = bookmarks.get(ifTag.getBookmarkName());
		 try {
			if (!ifTag.evaluateExpression(context)) {
			   bookmark.setText("");
			}
		 } catch (Exception ignore) {
		 }
	  }
   }

   public static void removeEmptyRowsWithIfTags(LinkedList<IfTag> ifTags, Document doc) {
	  DocumentBuilder docBuilder = new DocumentBuilder(doc);
	  Set<Row> emptyRows = new HashSet<>(50);
	  for (IfTag ifTag : ifTags) {
		 try {
			if (docBuilder.moveToBookmark(ifTag.getBookmarkName(), false, false)) {
			   Node bookmarkNode = docBuilder.getCurrentNode();
			   Node containerNode = (bookmarkNode == null) ? null : bookmarkNode.getAncestor(NodeType.ROW);
			   if ((containerNode instanceof Row) && (containerNode.getText().trim().isEmpty())) {
				  emptyRows.add((Row) containerNode);
			   }
			}
		 } catch (Exception ignore) {
		 }
		 try {
			if (docBuilder.moveToBookmark(ifTag.getBookmarkName(), false, true)) {
			   Node bookmarkNode = docBuilder.getCurrentNode();
			   Node containerNode = (bookmarkNode == null) ? null : bookmarkNode.getAncestor(NodeType.ROW);
			   if ((containerNode instanceof Row) && (containerNode.getText().trim().isEmpty())) {
				  emptyRows.add((Row) containerNode);
			   }
			}
		 } catch (Exception ignore) {
		 }
	  }
	  for (Row row : emptyRows) {
		 try {
			row.remove();
		 } catch (Exception ignore) {
		 }
	  }
   }
}
