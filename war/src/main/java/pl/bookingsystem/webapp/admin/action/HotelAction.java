package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Address;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.User;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.util.Date;
import java.util.List;

@ParentPackage("json-default")
@Namespace("")
public class HotelAction extends ActionSupport {

    private String[][] data;

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
    public String dataFromDBsmall() {
        try {
            HotelDAO hotelManager = new HotelDAOImpl();
            List<Hotel> hotels = hotelManager.selectAll(Hotel.class);
            UserDAO userManager = new UserDAOImpl();
            int size = hotels.size();
            data = new String[size][];
            for (int j = 0; j < hotels.size(); j++) {

                String[] tableS = new String[7];
                User u=null;
                Hotel h = hotels.get(j);
                Address a = h.getAddress();
                tableS[0] = String.valueOf(h.getId());
                tableS[1] = String.valueOf(h.getName());
                tableS[2] = String.valueOf(a.getCity());
                tableS[3] = String.valueOf(a.getStreet()+" "+a.getBuilding_no());
                tableS[4] = String.valueOf(h.getPhone_number());
                tableS[5] = String.valueOf(h.getEmail());
                if(!(h.getUsers().isEmpty())){
                    u = h.getOwner();}


                if (u==null){
                    tableS[6] ="None";
                }else{
                    tableS[6] = String.valueOf(u.getFirst_name() +" "+u.getLast_name());
                }


                data[j] = tableS;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = new String[][]{new String[]{"ERROR!!!"}};
            return ERROR;
        }

    }


    public String jasonConvert(String serialize) {
        String conv = "{\"" + serialize + "\"}";
        conv = conv.replaceAll("=", "\":\"");
        conv = conv.replaceAll("&", "\",\"");
        System.out.println(conv);
        return conv;
    }


    @Action(value = "hotel-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String hotelAdd() {
        try {
            UserDAO userManager = new UserDAOImpl();

            System.out.println(dataFrom);
            JSONObject jsonObject = new JSONObject(dataFrom);
            String name = (String) jsonObject.get("name");
            String email = (String) jsonObject.get("email");
            String  phone_number = (String) jsonObject.get("phone_number");
            String  city = (String) jsonObject.get("city");
            String  street = (String) jsonObject.get("street");
            Integer building_no = Integer.parseInt((String) jsonObject.get("building_no"));
            String  postcode = (String) jsonObject.get("postcode");
            String  country = (String) jsonObject.get("country");
            String  description = (String) jsonObject.get("description");

           // User user = userManager.getCurrentUser((String) jsonObject.get("owner"));

            Address address = new Address (city, street, building_no, postcode, country);
            User user = new User("TEmp", "Hebel", 80030801234L, "b@b.pl", "888011166", "user", User.Type.OWNER, new Address("Wroclaw", "Wroclawska", 7, 2, "32-234", "Polska"));


            Hotel hotel = new Hotel (name, description, phone_number, email, address,user);

            //user.setHotel(hotel);
            //userManager.save(user);
            //hotel.setOwner(user);
            //userManager.delete(user);

            HotelDAO hotelManager = new HotelDAOImpl();
            hotelManager.save(hotel);
            return SUCCESS;

        } catch (Exception e) {

            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
            return ERROR;
        }

    }

}
