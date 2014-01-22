package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Address;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.User;

import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("")
public class UserAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;

    private String[][] data;

    public String[][] getData() {

        return data;
    }

    private String dataFrom;

    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }


    @Action(value = "owner-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDBOwnerList() {
        try {
            UserDAO userDAO = new UserDAOImpl();
            List<User> ownersList = userDAO.selectAllOwners();

            int size = ownersList.size();
            data = new String[size][];
            for (int j = 0; j < ownersList.size(); j++) {
                String[] tableS = new String[4];
                User u = ownersList.get(j);
                tableS[0] = String.valueOf(u.getId());
                tableS[1] = String.valueOf(String.valueOf(u.getLast_name() + " " + u.getFirst_name() + " - " + u.getEmail()));
                tableS[2] = String.valueOf(u.getEmail());
                tableS[3] = String.valueOf(u.getPhone_number());

                data[j] = tableS;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "user-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String userAdd() {
        try {
            System.out.println(dataFrom);
            JSONObject jsonObject = new JSONObject(dataFrom);

            String first_name = jsonObject.getString("first_name");
            String last_name = jsonObject.getString("last_name");
            String email = jsonObject.getString("email");
            Long pesel = Long.parseLong(jsonObject.getString("pesel"));
            User.Type type = getUsertype(jsonObject.getString("type"));
            String phone_number = jsonObject.getString("phone_number");
            String password = jsonObject.getString("password");
            String city = jsonObject.getString("city");
            String street = jsonObject.getString("street");
            Integer building_no = Integer.parseInt(jsonObject.getString("building_no"));
            String postcode = jsonObject.getString("postcode");
            String country = jsonObject.getString("country");

//ADDRESS
            Address address = new Address(city, street, building_no, postcode, country);
            String apartment_no = jsonObject.getString("apartment_no");
            if (!apartment_no.isEmpty()) {
                address.setApartment_no(Integer.valueOf(apartment_no));
            }

//CREATE NEW USER
            User user = new User(first_name, last_name, pesel, email, phone_number, password, type, address);

//NIP
            String nip = jsonObject.getString(("nip"));
            if (!nip.isEmpty()) {
                user.setNip(Long.parseLong(nip));
            }

//SAVE USER
            UserDAO userDAO = new UserDAOImpl();
            userDAO.create(user);

//ADD HOTEL IF EMPLOYEE
            if (User.Type.EMPLOYEE.equals(type)) {
                Long hotelId = Long.valueOf(jsonObject.getString("hotel_id"));
                HotelDAO hotelDAO = new HotelDAOImpl();
                Hotel hotel = hotelDAO.selectByID(hotelId);
                addUserToHotel(user, hotel);
            }

            data = setMsg(SUCCESS);
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }


    @Action(value = "employee-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String employeeAdd() {
        try {
            System.out.println(dataFrom);
            JSONObject jsonObject = new JSONObject(dataFrom);

            String first_name = jsonObject.getString("first_name");
            String last_name = jsonObject.getString("last_name");
            String email = jsonObject.getString("email");
            Long pesel = Long.parseLong(jsonObject.getString("pesel"));
            String phone_number = jsonObject.getString("phone_number");
            String password = jsonObject.getString("password");
            String city = jsonObject.getString("city");
            String street = jsonObject.getString("street");
            Integer building_no = Integer.parseInt(jsonObject.getString("building_no"));
            String postcode = jsonObject.getString("postcode");
            String country = jsonObject.getString("country");

//ADDRESS
            Address address = new Address(city, street, building_no, postcode, country);
            String apartment_no = jsonObject.getString("apartment_no");
            if (!apartment_no.isEmpty()) {
                address.setApartment_no(Integer.valueOf(apartment_no));
            }

            User.Type type = User.Type.EMPLOYEE;
            User user = new User(first_name, last_name, pesel, email, phone_number, password, type, address);

//NIP
            String nip = jsonObject.getString(("nip"));
            if (!nip.isEmpty()) {
                user.setNip(Long.parseLong(nip));
            }

//SAVE EMPLOYEE
            UserDAO userDAO = new UserDAOImpl();
            userDAO.create(user);

//UPDATE HOTEL
            Hotel hotel = (Hotel) session.get("hotel");
            addUserToHotel(user, hotel);

//UPDATE SESSION
            updateSession(hotel);

            data = setMsg(SUCCESS);
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }


    @Action(value = "user-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDB() {
        try {
            Boolean isOwner = (Boolean) session.get("isOwner");
            Boolean isAdmin = (Boolean) session.get("isAdmin");
            List<User> users = null;
            UserDAO userDAO = new UserDAOImpl();
            if (isAdmin) {
                users = userDAO.selectAll(User.class);
            } else if (isOwner) {
                Hotel hotel = (Hotel) session.get("hotel");
                users = userDAO.getEmployeesFromHotel(hotel);
            }

            int size = users != null ? users.size() : 0;
            data = new String[size][];
            for (int j = 0; j < size; j++) {
                String[] tableS = new String[6];

                User u = users.get(j);

                tableS[0] = String.valueOf(u.getId());
                tableS[1] = String.valueOf(String.valueOf(u.getLast_name() + " " + u.getFirst_name()));
                tableS[2] = String.valueOf(u.getEmail());
                tableS[3] = String.valueOf(u.getPhone_number());
                tableS[4] = String.valueOf(u.getType());
                tableS[5] = String.valueOf(buildHotelsString(u));

                data[j] = tableS;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }


    @Action(value = "user-delete", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String userDelete() {
        try {
            JSONObject jsonObject = new JSONObject(dataFrom);
            Long index = Long.parseLong(jsonObject.getString("index"));

            UserDAO userDAO = new UserDAOImpl();
            userDAO.deleteByID(index);
            data = setMsg(SUCCESS);
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }


    private String buildHotelsString(User u) {
        String hotelsString = "";
        if (!u.getType().equals(User.Type.ADMIN)) {
            HotelDAO hotelDAO = new HotelDAOImpl();
            List<Hotel> hotels = hotelDAO.selectAllHotelsOfUser(u.getId());
            for (Hotel hotel : hotels) {
                hotelsString += hotel.getName() + ", ";
            }
            hotelsString = hotelsString.substring(0, hotelsString.length() - 1);

        } else {
            hotelsString = "-";
        }
        return hotelsString;
    }

    public User.Type getUsertype(String s) {
        if (s.equals("ADMIN")) {
            return User.Type.ADMIN;

        } else if (s.equals("EMPLOYEE")) {
            return User.Type.EMPLOYEE;

        } else if (s.equals("OWNER")) {
            return User.Type.OWNER;

        }
        return User.Type.EMPLOYEE;
    }

    private static void addUserToHotel(User user, Hotel hotel) {
        HotelDAO hotelDAO = new HotelDAOImpl();
        UserDAO userDAO = new UserDAOImpl();
        User _user = userDAO.selectWith(user.getId(), "hotels");
        Hotel _hotel = hotelDAO.selectWith(hotel.getId(), "users");

        _hotel.getUsers().add(_user);
        _user.getHotels().add(_hotel);

        hotelDAO.update(_hotel);
    }

    private void updateSession(Hotel hotel) {
        HotelDAO hotelDAO = new HotelDAOImpl();
        Hotel sessionHotel = hotelDAO.selectByID(hotel.getId());
        session.put("hotel", sessionHotel);
        User currentUser = (User) session.get("user");
        Boolean isAdmin = (Boolean) session.get("isAdmin");
        List<Hotel> sessionHotels;
        if(isAdmin){
            sessionHotels = hotelDAO.selectAllHotels();
        } else {
            sessionHotels = hotelDAO.selectAllHotelsOfUser(currentUser.getId());
        }
        session.put("hotels", sessionHotels);
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }
}
