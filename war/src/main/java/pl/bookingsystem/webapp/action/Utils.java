package pl.bookingsystem.webapp.action;

import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.User;

import java.util.Map;

/**
 * Author: rastek
 * Date: 10.01.14 @ 20:15
 */
public class Utils {

    public static String[][] setMsg(String message) {
        return setMsg(message, null);
    }

    public static String[][] setMsg(String message, String message2) {
        return new String[][]{new String[]{message, message2}};
    }
}
