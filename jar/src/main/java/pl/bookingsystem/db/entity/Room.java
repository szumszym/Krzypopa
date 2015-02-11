package pl.bookingsystem.db.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "room")

public class Room implements Serializable, BaseEntity {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "room_addition", joinColumns = {
            @JoinColumn(name = "room_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "addition_id",
                    nullable = false, updatable = false)})
    private List<Addition> additions = new LinkedList<Addition>();

    @ManyToMany(mappedBy = "rooms")
    private List<Reservation> reservations = new LinkedList<Reservation>();

    @Column(name = "price")
    private Double price;

    private Double priceAdditions;

    @Column(name = "published")
    private Boolean published;


    public Room() {
    }

    /**
     * Constructor without reservation and addidtions
     */

    public Room(Integer no_room, String name, String bed, Integer capacity, Hotel hotel, Double price, Double priceAdditions) {
        this.no_room = no_room;
        this.name = name;
        this.bed = bed;
        this.capacity = capacity;
        this.hotel = hotel;
        this.price = price;
        this.priceAdditions = priceAdditions;
    }

    /**
     * Constructor without reservation
     */
    public Room(Integer no_room, String name, String bed, Integer capacity, Hotel hotel, List<Addition> additions, Double price, Double priceAdditions) {
        this.no_room = no_room;
        this.name = name;
        this.bed = bed;
        this.capacity = capacity;
        this.hotel = hotel;
        this.additions = additions;
        this.price = price;
        this.priceAdditions = priceAdditions;
    }

    /**
     * Constructor with all fields
     */
    public Room(Integer no_room, String name, String bed, Integer capacity, Hotel hotel, List<Addition> additions, List<Reservation> reservations, Double price) {
        this.no_room = no_room;
        this.name = name;
        this.bed = bed;
        this.capacity = capacity;
        this.hotel = hotel;
        this.additions = additions;
        this.reservations = reservations;
        this.price = price;
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

    public List<Addition> getAdditions() {
        return additions;
    }

    public String getAdditions(int limit) {
        String lista = "-";

        int additionSize = this.additions.size();
        if (additionSize > limit) {
            additionSize = limit;
        }
        Addition[] addit = this.additions.toArray(new Addition[0]);
        if (additionSize > 0) {
            lista = "";
        }
        for (int i = 0; i < additionSize; i++) {
            lista = lista + addit[i].getName();
            if (!(i == additionSize - 1)) {
                lista = lista + ", ";
            }
        }
        if (limit < additionSize) {
            lista = lista + ", ...";
        }
        return lista;
    }

    public void setAdditions(List<Addition> additions) {
        this.additions = additions;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceAdditions() {
        return priceAdditions;
    }

    public void setPriceAdditions(Double priceAdditions) {
        this.priceAdditions = priceAdditions;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}

