package Bean;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;

/*
 * RSA Key Size: 2048
 * Cipher Type: RSA/ECB/OAEPWithSHA-1AndMGF1Padding
 */
public class RSAExample2 {
    public PrivateKey privateKey;
    public PublicKey publicKey;

    public RSAExample2() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    // convert String publickey to Key object
    public static Key loadPublicKey(String stored)
            throws GeneralSecurityException, IOException {
        byte[] data = Base64.getDecoder().decode((stored.getBytes()));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        return fact.generatePublic(spec);
    }

    // Encrypt using publickey
    public static String encryptMessage(String plainText, String publickey)
            throws Exception {
        Cipher cipher = Cipher.
                getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, loadPublicKey(publickey));
        return Base64.getEncoder().
              encodeToString(cipher.doFinal(plainText.getBytes()));
    }

    // Decrypt using privatekey
    public static String decryptMessage(String encryptedText,
            String privatekey) throws Exception {
        Cipher cipher = Cipher.
                   getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, loadPrivateKey(privatekey));
        return new String(cipher.
              doFinal(Base64.getDecoder().decode(encryptedText)));
    }

    // Convert String private key to privateKey object
    public static PrivateKey loadPrivateKey(String key64)
            throws GeneralSecurityException {
        byte[] clear = Base64.getDecoder().decode((key64.getBytes()));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PrivateKey priv = fact.generatePrivate(keySpec);
        Arrays.fill(clear, (byte) 0);
        return priv;
    }

    public static void main(String[] args) throws Exception {
        final String secretText = "www.knowledgefactory.net";
        RSAExample2 keyPairGenerator = new RSAExample2();
        // Generate private and public key
        String privateKey = Base64.getEncoder().
              encodeToString(keyPairGenerator.getPrivateKey().getEncoded());
        String publicKey = Base64.getEncoder().
               encodeToString(keyPairGenerator.getPublicKey().getEncoded());
        System.out.println("Private Key: " + privateKey);
        System.out.println("Public Key: " + publicKey);

        // Encrypt secret text using public key
        String encryptedValue = RSAExample2.
                encryptMessage(secretText, publicKey);
        System.out.println("Encrypted Value: " + encryptedValue);
        
        // Decrypt
        String decryptedText = RSAExample2.
              decryptMessage(encryptedValue, privateKey);
        System.out.println("Decrypted output: " + decryptedText);
    }
}