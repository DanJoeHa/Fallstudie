package fallstudie.model.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Phil
 * @date 15.10.2013
 * Klasse dient zur SHA1-Verschlüsselung des Passworts
 */
public class VerschluesselungSHA1 {
	/**
	 * @author Phil
	 * Methode verschlüsselt den mitgegeben String mit dem SHA1 Algorythmus.
	 * @param String key
	 * @return String verschlüsselterKey
	 * @throws NoSuchAlgorithmException
	 */
	public static String getEncodedSha1Sum( String key ) throws NoSuchAlgorithmException
	{

		    MessageDigest md = MessageDigest.getInstance( "SHA1" );
		    md.update( key.getBytes() );
		    
		    return new BigInteger( 1, md.digest() ).toString(16);
	}
}
