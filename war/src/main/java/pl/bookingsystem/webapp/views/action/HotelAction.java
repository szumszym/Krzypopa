package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.LazyInitializationException;
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
    public String dataFromDB() {
        try {
            HotelDAO hotelManager = new HotelDAOImpl();
            List<Hotel> hotels = hotelManager.selectAllWithAddress();
            int size = hotels.size();
            data = new String[size][];
            for (int j = 0; j < hotels.size(); j++) {

                String[] tableS = new String[6];
                Hotel h = hotels.get(j);
                Address a = h.getAddress();
                tableS[0] = String.valueOf(h.getId());
                tableS[1] = String.valueOf(h.getName());
                tableS[2] = String.valueOf(a.getCity());
                tableS[3] = String.valueOf(a.getStreet() + " " + a.getBuilding_no());
                tableS[4] = String.valueOf(h.getPhone_number());
                tableS[5] = String.valueOf(h.getEmail());

                data[j] = tableS;
            }

            return SUCCESS;
        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "hotel-getOwnerList", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String getOwnerHotelList() {
        try {
            User user = (User) session.get("user");
            Long userId = user.getId();
            HotelDAO hotelManager = new HotelDAOImpl();
            List<Hotel> hotels = hotelManager.selectAllHotelsOfUser(userId);
            int size = hotels.size();
            data = new String[size][];
            for (int j = 0; j < hotels.size(); j++) {
                String[] tableS = new String[2];
                Hotel h = hotels.get(j);
                tableS[0] = String.valueOf(h.getId());
                tableS[1] = String.valueOf(h.getName());
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
            UserDAO userManager = new UserDAOImpl();

            JSONObject jsonObject = new JSONObject(dataFrom);
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String phone_number = jsonObject.getString("phone_number");
            String city = jsonObject.getString("city");
            String street = jsonObject.getString("street");
            Integer building_no = Integer.parseInt(jsonObject.getString("building_no"));
            String postcode = jsonObject.getString("postcode");
            String country = jsonObject.getString("country");
            String description = jsonObject.getString("description");

            // User user = userManager.getCurrentUser(jsonObject.getString("owner"));

            Address address = new Address(city, street, building_no, postcode, country);

            User user = userManager.selectByID(User.class, 12L);      //TODO: AAAAAAAAAAAA!

            Hotel hotel = new Hotel(name, description, phone_number, email, address, user);

            //user.setHotel(hotel);
            //userManager.save(user);
            //hotel.setOwner(user);
            //userManager.delete(user);

            HotelDAO hotelManager = new HotelDAOImpl();
            hotelManager.save(hotel);
            data = setMsg(SUCCESS);
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }
}
