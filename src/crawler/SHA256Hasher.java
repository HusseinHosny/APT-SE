package crawler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;
public class SHA256Hasher {
		public static String getURLHash(String s) throws NoSuchAlgorithmException{
		byte[] inputBytes=s.getBytes();
		String hashValue="";
		MessageDigest md=MessageDigest.getInstance("SHA-256");
		md.update(inputBytes);
		byte[] digestedBytes=md.digest();
		hashValue=DatatypeConverter.printHexBinary(digestedBytes).toUpperCase();
		return hashValue;
	}
}
