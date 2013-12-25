package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.dao.impl.ClientDAOImpl;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Address;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.User;

import java.util.Date;
import java.util.List;

@ParentPackage("json-default")
@Namespace("")
public class UserAction extends ActionSupport  {

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


    @Action(value = "user-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDB() {
        try {
            UserDAO userManager = new UserDAOImpl();
            List<User> users = userManager.selectAll(User.class);

            int size = users.size();
            data = new String[size][];
            for (int j = 0; j < users.size(); j++) {
                String[] tableS = new String[5];
                User u = users.get(j);
                Address a = u.getAddress();
                tableS[0] = String.valueOf(u.getId());
                tableS[1] = String.valueOf(String.valueOf(u.getLast_name()+" "+u.getFirst_name()));
                tableS[2] = String.valueOf(u.getEmail());
                tableS[3] = String.valueOf(u.getPhone_number());
                tableS[4] = String.valueOf(u.getType());


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


    @Action(value = "user-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String userAdd() {
        try {
            Date register_date = new Date();
            JSONObject jsonObject = new JSONObject(dataFrom);
            String first_name = (String) jsonObject.get("first_name");
            String last_name = (String) jsonObject.get("last_name");
            String email = (String) jsonObject.get("email");
            Long pesel = Long.parseLong((String) jsonObject.get("pesel"));
            User.Type type = (User.Type) jsonObject.get("type");
            String  phone_number = (String) jsonObject.get("phone_number");
            String  password = (String) jsonObject.get("password");
            String  city = (String) jsonObject.get("city");
            String  street = (String) jsonObject.get("street");
            Integer building_no = Integer.parseInt((String) jsonObject.get("building_no"));
            String  postcode = (String) jsonObject.get("postcode");
            String  country = (String) jsonObject.get("country");

            Address address = new Address (city, street, building_no, postcode, country);

            if(!((jsonObject.get("apartment_no")) == null)){
                address.setApartment_no(Integer.parseInt((String) jsonObject.get(("apartment_no"))));
            }else{
                System.out.println("Apartment_NO : NULL");
            }

            User user = new User (first_name,last_name, pesel, email, phone_number, password, type, address);


            System.out.println(data);

            UserDAO userManager = new UserDAOImpl();
            userManager.save(user);
            return SUCCESS;

        } catch (Exception e) {

            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
            return ERROR;
        }

    }
}
