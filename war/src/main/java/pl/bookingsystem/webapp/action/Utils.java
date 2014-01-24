package pl.bookingsystem.webapp.action;

import org.json.JSONArray;
import pl.bookingsystem.db.entity.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return !start1.after(end2) && !start2.after(end1);
    }

    public static List<Long> convertJSONArrayToArrayList(JSONArray jsonArray) {
        List<Long> list = new ArrayList<Long>();
        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                list.add(jsonArray.getLong(i));
            }
        }
        return list;
    }

    public static String generateRoomNumbersString(List<Room> rooms) {
        if (rooms.size() > 0) {
            String roomNumbers = "";
            for (Room room : rooms) {
                roomNumbers += room.getNo_room() + ", ";
            }
            roomNumbers = roomNumbers.substring(0, roomNumbers.length() - 2);
            return roomNumbers;
        } else {
            return "-";
        }
    }
}
