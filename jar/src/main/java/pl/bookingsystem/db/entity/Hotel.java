package pl.bookingsystem.db.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "hotel")
public class Hotel implements Serializable, BaseEntity {


    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY/*GenerationType.AUTO*/)
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
    @Column(name = "name")
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "hotel_client",
            joinColumns = @JoinColumn(name = "hotel_id")
    )
    @Column(name = "client_id")
    private List<Long> clientIds = new LinkedList<Long>();


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "hotels")
    private List<User> users = new LinkedList<User>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms = new LinkedList<Room>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Addition> additions = new LinkedList<Addition>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Status> statuses = new LinkedList<Status>();


    public Hotel(String name, String description, String phone_number, String email, Address address) {
        this.name = name;
        this.description = description;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
    }

    public Hotel(String name, String description, String phone_number, String email, Address address, List<User> users) {
        this.name = name;
        this.description = description;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.users = users;
    }

    public Hotel(String name, String description, String phone_number, String email, Address address, User user) {
        this.name = name;
        this.description = description;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.users.add(user);
    }


    public Hotel(String name, String phone_number, String email, Address address, List<Long> clients, List<User> users) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.clientIds = clients;
        this.users = users;
    }

    public Hotel(String name, String description, String phone_number, String email, Address address, List<Long> clients, List<User> users) {
        this.name = name;
        this.description = description;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.clientIds = clients;
        this.users = users;
    }

    public Hotel() {
    }

    public Hotel(String name, String phone_number, String email, Address address, List<Long> clients, List<User> users, List<Room> rooms) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.clientIds = clients;
        this.users = users;
        this.rooms = rooms;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Long> getClientIds() {
        return clientIds;
    }

    public void setClientIds(List<Long> clientIds) {
        this.clientIds = clientIds;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Addition> getAdditions() {
        return additions;
    }

    public void setAdditions(List<Addition> additions) {
        this.additions = additions;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
