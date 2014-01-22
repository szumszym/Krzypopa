package pl.bookingsystem.webapp.guest.action;

/**
 * Author: rastek
 * Date: 19.01.14 @ 12:13
 */

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.dao.impl.RoomDAOImpl;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.isOverlapping;
import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("")
public class FirstStepAction extends ActionSupport implements SessionAware {

    private String city;
    private String dateFrom;
    private String dateTo;

    private String[][] data;
    public String[][] getData() {

        return data;
    }

    private Map<String, Object> session;

    @Action(value = "avadaible-rooms", results = {
            @Result(name = "success", location = "/modules/guest/secondStep.jsp"),
            @Result(name = "norooms", type = "json"),

            @Result(name = "error", location = "/modules/guest/notFound.jsp")
    })
    public String firststep() throws ParseException {
        session.clear();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date_from = sdf.parse(dateFrom);
        Date date_to = sdf.parse(dateTo);


        HotelDAO hotelDAO = new HotelDAOImpl();
        RoomDAO roomDAO = new RoomDAOImpl();
        List rooms = roomDAO.getRoomsFromCity(city);

        ReservationDAO reservationDAO = new ReservationDAOImpl();

        List<String[]> arrayList = new ArrayList<String[]>();
        int size = rooms.size();
        for (int j = 0; j < size; j++) {
            Object[] roomObj = (Object[]) rooms.get(j);
            Room room = (Room) roomObj[0];
            Long hotelId = (Long) roomObj[1];
            boolean isAvadaible = true;

            List<Reservation> reservations = reservationDAO.getAllReservationsFrom(room);
            for (Reservation res : reservations) {
                Date dateFrom2 = res.getDate_from();
                Date dateTo2 = res.getDate_to();
                if (isOverlapping(date_from, date_to, dateFrom2, dateTo2)) {
                    isAvadaible = false;
                }
            }
            if (isAvadaible) {
                Room r = (Room) roomObj[0];
                String hotelname = (String) roomObj[2];

                String[] roomArray = new String[12];
                roomArray[0] = hotelname;
                roomArray[1] = String.valueOf(r.getNo_room());
                roomArray[2] = String.valueOf(r.getName());
                roomArray[3] = String.valueOf(r.getBed());
                roomArray[4] = String.valueOf(r.getCapacity());
                roomArray[5] = String.valueOf(r.getAdditions(3));
                String description = r.getDescription();
                roomArray[6] = String.valueOf(description != null ? description : "-");
                roomArray[7] = String.valueOf(r.getPrice());
                roomArray[8] = String.valueOf(r.getId());

                arrayList.add(roomArray);
            }
        }
        if (arrayList.size() == 0) {
            data = setMsg("NO_ROOMS");
            return "norooms";
        } else {
            String[][] dataArray = new String[arrayList.size()][];
            arrayList.toArray(dataArray);
            data = dataArray;
            session.put("avadaibleRooms", dataArray);
            return SUCCESS;
        }
    }

    @Action(value = "login", results = {@Result(name = "success", location = "/modules/login/login.jsp")})
    public String goToLogin() {
        return SUCCESS;
    }


    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}