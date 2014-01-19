package pl.bookingsystem.webapp.action;

import java.util.Date;

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

    public static int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
