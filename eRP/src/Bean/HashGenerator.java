package Bean;

import java.security.MessageDigest;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

/**
 * Demonstrates how to generate hash using Java
 */
public class HashGenerator {


    /**
     * Returns a hexadecimal encoded SHA256 hash for the input String.
     * @param data
     * @return
     */

    public static String getSHA256(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

                byte[] hash = digest.digest(data.getBytes("UTF-8"));

            return DatatypeConverter.printHexBinary(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}