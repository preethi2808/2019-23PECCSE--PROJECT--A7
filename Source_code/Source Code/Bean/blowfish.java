package Bean;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * This program demonstrates how to encrypt/decrypt 
 * input using the Blowfish
 * Cipher with the Java Cryptography.
 */
public class blowfish {
private static String key = "knowledgefactory";

    public static String encrypt(String password, String key) throws 
                  NoSuchAlgorithmException, NoSuchPaddingException,
                   InvalidKeyException, IllegalBlockSizeException, 
                    BadPaddingException, UnsupportedEncodingException {
        byte[] KeyData = key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, KS);
        String encryptedtext = Base64.getEncoder().
            encodeToString(cipher.doFinal(password.getBytes("UTF-8")));
        return encryptedtext;

    }

    public static String decrypt(String encryptedtext, String key) 
          throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, 
                        BadPaddingException {
        byte[] KeyData = key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        byte[] ecryptedtexttobytes = Base64.getDecoder().
                         decode(encryptedtext);
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, KS);
        byte[] decrypted = cipher.doFinal(ecryptedtexttobytes);
        String decryptedString = 
                   new String(decrypted, Charset.forName("UTF-8"));
        return decryptedString;

    }

    public static String enc(String data) throws Exception {
    	 
        String enc_output =encrypt(data, key);
        
        return enc_output;
    }
    public static String dec(String encrypteddata) throws Exception {
   	 
    	
       String dec_output = decrypt(encrypteddata, key);
       
       return dec_output;
   }
}