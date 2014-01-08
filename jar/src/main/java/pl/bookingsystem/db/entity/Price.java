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
    private String room_type;

    @Column(name = "person_type")
    private String person_type;

    @Column(name = "value")
    private Double value;

    public Price() {
    }

    public Long getId() {
        return id;
    }

    public Price(String room_type, String person_type, Double value) {
        this.room_type = room_type;
        this.person_type = person_type;
        this.value = value;

    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getPerson_type() {
        return person_type;
    }

    public void setPerson_type(String person_type) {
        this.person_type = person_type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


}
