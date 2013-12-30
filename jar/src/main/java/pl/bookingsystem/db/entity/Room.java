package pl.bookingsystem.db.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")

public class
        Room implements Serializable {


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

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "room_addition", joinColumns = {
            @JoinColumn(name = "room_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "addition_id",
                    nullable = false, updatable = false)})
    private Set<Addition> additions= new HashSet<Addition>();

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

    public Room(Integer no_room, String name, String bed, Integer capacity, String description, Hotel hotel) {
        this.no_room = no_room;
        this.name = name;
        this.bed = bed;
        this.capacity = capacity;
        this.description = description;
        this.hotel = hotel;
    }

    public Room(Integer no_room, String name, String bed, Integer capacity, String description, Hotel hotel, Set<Addition> additions) {
        this.no_room = no_room;
        this.name = name;
        this.bed = bed;
        this.capacity = capacity;
        this.description = description;
        this.hotel = hotel;
        this.additions = additions;
        this.reservations = reservations;
    }

    public Integer getNo_room() { return no_room; }

    public void setNo_room(Integer no_room) { this.no_room = no_room; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBed() { return bed; }

    public void setBed(String bed) { this.bed = bed; }

    public Integer getCapacity() { return capacity; }

    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Hotel getHotel() { return hotel; }

    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public Set<Addition> getAdditions() { return additions; }

    public String getAdditions(int limit){
        String lista = "None";

        int additionSize= this.additions.size();
        if(additionSize>limit){
            additionSize=limit;
        }
        Addition[] addit = this.additions.toArray(new Addition[0]);
        if(additionSize>0){
            lista="";
        }
        for(int i= 0 ; i < additionSize; i++){
            lista= lista+addit[i].getName();
            if(!(i==additionSize-1)){
                lista= lista+ ", ";
            }
        }
        if(limit<additionSize){
            lista=lista+", ...";
        }
        return lista;
    }

    public void setAdditions(Set<Addition> additions) { this.additions = additions; }

    public Set<Reservation> getReservations() { return reservations; }

    public void setReservations(Set<Reservation> reservations) { this.reservations = reservations; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

}

