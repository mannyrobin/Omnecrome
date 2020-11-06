package ru.armd.pbk.aspose.aspose;

import com.aspose.words.Cell;
import com.aspose.words.FieldMergingArgs;
import com.aspose.words.IFieldMergingCallback;
import com.aspose.words.ImageFieldMergingArgs;
import com.aspose.words.NodeType;

import java.awt.Color;

public class ColoringRegionsHandler
	implements IFieldMergingCallback {

   public void fieldMerging(FieldMergingArgs args)
	   throws Exception {
	  Cell cell = (Cell) args.getField().getStart().getAncestor(NodeType.CELL);
	  String fieldCode = args.getField().getFieldCode();
	  if (cell != null && fieldCode.indexOf("colorif") != -1) {
		 int valIdx1 = fieldCode.indexOf("\u0014");
		 int valIdx2 = fieldCode.indexOf("\u0015");
		 if (valIdx1 != -1 && valIdx2 != -1 && valIdx2 > valIdx1 + 1) {
			String valToCompare = fieldCode.substring(valIdx1 + 1, valIdx2);
			String cellText = cell.getText();
			if (valToCompare.equals(getCleanCellText(cellText))) {
			   cell.getCellFormat().getShading().setBackgroundPatternColor(Color.lightGray);
			}
		 }
		 args.setText("");
	  }
   }

   /**
	* get text without field contents - simple realization
	*
	* @param fulltext
	* @return
	*/
   private String getCleanCellText(String fulltext) {
	  String text = "";
	  int idx1 = fulltext.indexOf("\u0013");
	  int idx2 = fulltext.indexOf("\u0007");
	  if (idx1 > 0 && idx2 > 0 && idx2 > idx1 + 1) {
		 text = fulltext.substring(0, idx1);
	  }
	  return text;
   }

   public void imageFieldMerging(ImageFieldMergingArgs args)
	   throws Exception {
	  // Do Nothing
   }
}
