package pl.bookingsystem.db.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "reservation")

public class Reservation implements Serializable {


    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_from")
    private Date date_from;

    @Column(name = "date_to")
    private Date date_to;

    @Column(name = "person_count")
    private Integer person_count;

    @Column(name = "entry_date")
    private Date entry_date;

    @Column(name = "update_date")
    private Date update_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = true)
    private Status status;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "room_reservation",
            joinColumns = {@JoinColumn(name = "reservation_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "room_id")})
    private Set<Room> rooms = new LinkedHashSet<Room>();

    @Column(name = "price")
    private Double price;

    public Reservation() {
    }

    public Reservation(String name, Date date_from, Date date_to, Integer person_count, Client client, Status status, List<Room> rooms, Double price) {
        this.name = name;
        this.date_from = date_from;
        this.date_to = date_to;
        this.person_count = person_count;
        this.client = client;
        this.status = status;
        this.rooms.addAll(rooms);
        this.price = price;
    }

    public Reservation(String name, Date date_from, Date date_to, Integer person_count, Status status, List<Room> rooms, Double price) {
        this.name = name;
        this.date_from = date_from;
        this.date_to = date_to;
        this.person_count = person_count;
        this.status = status;
        this.rooms.addAll(rooms);
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    public Integer getPerson_count() {
        return person_count;
    }

    public void setPerson_count(Integer person_count) {
        this.person_count = person_count;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date date_edit) {
        this.update_date = date_edit;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

