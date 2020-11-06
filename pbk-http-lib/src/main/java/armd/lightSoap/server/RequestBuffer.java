package armd.lightSoap.server;

import org.mozilla.universalchardet.UniversalDetector;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


public class RequestBuffer
	extends InputStream {

   private static final int requestMaxLen = 10 * 1024 * 1024;  //TODO config

   private HttpServletRequest request = null;

   private String bodyContent = null;

   private byte[] byteBuffer = null;
   private String content = null;
   private ByteArrayInputStream stream = null;

   private Charset resolveCharset() {
	  Charset charset = null;
	  try {
		 charset = Charset.forName(request.getCharacterEncoding());
	  } catch (Exception ex) {

		 UniversalDetector detector = new UniversalDetector(null);
		 detector.handleData(byteBuffer, 0, byteBuffer.length);
		 String encoding = detector.getDetectedCharset();
		 if ((encoding != null) && (!encoding.isEmpty())) {
			charset = Charset.forName(encoding);
		 } else {
			charset = Charset.forName("UTF-8");
		 }
	  }
	  return charset;
   }

   public RequestBuffer(HttpServletRequest request)
	   throws IOException {

	  this.request = request;

	  int len = 0;
	  int totalLength = 0;
	  byte[] buffer = new byte[2048];
	  InputStream inputStream = request.getInputStream();

	  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	  while ((len = inputStream.read(buffer, 0, buffer.length)) > 0) {
		 outputStream.write(buffer, 0, len);
		 totalLength += len;

		 if (totalLength > requestMaxLen) {
			throw new IOException("Too big request"); //TODO typed exception
		 }
	  }

	  this.byteBuffer = outputStream.toByteArray();
	  this.content = new String(byteBuffer, resolveCharset());
	  this.stream = new ByteArrayInputStream(byteBuffer);
   }


   public HttpServletRequest getRequest() {
	  return this.request;
   }

   @Override
   public int read()
	   throws IOException {
	  return stream.read();
   }

   @Override
   public int read(byte[] b)
	   throws IOException {
	  return stream.read(b);
   }

   @Override
   public int read(byte[] b, int off, int len)
	   throws IOException {
	  return stream.read(b, off, len);
   }

   @Override
   public long skip(long n)
	   throws IOException {
	  return stream.skip(n);
   }

   @Override
   public int available()
	   throws IOException {
	  return stream.available();
   }

   @Override
   public void close()
	   throws IOException {
	  stream.close();
   }

   @Override
   public synchronized void mark(int readlimit) {
	  stream.mark(readlimit);
   }

   @Override
   public synchronized void reset()
	   throws IOException {
	  stream.reset();
   }

   @Override
   public boolean markSupported() {
	  return stream.markSupported();
   }

   public String getContent() {
	  return content;
   }

   public String getBodyContent() {
	  return bodyContent;
   }

   public void setBodyContent(String bodyContent) {
	  this.bodyContent = bodyContent;
   }
}
