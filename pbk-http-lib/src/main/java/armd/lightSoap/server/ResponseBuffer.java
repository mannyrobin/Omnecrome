package armd.lightSoap.server;


import armd.core.RefVal;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ResponseBuffer
	extends Writer {

   private StringWriter innerWriter = new StringWriter(4096);

   private Integer status = null;

   private boolean _hasContent = false;

   private Charset encoding = Charset.forName("utf-8");

   public boolean hasContent() {
	  return _hasContent;
   }

   public Charset getEncoding() {
	  return encoding;
   }

   public void setEncoding(Charset encoding) {
	  this.encoding = encoding;
   }

   protected ResponseBuffer() {

   }

   protected ResponseBuffer(Object lock) {

   }

   public Integer getStatus() {
	  return status;
   }

   public void setStatus(Integer status) {
	  this.status = status;
   }

   public void resetContent(String newContent) {
	  innerWriter = new StringWriter(4096);
	  if (newContent != null) {
		 _hasContent = true;
		 innerWriter.write(newContent);
	  }
   }

   @Override
   public void write(int c)
	   throws IOException {
	  _hasContent = true;
	  innerWriter.write(c);
   }

   @Override
   public void write(char[] cbuf)
	   throws IOException {
	  _hasContent = true;
	  innerWriter.write(cbuf);
   }

   @Override
   public void write(char[] cbuf, int off, int len)
	   throws IOException {
	  _hasContent = true;
	  innerWriter.write(cbuf, off, len);
   }

   @Override
   public void write(String str)
	   throws IOException {
	  _hasContent = true;
	  innerWriter.write(str);
   }

   @Override
   public void write(String str, int off, int len)
	   throws IOException {
	  _hasContent = true;
	  innerWriter.write(str, off, len);
   }

   @Override
   public Writer append(CharSequence csq)
	   throws IOException {
	  _hasContent = true;
	  return innerWriter.append(csq);
   }

   @Override
   public Writer append(CharSequence csq, int start, int end)
	   throws IOException {
	  _hasContent = true;
	  return innerWriter.append(csq, start, end);
   }

   @Override
   public Writer append(char c)
	   throws IOException {
	  _hasContent = true;
	  return innerWriter.append(c);
   }

   @Override
   public void flush()
	   throws IOException {
	  innerWriter.flush();
   }

   @Override
   public void close()
	   throws IOException {
	  innerWriter.close();
   }

   public String getContent() {
	  return innerWriter.toString();
   }

   public void write(HttpServletResponse response, RefVal<String> content)
	   throws IOException {
	  if (getStatus() != null) {
		 response.setStatus(getStatus());
	  }

	  if (hasContent()) {
		 content.set(getContent());

		 ByteBuffer buffer = encoding.encode(content.get());
		 ByteBufferBackedInputStream byteStream = new ByteBufferBackedInputStream(buffer);

		 response.setContentType("text/xml; charset=" + encoding.displayName());

		 OutputStream outputStream = response.getOutputStream();
		 byte[] array = new byte[2048];
		 int len = 0;
		 while ((len = byteStream.read(array, 0, array.length)) > 0) {
			outputStream.write(array, 0, len);
		 }
	  } else {
		 content.set(null);
	  }

   }

}
