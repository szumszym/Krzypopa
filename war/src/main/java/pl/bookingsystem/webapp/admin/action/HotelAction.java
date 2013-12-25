package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.entity.Address;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.User;

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

            int size = hotels.size();
            data = new String[size][];
            for (int j = 0; j < hotels.size(); j++) {
                System.out.println("COs1 " + hotels.size());
                String[] tableS = new String[7];
                System.out.println("COs2 " + j);


                Hotel h = hotels.get(j);
                System.out.println("COs3");
                Address a = h.getAddress();
                tableS[0] = String.valueOf(h.getId());
                System.out.println(tableS[0]);
                tableS[1] = String.valueOf(h.getName());
                System.out.println(tableS[1]);
                tableS[2] = String.valueOf(a.getCity());
                System.out.println(tableS[2]);
                tableS[3] = String.valueOf(a.getStreet()+" "+a.getBuilding_no());
                System.out.println(tableS[3]);
                tableS[4] = String.valueOf(h.getPhone_number());
                System.out.println(tableS[4]);
                tableS[5] = String.valueOf(h.getEmail());
                System.out.println(tableS[5]);
                //tableS[6] = "None";
                User u = hotelManager.getOwner(h.getId());
                System.out.println(u.getId()+ " "+ u.getFirst_name());
                if (u==null){
                    tableS[6] ="None";
                }else{
                    tableS[6] = String.valueOf(u.getFirst_name() +" "+u.getLast_name());
                }
                System.out.println(tableS[6]);
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
            //User user = new UserDAOImpl().getCurrentUser((String) jsonObject.get("owner"));
            Address address = new Address (city, street, building_no, postcode, country);
            //System.out.println(user.getId());
            User user = new User("TEmp", "Hebel", 80030801234L, "r@gmail.pl", "888011166", "user", User.Type.EMPLOYEE, new Address("Wroclaw", "Wroclawska", 7, 2, "32-234", "Polska"));


            Hotel hotel = new Hotel (name, description, phone_number, email, address, user);
            HotelDAO hotelManager = new HotelDAOImpl();
            hotelManager.save(hotel);
            return SUCCESS;

        } catch (Exception e) {

            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
            return ERROR;
        }

    }

}
