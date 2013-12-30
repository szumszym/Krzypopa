package pl.bookingsystem.webapp.admin.action;

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
                tableS[1] = String.valueOf(String.valueOf(c.getLast_name()+" "+c.getFirst_name()));
                tableS[2] = String.valueOf(c.getEmail());
                tableS[3] = String.valueOf(c.getPhone_number());
                tableS[4] = String.valueOf(a.getCity());
                if(a.getApartment_no()!=null){
                    tableS[5] = String.valueOf(a.getStreet())+" "+String.valueOf(a.getBuilding_no())+"/"+String.valueOf(a.getApartment_no());
                }else{
                    tableS[5] = String.valueOf(a.getStreet())+" "+String.valueOf(a.getBuilding_no());
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
                tableS[1] = String.valueOf(String.valueOf(c.getLast_name()+" "+c.getFirst_name()));
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
            String last_name = (String) jsonObject.get("last_name");
            String email = (String) jsonObject.get("email");
            Long pesel = Long.parseLong((String) jsonObject.get("pesel"));
            String  phone_number = (String) jsonObject.get("phone_number");
            String  password = (String) jsonObject.get("password");
            String  city = (String) jsonObject.get("city");
            String  street = (String) jsonObject.get("street");
            Integer building_no = Integer.parseInt((String) jsonObject.get("building_no"));
            String  postcode = (String) jsonObject.get("postcode");
            String  country = (String) jsonObject.get("country");

            Address address = new Address (city, street, building_no, postcode, country);

            if (((String) jsonObject.get("apartment_no")).isEmpty()) {
                System.out.println("Apartment_NO : NULL");

            } else {
                System.out.println("Apartment_NO : Kurcze1");
                address.setApartment_no(Integer.parseInt((String) jsonObject.get(("apartment_no"))));

            }

            Client client = new Client (first_name,last_name, pesel, email, phone_number, password, address, register_date);

            if (((String) jsonObject.get("nip")).isEmpty()) {
                System.out.println("nip : NULL");
            } else {
                System.out.println("nip : Kurcze");
                client.setNip(Long.parseLong((String) jsonObject.get(("nip"))));

            }


            ClientDAO clientManager = new ClientDAOImpl();
            clientManager.save(client);
            return SUCCESS;

        } catch (Exception e) {
            log.error("Client save error: ", e);
            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
            return ERROR;
        }

    }



}



