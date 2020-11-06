package ru.armd.pbk.aspose.words;

import com.aspose.words.Cell;
import com.aspose.words.HeightRule;
import com.aspose.words.Node;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.Row;
import com.aspose.words.Run;
import com.aspose.words.Table;

public class TableUtils {

   public static Row getRow(Node node) {
	  Node row = node;
	  while (row != null && row.getNodeType() != NodeType.ROW) {
		 row = row.getParentNode();
	  }

	  return (Row) row;
   }

   public static Cell getCell(Node node) {
	  Node cell = node;
	  while (cell != null && cell.getNodeType() != NodeType.CELL) {
		 cell = cell.getParentNode();
	  }

	  return (Cell) cell;
   }

   public static Paragraph getParagraph(Node node) {
	  Node paragraph = node;
	  while (paragraph != null && paragraph.getNodeType() != NodeType.PARAGRAPH) {
		 paragraph = paragraph.getParentNode();
	  }

	  return (Paragraph) paragraph;
   }

   public static Run getRun(Node node) {
	  Node run = node;
	  while (run != null && run.getNodeType() != NodeType.RUN) {
		 if (run.getNodeType() != NodeType.PARAGRAPH) {
			run = null;
			break;
		 }
		 run = run.getParentNode();
	  }

	  return (Run) run;
   }

   public static Table getTable(Node node) {
	  Node table = node;
	  while (table != null && table.getNodeType() != NodeType.TABLE) {
		 table = table.getParentNode();
	  }

	  return (Table) table;
   }

   public static Node putCell(Table table, Node format, boolean newRow) {

	  Row row = null;
	  if (newRow) {
		 Row formatRow = null;
		 if (format != null) {
			formatRow = getRow(format);
		 }
		 if (formatRow != null) {
			row = (Row) formatRow.deepClone(false);
		 } else {
			row = new Row(table.getDocument());
		 }
		 table.appendChild(row);
		 row.getRowFormat().setAllowBreakAcrossPages(true);
		 row.getRowFormat().setHeightRule(HeightRule.AUTO);
	  } else {
		 row = table.getLastRow();
	  }

	  Cell formatCell = null;
	  Paragraph formatParagraph = null;
	  Run formatRun = null;
	  if (format != null) {
		 formatCell = getCell(format);
		 formatParagraph = getParagraph(format);
		 formatRun = getRun(format);
	  }

	  Cell cell = null;
	  if (formatCell != null) {
		 cell = (Cell) formatCell.deepClone(false);
	  } else {
		 cell = new Cell(table.getDocument());
	  }

	  Paragraph paragraph = null;
	  if (formatParagraph != null) {
		 paragraph = (Paragraph) formatParagraph.deepClone(false);
	  } else {
		 paragraph = new Paragraph(table.getDocument());
	  }

	  Run run = null;
	  if (formatRun != null) {
		 run = (Run) formatRun.deepClone(false);
		 run.setText("");
	  }

	  if (run != null) {
		 paragraph.appendChild(run);
	  }
	  cell.appendChild(paragraph);
	  row.appendChild(cell);

	  return run != null ? run : paragraph;
   }

   /**
	* Применяет формат к ячейке, в которой содержится переданный узел.
	*
	* @param nodeInCell Узел, находящийся в ячейке.
	* @param format     Узел для копирования формата.
	* @return Новая серия или параграф внутри ячейки с заданным форматом.
	*/
   public static Node applyFormatToCell(Node nodeInCell, Node format) {
	  if (nodeInCell == null || format == null) {
		 return null;
	  }

	  Cell cell = getCell(nodeInCell);
	  if (cell == null) {
		 return null;
	  }

	  Paragraph paragraph;
	  Paragraph formatParagraph = getParagraph(format);
	  if (formatParagraph != null) {
		 paragraph = (Paragraph) formatParagraph.deepClone(false);
	  } else {
		 paragraph = new Paragraph(cell.getDocument());
	  }

	  Run run = null;
	  Run formatRun = getRun(format);
	  if (formatRun != null) {
		 run = (Run) formatRun.deepClone(false);
		 run.setText("");
	  }

	  if (run != null) {
		 paragraph.appendChild(run);
	  }
	  cell.appendChild(paragraph);

	  return run != null ? run : paragraph;
   }
}
