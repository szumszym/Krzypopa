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
import pl.bookingsystem.webapp.action.Utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("")
public class UserAction extends ActionSupport implements SessionAware{
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
    public String getOwnerList() {
        try {
            UserDAO userManager = new UserDAOImpl();
            List<User> ownersList = userManager.selectAllOwners();

            int size = ownersList.size();
            data = new String[size][];
            for (int j = 0; j < ownersList.size(); j++) {
                String[] tableS = new String[4];
                User u = ownersList.get(j);
                tableS[0] = String.valueOf(u.getId());
                tableS[1] = String.valueOf(String.valueOf(u.getLast_name() + " " + u.getFirst_name() + " - " +u.getEmail()));
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

            Address address = new Address(city, street, building_no, postcode, country);
            String apartment_no = jsonObject.getString("apartment_no");
            if (!apartment_no.isEmpty()) {
                address.setApartment_no(Integer.valueOf(apartment_no));
            }

            User user = new User(first_name, last_name, pesel, email, phone_number, password, type, address);
            String nip = jsonObject.getString(("nip"));
            if (!nip.isEmpty()) {
                user.setNip(Long.parseLong(nip));
            }

            UserDAO userManager = new UserDAOImpl();
            userManager.save(user);

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

            Address address = new Address(city, street, building_no, postcode, country);
            String apartment_no = jsonObject.getString("apartment_no");
            if (!apartment_no.isEmpty()) {
                address.setApartment_no(Integer.valueOf(apartment_no));
            }

            User.Type type = User.Type.EMPLOYEE;
            User user = new User(first_name, last_name, pesel, email, phone_number, password, type, address);
            String nip = jsonObject.getString(("nip"));
            if (!nip.isEmpty()) {
                user.setNip(Long.parseLong(nip));
            }

//UPDATE HOTEL
            HotelDAO hotelManager = new HotelDAOImpl();
            Hotel hotel = (Hotel) session.get("hotel");
            hotel.getUsers().add(user);
            hotelManager.save(hotel);

//SAVE EMPLOYEE
            UserDAO userManager = new UserDAOImpl();
            userManager.save(user);

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
            UserDAO userManager = new UserDAOImpl();
            if(isAdmin){
                users = userManager.selectAll(User.class);
            } else if(isOwner){
                Hotel hotel = (Hotel) session.get("hotel");
                users = userManager.getEmployeesFromHotel(hotel.getId());
            }

            int size = users != null ? users.size() : 0;
            data = new String[size][];
            for (int j = 0; j < size; j++) {
                String[] tableS = new String[5];
                User u = users.get(j);
                tableS[0] = String.valueOf(u.getId());
                tableS[1] = String.valueOf(String.valueOf(u.getLast_name() + " " + u.getFirst_name()));
                tableS[2] = String.valueOf(u.getEmail());
                tableS[3] = String.valueOf(u.getPhone_number());
                tableS[4] = String.valueOf(u.getType());

                data[j] = tableS;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

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

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }
}
