package pl.bookingsystem.db.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")

public class Room implements Serializable {


    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "room_no")
    private Integer no_room;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "bed")
    private String bed;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private Set<Addition> additions;

    @ManyToMany(mappedBy = "rooms")
    private Set<Reservation> reservations = new HashSet<Reservation>();
    //TODO: add Price field   ?

    public Room() {
    }

    /**
     * Constructor without reservation, addidtions and hotel
     */
    public Room(Integer no_room, String name, String bed, Integer capacity) {
        this.no_room = no_room;
        this.name = name;
        this.bed = bed;
        this.capacity = capacity;
    }

    /**
     * Constructor without reservation and addidtions
     */
    public Room(Integer no_room, String name, String bed, Integer capacity, Hotel hotel) {
        this.no_room = no_room;
        this.name = name;
        this.bed = bed;
        this.capacity = capacity;
        this.hotel = hotel;
    }

    /**
     * Constructor with all fields
     */
    public Room(Integer no_room, String name, String bed, Integer capacity, Hotel hotel, Set<Addition> additions) {
        this.no_room = no_room;
        this.name = name;
        this.bed = bed;
        this.capacity = capacity;
        this.hotel = hotel;
        this.additions = additions;
    }


    public Integer getNo_room() {
        return no_room;
    }

    public void setNo_room(Integer no_room) {
        this.no_room = no_room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<Addition> getAdditions() {
        return additions;
    }

    public void setAdditions(Set<Addition> additions) {
        this.additions = additions;
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

    public void setId(Long id) {
        this.id = id;
    }
}

