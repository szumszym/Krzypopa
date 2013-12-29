package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.PriceDAO;
import pl.bookingsystem.db.dao.impl.PriceDAOImpl;
import pl.bookingsystem.db.entity.Price;

import java.util.List;

/**
 * Created by Dwory on 28.12.13.
 */
@ParentPackage("json-default")
@Namespace("")
public class PriceAction extends ActionSupport {

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


    @Action(value = "price-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDBsmall() {
        try {
            PriceDAO priceManager = new PriceDAOImpl();
            List<Price> prices = priceManager.selectAll(Price.class);

            int size = prices.size();
            data = new String[size][];
            for (int j = 0; j < prices.size(); j++) {

                String[] tableS = new String[4];
                Price p = prices.get(j);
                tableS[0] = String.valueOf(p.getId());
                tableS[1] = String.valueOf(p.getRoomt_type());
                tableS[2] = String.valueOf(p.getPrersone_type());
                tableS[3] = String.valueOf(p.getValue());

                data[j] = tableS;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = new String[][]{new String[]{"ERROR!!!"}};
            return ERROR;
        }

    }



    @Action(value = "price-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String priceAdd() {
        try {

            System.out.println(dataFrom);
            PriceDAO priceManager = new PriceDAOImpl();
            JSONObject jsonObject = new JSONObject(dataFrom);
            String typeRoom = (String) jsonObject.get("type");
            String typePerson = (String) jsonObject.get("for");
            Integer value = Integer.parseInt((String) jsonObject.get("value"));
            Price price = new Price(typeRoom,typePerson,value);
            priceManager.save(price);
            return SUCCESS;

        } catch (Exception e) {

            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
            return ERROR;
        }

    }
}
