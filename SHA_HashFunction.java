import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA_HashFunction {
	
	public static String getHash(String pass)	{
		String hashVal = null;
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			
	        digest.reset();
	        
	        digest.update(pass.getBytes("utf8"));
	        
	        hashVal = String.format("%040x", new BigInteger(1, digest.digest()));
	        
		}	catch (Exception e)	{
				e.printStackTrace();
			}
		return hashVal;
	}
}		// END OF CLASS