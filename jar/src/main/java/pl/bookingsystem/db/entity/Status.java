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


    public Status() {
    }

    public Status(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Status(String type, String description, Set<Reservation> reservations) {
        this.type = type;
        this.description = description;
        this.reservations = reservations;
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

}
