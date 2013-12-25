package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.impl.ClientDAOImpl;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.entity.Address;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.Reservation;

import java.util.Date;
import java.util.List;

@ParentPackage("json-default")
@Namespace("")
public class ClientAction extends ActionSupport {

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
                tableS[1] = String.valueOf(String.valueOf(c.getLast_name()+" "+c.getFirst_name()));
                tableS[2] = String.valueOf(c.getEmail());
                tableS[3] = String.valueOf(c.getPhone_number());
                tableS[4] = String.valueOf(a.getCity());
                if(a.getApartment_no()!=null){
                    tableS[5] = String.valueOf(a.getStreet())+" "+String.valueOf(a.getBuilding_no())+"/"+String.valueOf(a.getApartment_no());
                }else{
                    tableS[5] = String.valueOf(a.getStreet())+String.valueOf(a.getBuilding_no());
                }
                tableS[6] = String.valueOf(a.getCountry());

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


    @Action(value = "client-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String clientAdd() {
        try {
            Date register_date = new Date();
            System.out.println(dataFrom);
            JSONObject jsonObject = new JSONObject(dataFrom);
            String first_name = (String) jsonObject.get("first_name");
            System.out.println("First_name: " + first_name );
            String last_name = (String) jsonObject.get("last_name");
            System.out.println("Last_name: " + last_name );
            String email = (String) jsonObject.get("email");
            System.out.println("Email: " + first_name );
            Long pesel = Long.parseLong((String) jsonObject.get("pesel"));
            System.out.println("Email: " + pesel );
            String  phone_number = (String) jsonObject.get("phone_number");
            String  password = (String) jsonObject.get("password");
            String  city = (String) jsonObject.get("city");
            String  street = (String) jsonObject.get("street");
            Integer building_no = Integer.parseInt((String) jsonObject.get("building_no"));
            String  postcode = (String) jsonObject.get("postcode");
            String  country = (String) jsonObject.get("country");

            Address address = new Address (city, street, building_no, postcode, country);
            // Address address = new Address ("Oświecim", "Ruska", 10, "32-600", "Poland");
            if(!((jsonObject.get("apartment_no")) == null)){
                address.setApartment_no(Integer.parseInt((String) jsonObject.get(("apartment_no"))));
            }else{
                System.out.println("Apartment_NO : NULL");
            }

            Client client = new Client (first_name,last_name, pesel, email, phone_number, password, address, register_date);

            System.out.println(data);

            ClientDAO clientManager = new ClientDAOImpl();
            clientManager.save(client);
            return SUCCESS;

        } catch (Exception e) {

            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
            return ERROR;
        }

    }



}



