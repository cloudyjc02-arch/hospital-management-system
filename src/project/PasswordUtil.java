package project;

public class PasswordUtil {

    public static String hash(String plainPassword) {
        return plainPassword;
    }

    public static boolean verify(String plainPassword, String storedPassword) {
        return plainPassword.equals(storedPassword);
    }
}