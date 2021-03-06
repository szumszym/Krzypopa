package pl.bookingsystem.webapp.guest.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.dao.impl.ClientDAOImpl;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.ClientAddress;
import pl.bookingsystem.db.entity.Reservation;

import java.util.Date;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("/")
public class ThirdStepAction extends ActionSupport implements SessionAware {

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

    @Action(value = "thirdStep", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String thirdStepClientAdd() {
        try {
            JSONObject jsonObject = new JSONObject(dataFrom);
            String first_name = jsonObject.getString("g_first_name");
            String last_name = jsonObject.getString("g_last_name");
            String email = jsonObject.getString("g_email");
            Long pesel = Long.parseLong(jsonObject.getString("g_pesel"));
            String phone_number = jsonObject.getString("g_phone_number");
            String password = jsonObject.getString("g_password");
            String city = jsonObject.getString("g_city");
            String street = jsonObject.getString("g_street");
            String building_no = jsonObject.getString("g_building_no");
            String postcode = jsonObject.getString("g_postcode");
            String country = jsonObject.getString("g_country");
            String apartment_no = jsonObject.getString("g_apartment_no");

            ClientAddress address = new ClientAddress(city, street, building_no, postcode, country);
            if (!apartment_no.isEmpty()) {
                address.setApartment_no(Integer.valueOf(apartment_no));
            }

            ClientDAO clientDAO = new ClientDAOImpl();
            if (clientDAO.checkIfEmailIsInDB(email)) {
                data = setMsg("EMAIL_EXISTS");
                return ERROR;
            }
            UserDAO userDAO = new UserDAOImpl();
            if (userDAO.checkIfEmailIsInDB(email)) {
                data = setMsg("EMAIL_EXISTS");
                return ERROR;
            }

            Date register_date = new Date();
            Client client = new Client(first_name, last_name, pesel, email, phone_number, password, address, register_date);
            String nip = jsonObject.getString(("g_nip"));
            if (!nip.isEmpty()) {
                client.setNip(Long.parseLong(nip));
            }

            clientDAO.create(client);


            ReservationDAO reservationDAO = new ReservationDAOImpl();
            Reservation reservation = (Reservation) session.get("reservation");

            reservation.setClient(client);
            reservationDAO.create(reservation);

//CLEANING SESSION
            session.put("available_rooms", null);

            data = setMsg("SHOW_MODAL");
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }


    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}