package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.dao.impl.ClientDAOImpl;
import pl.bookingsystem.db.entity.Address;
import pl.bookingsystem.db.entity.Client;

import java.util.Date;
import java.util.List;

import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("")
public class ClientAction extends ActionSupport {
    private static final Logger log = Logger.getLogger(ClientAction.class);
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


    @Action(value = "client-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDB() {
        try {
            ClientDAO clientManager = new ClientDAOImpl();
            List<Client> clients = clientManager.selectAll(Client.class);

            int size = clients.size();
            data = new String[size][];
            for (int j = 0; j < clients.size(); j++) {
                String[] tableS = new String[7];
                Client c = clients.get(j);
                Address a = c.getAddress();
                tableS[0] = String.valueOf(c.getId());
                tableS[1] = String.valueOf(String.valueOf(c.getLast_name() + " " + c.getFirst_name()));
                tableS[2] = String.valueOf(c.getEmail());
                tableS[3] = String.valueOf(c.getPhone_number());
                tableS[4] = String.valueOf(a.getCity());
                if (a.getApartment_no() != null) {
                    tableS[5] = String.valueOf(a.getStreet()) + " " + String.valueOf(a.getBuilding_no()) + "/" + String.valueOf(a.getApartment_no());
                } else {
                    tableS[5] = String.valueOf(a.getStreet()) + " " + String.valueOf(a.getBuilding_no());
                }
                tableS[6] = String.valueOf(a.getCountry());

                data[j] = tableS;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "client-getData-small", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDBsmall() {
        try {
            ClientDAO clientManager = new ClientDAOImpl();
            List<Client> clients = clientManager.selectAll(Client.class);

            int size = clients.size();
            data = new String[size][];
            for (int j = 0; j < clients.size(); j++) {
                String[] tableS = new String[4];
                Client c = clients.get(j);

                tableS[0] = String.valueOf(c.getId());
                tableS[1] = String.valueOf(String.valueOf(c.getLast_name() + " " + c.getFirst_name()));
                tableS[2] = String.valueOf(c.getEmail());
                tableS[3] = String.valueOf(c.getPhone_number());


                data[j] = tableS;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = new String[][]{new String[]{"ERROR!!!"}};
            return ERROR;
        }

    }

    @Action(value = "client-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String clientAdd() {
        try {
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
            String apartment_no = jsonObject.getString("apartment_no");

            Address address = new Address(city, street, building_no, postcode, country);
            if (!apartment_no.isEmpty()) {
                address.setApartment_no(Integer.valueOf(apartment_no));
            }

            Date register_date = new Date();
            Client client = new Client(first_name, last_name, pesel, email, phone_number, password, address, register_date);
            String nip = jsonObject.getString(("nip"));
            if (!nip.isEmpty()) {
                client.setNip(Long.parseLong(nip));
            }

            ClientDAO clientManager = new ClientDAOImpl();
            clientManager.save(client);
            data = setMsg(SUCCESS);
            return SUCCESS;

        } catch (Exception e) {
            log.error("Client save error: ", e);
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }


}



