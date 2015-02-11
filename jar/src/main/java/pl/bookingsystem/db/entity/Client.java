package pl.bookingsystem.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client implements Serializable , BaseEntity{

    @Column(name = "id")
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
    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "pesel", nullable = false, unique = true)
    private Long pesel;

    @Column(name = "nip")
    private Long nip;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    @Column(name = "pass", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private ClientAddress address;

    @Column(name = "register_date")
    private Date register_date;

    @Column(name = "update_date")
    private Date update_date;
    /*
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")*/
    @Transient
    private List<Reservation> reservations;

    /*  @ManyToMany(fetch = FetchType.LAZY)
      @JoinTable(name = "hotel_client", joinColumns = {
              @JoinColumn(name = "client_id", nullable = false, updatable = false)},
              inverseJoinColumns = {@JoinColumn(name = "hotel_id",
                      nullable = false, updatable = false)})*/
    @Transient
    private List<Hotel> hotels = new LinkedList<Hotel>();

    public Client(String first_name, String last_name, Long pesel, String email, String phone_number, String password, ClientAddress address, Date register_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.address = address;
        this.register_date = register_date;
    }

    public Client(String first_name, String last_name, Long pesel, String email, String phone_number, String password, ClientAddress address, Date register_date, Long nip) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.address = address;
        this.register_date = register_date;
        this.nip = nip;
    }

    public Client(String first_name, String last_name, Long pesel, Long nip, String email, String phone_number, String password, ClientAddress address, Date register_date, List<Reservation> reservations, List<Hotel> hotels) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.nip = nip;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.address = address;
        this.register_date = register_date;
        this.reservations = reservations;
        this.hotels = hotels;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

    public Long getNip() {
        return nip;
    }

    public void setNip(Long nip) {
        this.nip = nip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientAddress getAddress() {
        return address;
    }

    public void setAddress(ClientAddress address) {
        this.address = address;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return this.getFirst_name() + " " + this.getLast_name();
    }

}
