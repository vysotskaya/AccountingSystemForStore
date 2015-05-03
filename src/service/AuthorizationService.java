package service;

/**
 * Created by User on 03.05.2015.
 */
public class AuthorizationService {
    private static String LOGIN = "olya";
    private static String PASSWORD = "1234";
    public static boolean checkAuthorize(String login, String password) {
        if (login.equals(LOGIN) && password.equals(PASSWORD)) {
            return true;
        }
        return false;
    }
}
