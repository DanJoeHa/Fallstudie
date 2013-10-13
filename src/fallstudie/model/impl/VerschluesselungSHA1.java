package fallstudie.model.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Dient zur SHA1 Verschlüsselung des Passworts
 * -> Geht schneller und bequemer als immer ResultSets vom MYSQL
 * 
 * @author Phil
 *
 */
public class VerschluesselungSHA1 {

	public static String getEncodedSha1Sum( String key ) throws NoSuchAlgorithmException
	{

		    MessageDigest md = MessageDigest.getInstance( "SHA1" );
		    md.update( key.getBytes() );
		    
		    return new BigInteger( 1, md.digest() ).toString(16);
	}
}
