package pl.bookingsystem.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status implements Serializable {


    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status_type")
    private String type;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "status")
    private Set<Reservation> reservations = new HashSet<Reservation>();

    @Column (name = "color")
    private String color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Status() {
    }

    public Status(String type, String description, String color) {
        this.type = type;
        this.description = description;
        this.color = color;
    }

    public Status(String type, String description, String color, Set<Reservation> reservations) {
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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
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
