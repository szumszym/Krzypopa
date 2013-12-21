package pl.bookingsystem.db.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: rastek
 * Date: 11.12.13
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "price")

public class Price implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "room_type")
    private String roomt_type;

    @Column(name = "person_type")
    private String prersone_type;

    @Column(name = "value")
    private Integer value;

    public Price() {
    }

    public Long getId() {
        return id;
    }

    public Price(String roomt_type, String prersone_type, Integer value) {
        this.roomt_type = roomt_type;
        this.prersone_type = prersone_type;
        this.value = value;

    }

    public String getRoomt_type() {
        return roomt_type;
    }

    public void setRoomt_type(String roomt_type) {
        this.roomt_type = roomt_type;
    }

    public String getPrersone_type() {
        return prersone_type;
    }

    public void setPrersone_type(String prersone_type) {
        this.prersone_type = prersone_type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
