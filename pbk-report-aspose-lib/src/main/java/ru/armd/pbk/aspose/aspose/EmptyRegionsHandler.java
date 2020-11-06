package ru.armd.pbk.aspose.aspose;

import com.aspose.words.FieldMergingArgs;
import com.aspose.words.IFieldMergingCallback;
import com.aspose.words.ImageFieldMergingArgs;

public class EmptyRegionsHandler
	implements IFieldMergingCallback {

   private EmptyRegionsHandlerType type;

   /**
	* @param type A type of handler. Cannot be <code>null</code>.
	*/
   public EmptyRegionsHandler(EmptyRegionsHandlerType type) {
	  setType(type);
   }

   /**
	* Handler with default {@link EmptyRegionsHandlerType#REMOVE_TABLE} type.
	*/
   public EmptyRegionsHandler() {
	  this(EmptyRegionsHandlerType.REMOVE_TABLE);
   }

   /**
	* @param type A type of handler. Cannot be <code>null</code>. Default is {@link EmptyRegionsHandlerType#REMOVE_TABLE}
	*/
   public void setType(EmptyRegionsHandlerType type) {
	  if (type == null) {
		 throw new RuntimeException("A type of empty regions handler cannot be null");
	  }

	  this.type = type;
   }

   /**
	* Called for each field belonging to an unmerged region in the document.
	*/
   public void fieldMerging(FieldMergingArgs args)
	   throws Exception {
	  type.handleFieldMerging(args);
   }

   public void imageFieldMerging(ImageFieldMergingArgs args)
	   throws Exception {
	  type.handleImageFieldMerging(args);
   }
}
