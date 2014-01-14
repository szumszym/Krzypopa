package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Address;
import pl.bookingsystem.db.entity.User;

import java.util.Date;
import java.util.List;

import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("")
public class UserAction extends ActionSupport {

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

}
