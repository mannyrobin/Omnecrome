package armd.core;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 */
public class ByteUtils {

   public static byte[] longToBytes(long x) {
	  ByteBuffer buffer = ByteBuffer.allocate(8);
	  buffer.putLong(x);
	  return buffer.array();
   }

   public static long bytesToLong(byte[] bytes) {
	  ByteBuffer buffer = ByteBuffer.allocate(8);
	  buffer.put(bytes);
	  buffer.flip();
	  return buffer.getLong();
   }

   final protected static char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

   public static String bytesToHex(byte[] bytes) {
	  char[] hexChars = new char[bytes.length * 2];
	  int v;
	  for (int j = 0; j < bytes.length; j++) {
		 v = bytes[j] & 0xFF;  // (safely) convert byte to int
		 hexChars[j * 2] = hexArray[v >>> 4];
		 hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	  }
	  return new String(hexChars);
   }

   public static byte[] uuidToBytes(UUID uuid) {

	  ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	  bb.putLong(uuid.getMostSignificantBits());
	  bb.putLong(uuid.getLeastSignificantBits());
	  return bb.array();
   }

   public static UUID bytesToUuid(byte[] bytes) {
	  ByteBuffer buffer = ByteBuffer.allocate(8);
	  buffer.put(bytes, 0, 8);
	  buffer.flip();

	  long mostSignificantBits = buffer.getLong();

	  buffer.position(0);
	  buffer.put(bytes, 8, 8);
	  buffer.flip();
	  long leastSignificantBits = buffer.getLong();


	  return new UUID(mostSignificantBits, leastSignificantBits);
   }
}
