package armd.core;


public class TextUtils {

   public static String getAddress(
	   String country, String region,
	   String address, String street, String bld1, String bld2, String corpus,
	   Integer flat, Integer porch, String domofon, Integer floor
   ) {
	  StringBuilder result = new StringBuilder(150);
	  if (!isEmpty(country)) {
		 if (result.length() > 0) {
			result.append(", ");
		 }
		 result.append(country);
	  }
	  if (!isEmpty(region)) {
		 if (result.length() > 0) {
			result.append(", ");
		 }
		 result.append(region);
	  }
	  if (!isEmpty(address)) {
		 if (result.length() > 0) {
			result.append(", ");
		 }
		 result.append(address);
	  }
	  if (!isEmpty(street)) {
		 if (result.length() > 0) {
			result.append(", ");
		 }
		 result.append(street);
	  }
	  if (!isEmpty(bld1)) {
		 if (result.length() > 0) {
			result.append(", ");
		 }
		 result.append("д. ").append(bld1);
	  }
	  if (!isEmpty(bld2)) {
		 result.append('/').append(bld2);
	  }
	  if (!isEmpty(corpus)) {
		 if (result.length() > 0) {
			result.append(", ");
		 }
		 result.append("к. ").append(corpus);
	  }
	  if (flat != null) {
		 if (result.length() > 0) {
			result.append(", ");
		 }
		 result.append("кв. ").append(flat);
	  }
	  if (porch != null) {
		 if (result.length() > 0) {
			result.append(", ");
		 }
		 result.append("под. ").append(porch);
	  }
	  if (!isEmpty(domofon)) {
		 if (result.length() > 0) {
			result.append(", ");
		 }
		 result.append("домофон ").append(domofon);
	  }
	  if (floor != null) {
		 if (result.length() > 0) {
			result.append(", ");
		 }
		 result.append("этаж ").append(floor);
	  }
	  return result.toString();
   }

   public static String getFullName(String firstName, String secondName, String lastName) {
	  StringBuilder result = new StringBuilder(100);
	  if (!isEmpty(lastName)) {
		 result.append(lastName).append(' ');
	  }
	  if (!isEmpty(firstName)) {
		 result.append(firstName).append(' ');
	  }
	  if (!isEmpty(secondName)) {
		 result.append(secondName);
	  }
	  return result.toString().trim();
   }

   public static String nvl(String value1, String value2) {
	  return (value1 == null) || isEmpty(value1) ? value2 : value1;
   }

   public static boolean isEmpty(String value) {
	  return (value == null) || value.trim().isEmpty();
   }

   public static String[] splitIntoTwoParts(String value, int maxLenOfFirstPart) {
	  String[] result = new String[] {"", ""};
	  if (value != null) {
		 if (maxLenOfFirstPart <= 0) {
			result[1] = value;
		 } else if (value.length() <= maxLenOfFirstPart) {
			result[0] = value;
		 } else {
			int lenOfFirstPart = maxLenOfFirstPart;
			while ((lenOfFirstPart <= value.length())
				&& (value.charAt(lenOfFirstPart) != ' ')) {
			   lenOfFirstPart = lenOfFirstPart + 1;
			}
			if (value.length() <= lenOfFirstPart) {
			   result[0] = value;
			} else {
			   result[0] = value.substring(0, lenOfFirstPart);
			   result[1] = value.substring(lenOfFirstPart);
			}
		 }
	  }
	  return result;
   }
}
