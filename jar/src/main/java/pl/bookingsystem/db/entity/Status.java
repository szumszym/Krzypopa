package pl.bookingsystem.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "status")
public class Status implements Serializable, BaseEntity {


    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    @Column(name = "status_type")
    private String type;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "status")
    private List<Reservation> reservations = new LinkedList<Reservation>();

    @Column (name = "color")
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Status() {
    }

    public Status(String type, String description, String color) {
        this.type = type;
        this.description = description;
        this.color = color;
    }

    public Status(String type, String description, String color, List<Reservation> reservations) {
        this.type = type;
        this.description = description;
        this.reservations = reservations;
        this.color = color;
    }

    public Status(String type, String description, String color, Hotel hotel) {
        this.type = type;
        this.description = description;
        this.color = color;
        this.hotel = hotel;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return this.getType();
    }

}
