package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.*;
import pl.bookingsystem.db.dao.impl.*;
import pl.bookingsystem.db.entity.*;

import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("")
public class HotelAction extends ActionSupport implements SessionAware {

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


    @Action(value = "hotel-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String getHotels() {
        try {
            List<Hotel> hotels = (List<Hotel>) session.get("hotels");
            int size = hotels.size();
            data = new String[size][];
            for (int j = 0; j < hotels.size(); j++) {

                String[] tableS = new String[7];
                Hotel h = hotels.get(j);
                Address a = h.getAddress();
                tableS[0] = String.valueOf(h.getId());
                tableS[1] = String.valueOf(h.getName());
                tableS[2] = String.valueOf(a.getCity());
                tableS[3] = String.valueOf(a.getStreet() + " " + a.getBuilding_no());
                tableS[4] = String.valueOf(h.getPhone_number());
                tableS[5] = String.valueOf(h.getEmail());
                tableS[6] = String.valueOf(h.getDescription());

                data[j] = tableS;
            }

            return SUCCESS;
        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "hotel-getData-client", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String getHotelsForClient() {
        try {
            HotelDAO hotelDAO = new HotelDAOImpl();
            List<Hotel> hotels = hotelDAO.selectAllWithAddress();
            int size = hotels.size();
            data = new String[size][];
            for (int j = 0; j < hotels.size(); j++) {

                String[] tableS = new String[7];
                Hotel h = hotels.get(j);
                Address a = h.getAddress();
                tableS[0] = String.valueOf(h.getId());
                tableS[1] = String.valueOf(h.getName());
                tableS[2] = String.valueOf(a.getCity());
                tableS[3] = String.valueOf(a.getStreet() + " " + a.getBuilding_no());
                tableS[4] = String.valueOf(h.getPhone_number());
                tableS[5] = String.valueOf(h.getEmail());
                tableS[6] = String.valueOf(h.getDescription());

                data[j] = tableS;
            }

            return SUCCESS;
        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "hotel-getList-small", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String getHotelList() {
        try {
            List<Hotel> hotels = (List<Hotel>) session.get("hotels");
            int size = hotels.size();
            data = new String[size][];
            for (int j = 0; j < hotels.size(); j++) {

                String[] tableS = new String[2];
                Hotel h = hotels.get(j);
                Address a = h.getAddress();
                tableS[0] = String.valueOf(h.getId());
                tableS[1] = String.valueOf(h.getName()) + " - " + String.valueOf(a.getCity()) + " - " + String.valueOf(a.getStreet() + " " + a.getBuilding_no());

                data[j] = tableS;
            }
            return SUCCESS;
        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "hotel-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String hotelAdd() {
        try {
            JSONObject jsonObject = new JSONObject(dataFrom);

            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String phone_number = jsonObject.getString("phone_number");
            String description = jsonObject.getString("description");

//ADDRESS
            String city = jsonObject.getString("city");
            String street = jsonObject.getString("street");
            String building_no = jsonObject.getString("building_no");
            String postcode = jsonObject.getString("postcode");
            String country = jsonObject.getString("country");
            Address address = new Address(city, street, building_no, postcode, country);
            Long ownerId = null;
            try {
                ownerId = Long.valueOf(jsonObject.getString("owner_id"));
            } catch (JSONException e) {/*do nothing*/}
//USER
            User user = getUser(ownerId);

//CREATE HOTEL AND SAVE
            if (user != null) {
                Hotel hotel = new Hotel(name, description, phone_number, email, address, user);

                HotelDAO hotelDAO = new HotelDAOImpl();
                hotelDAO.create(hotel);

//UPDATE SESSION
                updateHotelsInSession();

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

    @Action(value = "hotel-delete", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String hotelDelete() {
        HotelDAO hotelDAO = new HotelDAOImpl();
        try {
            JSONObject jsonObject = new JSONObject(dataFrom);
            Long index = Long.parseLong(jsonObject.getString("index"));

            Hotel hotel = hotelDAO.selectByID(index);
            Hotel currentHotel = (Hotel) session.get("hotel");

            if (!index.equals(currentHotel.getId())) {
                StatusDAO statusDAO = new StatusDAOImpl();
                AdditionDAO additionDAO = new AdditionDAOImpl();
                UserDAO userDAO = new UserDAOImpl();
                RoomDAO roomDAO = new RoomDAOImpl();
                ReservationDAO reservationDAO = new ReservationDAOImpl();

                List<Status> hotelStatuses = statusDAO.getStatuses(hotel);
                List<Addition> hotelAdditions = additionDAO.getAdditions(hotel);

                List<User> hotelUsers = userDAO.getEmployeesFromHotel(hotel);
                List<Room> hotelRooms = roomDAO.getRooms(hotel);
                List<Reservation> roomsReservations = reservationDAO.getAllReservationsFrom(hotelRooms);


//DELETE STATUSES
                for (Status status : hotelStatuses) {
                    statusDAO.delete(status);
                }

//DELETE ADDITIONS
                for (Addition addition : hotelAdditions) {
                    additionDAO.delete(addition);
                }

//DELETE EMPLOYEE
                for (User user : hotelUsers) {
                    userDAO.delete(user);
                }

//DELETE ROOMS
                for (Room room : hotelRooms) {
                    roomDAO.delete(room);
                }

//DELETE RESERVATIONS
                for (Reservation reservation : roomsReservations) {
                    reservationDAO.delete(reservation);
                }

/*//DELETE CLIENTS
                for (Client client : hotelClients) {
                    clientDAO.delete(client);
                }*/


//DELETE HOTEL
                hotelDAO.delete(hotel);

                updateHotelsInSession();

                data = setMsg(SUCCESS);
                return SUCCESS;
            } else {
                data = setMsg("CURRENT_HOTEL");
                return ERROR;
            }

        } catch (Exception e) {

// STOP SESSION - ROLLBACK
            hotelDAO.stop(false);
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }


    @Action(value = "hotel-update", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String hotelUpdate() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {
                User user = (User) session.get("user");
                User.Type type = user.getType();

                JSONObject jsonObject = new JSONObject(dataFrom);
                Long index = jsonObject.getLong("index");

                String name = jsonObject.getString("name");
                String email = jsonObject.getString("email");
                String phone_number = jsonObject.getString("phone_number");
                String description = jsonObject.getString("description");

//ADDRESS
                String city = jsonObject.getString("city");
                String street = jsonObject.getString("street");
                String building_no = jsonObject.getString("building_no");
                String postcode = jsonObject.getString("postcode");
                String country = jsonObject.getString("country");
                Address address = new Address(city, street, building_no, postcode, country);


//SET NEW PROPERTIES
                HotelDAO hotelDAO = new HotelDAOImpl();
                Hotel hotel = hotelDAO.selectByID(Hotel.class, index);
                hotel.setName(name);
                hotel.setEmail(email);
                hotel.setPhone_number(phone_number);
                hotel.setDescription(description);
                hotel.setAddress(address);


                hotelDAO.update(hotel);

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

    @Action(value = "hotel-edit", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String hotelEdit() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                JSONObject jsonObject = new JSONObject(dataFrom);
                Long index = Long.parseLong(jsonObject.getString("index"));

                HotelDAO hotelDAO = new HotelDAOImpl();
                Hotel hotel = hotelDAO.selectByID(Hotel.class, index);

                session.put("edit", hotel);

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

    private User getUser(Long ownerId) {

        User user = null;
        Boolean isAdmin = (Boolean) session.get("isAdmin");
        Boolean isOwner = (Boolean) session.get("isOwner");

        if (isAdmin) {
            UserDAO userDAO = new UserDAOImpl();
            user = userDAO.selectByID(User.class, ownerId);
        } else if (isOwner) {
            user = (User) session.get("user");
        }
        return user;
    }

    private void updateHotelsInSession() {
        Hotel hotel = (Hotel) session.get("hotel");
        if (hotel != null) {
            HotelDAO hotelDAO = new HotelDAOImpl();

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
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }
}
