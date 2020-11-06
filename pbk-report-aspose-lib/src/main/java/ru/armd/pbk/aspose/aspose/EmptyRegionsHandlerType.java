package ru.armd.pbk.aspose.aspose;

import com.aspose.words.FieldMergingArgs;
import com.aspose.words.ImageFieldMergingArgs;
import com.aspose.words.NodeType;
import com.aspose.words.Row;
import com.aspose.words.Table;

/**
 * The type of unmerged fields handler.
 */
public enum EmptyRegionsHandlerType {

   /**
	* Removes a table containing unmerged fields from the document.
	*/
   REMOVE_TABLE {
	  public void handleFieldMerging(FieldMergingArgs args) {
		 Table table = (Table) args.getField().getStart().getAncestor(NodeType.TABLE);
		 if (table == null) {
			return;
		 }

		 // Check if the table has been removed from the document already.
		 if (table.getParentNode() != null) {
			table.remove();
		 }
	  }
   },

   /**
	* Removes a row containing unmerged fields from the table.
	*/
   REMOVE_ROW {
	  public void handleFieldMerging(FieldMergingArgs args) {
		 Row row = (Row) args.getField().getStart().getAncestor(NodeType.ROW);
		 if (row == null) {
			return;
		 }

		 // Check if the row has been removed from the table already.
		 if (row.getParentNode() != null) {
			row.remove();
		 }
	  }
   };

   public void handleFieldMerging(FieldMergingArgs args) {
	  // Default is do nothing.
   }

   public void handleImageFieldMerging(ImageFieldMergingArgs args) {
	  // Default if do nothing.
   }
}
