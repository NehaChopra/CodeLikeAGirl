package designPattern.bigDataTinyurl;

/*
 *  Approach : 
 *  1. Create a table with autogeneratedId, givenUrl and shortenedUrl
 *  2. for encoding - fetch the autogeneratedId and map it to shortenedUrl
 *  3. for decoding - fetch the shortenedUrl and map it to autogeneratedId
 *  4. Based on autogeneratedId, where autogeneratedId and shortenedUrl has 1 - 1 mapping, fetch the givenUrl
 *  
 *  
 *  Steps to solve the problem:
 *  1. create database urlShortner
 *  2. 
 */
class URLShortener {
	public static final String BASE_STRING = "9a8b7c6A5B4C3D2E1F0GdHeIfJgKhLijMkNlOmPnQoRpSqTrUsVtWuXvYwZx-y_z";
	public static final int BASE_LENGTH = BASE_STRING.length();
	
	public static void main(String []args) {
		/*
		 * Perform db activities
		 */
		String encodedString = URLShortener.encode(123456789);
		System.out.println(encodedString);
		System.out.println(URLShortener.decode(encodedString));
	}
	
	public static String encode(int autogeneratedId) {
		StringBuffer st = new StringBuffer();
		while(autogeneratedId > 0) {
			st.append(BASE_STRING.charAt(autogeneratedId % BASE_LENGTH));
			autogeneratedId = autogeneratedId / BASE_LENGTH;
		}
		
		return st.reverse().toString();
	}
	
	public static int decode(String encodedString) {
		int autogeneratedId = 0;
		for(int index=0; index<encodedString.length(); index++) {
			autogeneratedId =  (autogeneratedId * BASE_LENGTH ) +  BASE_STRING.indexOf(encodedString.charAt(index));
		}
		return autogeneratedId;
	}
}