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

    public enum USER_TYPE {
        ADMIN, OWNER, EMPLOYEE, CLIENT;

        @Override
        public String toString() {
            return this.name();
        }
    }

    public static USER_TYPE getCurrentUserType(Map<String, Object> session) {

        User user = (User) session.get("user");
        Client client = (Client) session.get("client");
        if (user != null) {
            User.Type userType = user.getType();
            if (userType.equals(User.Type.ADMIN)) {
                return USER_TYPE.ADMIN;
            } else if (userType.equals(User.Type.EMPLOYEE)) {
                return USER_TYPE.EMPLOYEE;
            } else if (userType.equals(User.Type.OWNER)) {
                return USER_TYPE.OWNER;
            }
        } else if (client != null) {
            return USER_TYPE.CLIENT;
        }
        return null;
    }

    public static boolean isAdmin(Map<String, Object> session) {
        Utils.USER_TYPE type = getCurrentUserType(session);
        return type.equals(USER_TYPE.ADMIN);
    }

    public static boolean isEmployee(Map<String, Object> session) {
        Utils.USER_TYPE type = getCurrentUserType(session);
        return type.equals(USER_TYPE.EMPLOYEE);
    }

    public static boolean isOwner(Map<String, Object> session) {
        Utils.USER_TYPE type = getCurrentUserType(session);
        return type.equals(USER_TYPE.OWNER);
    }

    public static boolean isClient(Map<String, Object> session) {
        Utils.USER_TYPE type = getCurrentUserType(session);
        return type.equals(USER_TYPE.CLIENT);
    }

    public static User getUser(Map<String, Object> session) {
        return isClient(session) ? null : (User) session.get("user");
    }

    public static Client getClient(Map<String, Object> session) {
        return isClient(session) ? (Client) session.get("client") : null;
    }

}
