package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.*;
import pl.bookingsystem.db.dao.impl.*;
import pl.bookingsystem.db.entity.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.*;

@ParentPackage("json-default")
@Namespace("")
public class ReservationAction extends ActionSupport implements SessionAware {
    private static final Logger log = Logger.getLogger(ReservationAction.class);
    private String[][] data;
    private Map<String, Object> session;

    public String[][] getData() {

        return data;
    }

    public String getDataFrom() {
        return dataFrom;
    }

    private String dataFrom;

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }


    @Action(value = "reservation-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDB() {
        try {
            Hotel hotel = (Hotel) session.get("hotel");
            Boolean isAdmin = (Boolean) session.get("isAdmin");

            ReservationDAO reservationDAO = new ReservationDAOImpl();
            List<Reservation> reservations = null;

            User user = (User) session.get("user");
            Client client = (Client) session.get("client");
            if (user != null) {
                if (isAdmin) {
                    reservations = reservationDAO.getAllReservations();
                } else {
                    reservations = reservationDAO.getHotelReservations(hotel);
                }
            } else if (client != null) {
                reservations = reservationDAO.getClientReservations(client);
            }

            List<Reservation> distinctList = getDistinctListOf(reservations);

            int size = distinctList != null ? distinctList.size() : 0;
            if (size > 0) {
                data = new String[size][];

                for (int j = 0; j < size; j++) {
                    String[] reservation = new String[12];
                    Reservation r = distinctList.get(j);
                    String userEmail = r.getClient().getEmail();
                    List<Room> rooms = r.getRooms();
                    String roomNumbers = generateRoomNumbersString(rooms);
                    String hotelName;
                    if (isAdmin) {
                        if (r.getRooms().size() > 0) {
                            hotelName = r.getRooms().get(0).getHotel().getName();
                        } else {
                            hotelName = "-";
                        }
                    } else {
                        hotelName = hotel.getName(); //because reservation are from this hotel only
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date1 = sdf.format(r.getDate_from());
                    String date2 = sdf.format(r.getDate_to());
                    Status status = r.getStatus();

                    reservation[0] = String.valueOf(r.getId());
                    reservation[1] = r.getName();
                    reservation[2] = date1;
                    reservation[3] = date2;
                    reservation[4] = String.valueOf(r.getPerson_count());
                    reservation[5] = String.valueOf(status.getColor() + "&" + status.getType());
                    reservation[6] = String.valueOf(r.getEntry_date() != null ? r.getUpdate_date() : "-");
                    reservation[7] = String.valueOf(r.getUpdate_date() != null ? r.getUpdate_date() : "-");
                    reservation[8] = String.valueOf(r.getPrice());
                    reservation[9] = userEmail;
                    reservation[10] = hotelName;
                    reservation[11] = roomNumbers;

                    data[j] = reservation;
                }
            } else {
                data = null;
            }
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    private List<Reservation> getDistinctListOf(List<Reservation> reservations) {
        Long id = 0L;
        List<Reservation> distinctList = new LinkedList<Reservation>();
        for (Reservation reservation : reservations) {
            Long res_id = reservation.getId();
            if (!res_id.equals(id)) {
                distinctList.add(reservation);
                id = res_id;
            }
        }
        return distinctList;
    }


    @Action(value = "reservation-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String reservationAdd() {
        try {

            User currentUser = (User) session.get("user");
            Client currentClient = (Client) session.get("client");
            Hotel hotel = (Hotel) session.get("hotel");

            JSONObject jsonObject = new JSONObject(dataFrom);
            String name = jsonObject.getString("name");
            Integer person_count = Integer.parseInt(jsonObject.getString("person_count"));

            //DATES
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date_from = sdf.parse(jsonObject.getString("date_from"));
            Date date_to = sdf.parse(jsonObject.getString("date_to"));
//ROOMS
            JSONArray jsonRoomIdsArray = (JSONArray) jsonObject.get("room_ids");
            List<Long> room_ids = convertJSONArrayToArrayList(jsonRoomIdsArray);
            RoomDAO roomDAO = new RoomDAOImpl();
            List<Room> rooms = roomDAO.selectByIDs(Room.class, room_ids);

            //CHECK IF TERM IS AVADAIBLE
            ReservationDAO reservationDAO = new ReservationDAOImpl();
            for (Room room : rooms) {
                List<Reservation> reservations = reservationDAO.getAllReservationsFrom(room);
                for (Reservation res : reservations) {
                    Date dateFrom2 = res.getDate_from();
                    Date dateTo2 = res.getDate_to();
                    if (isOverlapping(date_from, date_to, dateFrom2, dateTo2)) {

                        String date1 = sdf.format(dateFrom2);
                        String date2 = sdf.format(dateTo2);
                        data = setMsg("overlapped", "'" + room.getName() + "' from hotel: '" + hotel.getName() + "' is already reserved between " + date1 + " and " + date2);
                        return ERROR;
                    }
                }
            }

//CLIENT AND STATUS
            Status status = null;
            Client client = null;
            if (currentUser != null) {
                Long statusId = Long.parseLong(jsonObject.getString("status_id"));
                StatusDAO statusDAO = new StatusDAOImpl();
                status = statusDAO.selectByID(Status.class, statusId);

                Long clientId = Long.parseLong(jsonObject.getString("client_id"));
                ClientDAO clientDAO = new ClientDAOImpl();
                client = clientDAO.selectByID(Client.class, clientId);
            } else if (currentClient != null) {
                client = currentClient;
                StatusDAO statusDAO = new StatusDAOImpl();

                List<Status> statuses = statusDAO.getStatuses(hotel.getId());
                status = statuses.get(0);
            }

//PRICE
            Double price = 0.0;
            for (Room room : rooms) {
                List<Addition> additions = room.getAdditions();
                for (Addition add : additions) {
                    price += add.getPrice();
                }
                price += room.getPrice();
            }
            int days = daysBetween(date_from, date_to);
            price *= days;

            //CREATE NEW RESERVATION
            Reservation reservation = new Reservation(name, date_from, date_to, person_count, client, status, rooms, price);
            reservationDAO.create(reservation);

            data = setMsg(SUCCESS);
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "reservation-delete", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String reservationDelete() {
        try {
            JSONObject jsonObject = new JSONObject(dataFrom);
            Long index = Long.parseLong(jsonObject.getString("index"));

            ReservationDAO reservationDAO = new ReservationDAOImpl();
            reservationDAO.deleteByID(index);
            data = setMsg(SUCCESS);
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }


    @Action(value = "reservation-update", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String reservationUpdate() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                JSONObject jsonObject = new JSONObject(dataFrom);
                Long index = jsonObject.getLong("index");
                String name = jsonObject.getString("name");

                Long statusId = Long.parseLong(jsonObject.getString("status_id"));
                StatusDAO statusDAO = new StatusDAOImpl();
                Status status = statusDAO.selectByID(Status.class, statusId);


//SET NEW PROPERTIES
                ReservationDAO reservationDAO = new ReservationDAOImpl();
                Reservation reservation = reservationDAO.selectByID(Reservation.class, index);

                reservation.setName(name);
                reservation.setStatus(status);

                reservationDAO.update(reservation);

//UPDATE SESSION
                updateHotelsInSession();
                session.put("edit", null);

                data = setMsg(SUCCESS);
                return SUCCESS;
            } else {
                data = setMsg("ERROR!!!", "You have no permission to execute this action!");
                return ERROR;
            }

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "reservation-edit", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String reservationEdit() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                JSONObject jsonObject = new JSONObject(dataFrom);
                Long index = Long.parseLong(jsonObject.getString("index"));

                ReservationDAO reservationDAO = new ReservationDAOImpl();
                Reservation reservation = reservationDAO.selectByID(Reservation.class, index);

                session.put("edit", reservation);

                data = setMsg(SUCCESS);
                return SUCCESS;
            } else {
                data = setMsg("ERROR!!!", "You have no permission to execute this action!");
                return ERROR;
            }

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    private void updateHotelsInSession() {
        Hotel hotel = (Hotel) session.get("hotel");
        if (hotel != null) {
            HotelDAO hotelDAO = new HotelDAOImpl();
            Hotel updatedHotel = hotelDAO.selectByID(hotel.getId());
            session.put("hotel", updatedHotel);

            List<Hotel> sessionHotels;
            User currentUser = (User) session.get("user");
            Boolean isAdmin = (Boolean) session.get("isAdmin");
            if (isAdmin) {
                sessionHotels = hotelDAO.selectAllHotels();
            } else {
                sessionHotels = hotelDAO.selectAllHotelsOfUser(currentUser.getId());
            }
            session.put("hotels", sessionHotels);
        }
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}



