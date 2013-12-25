package pl.bookingsystem.db.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {


    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY/*GenerationType.AUTO*/)
    private Long id;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_client", joinColumns = {
            @JoinColumn(name = "hotel_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "client_id",
                    nullable = false, updatable = false)})
    private Set<Client> clients = new HashSet<Client>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_user", joinColumns = {
            @JoinColumn(name = "hotel_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id",
                    nullable = false, updatable = false)})
    private Set<User> users = new HashSet<User>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<Room> rooms = new HashSet<Room>();


    public void addRoom(Room room) {
        room.setHotel(this);
        this.rooms.add(room);
    }

    public Hotel(String name, String description, String phone_number, String email, Address address, Set<User> users) {
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



    public Hotel(String name, String phone_number, String email, Address address, Set<Client> clients, Set<User> users) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.clients = clients;
        this.users = users;
    }

    public Hotel(String name, String description, String phone_number, String email, Address address, Set<Client> clients, Set<User> users) {
        this.name = name;
        this.description = description;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.clients = clients;
        this.users = users;
    }

    public Hotel() {
    }

    public Hotel(String name, String phone_number, String email, Address address, Set<Client> clients, Set<User> users, HashSet<Room> rooms) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.clients = clients;
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

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
