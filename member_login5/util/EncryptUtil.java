package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class EncryptUtil {

    public static String encryptSHA512(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
            byte[] hashedBytes = md.digest(bytes);
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
