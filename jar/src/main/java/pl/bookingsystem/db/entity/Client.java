package pl.bookingsystem.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Client")
public class Client implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY/*GenerationType.AUTO*/)
    private Long id;

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
    private Address address;

    @Column(name = "register_date")
    private Date register_date;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Reservation> reservations;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    private Set<Hotel> hotels = new HashSet<Hotel>();

    public Client(String first_name, String last_name, Long pesel, String email, String phone_number, String password, Address address, Date register_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.address = address;
        this.register_date = register_date;
    }

    public Client(String first_name, String last_name, Long pesel, String email, String phone_number, String password, Address address, Date register_date, Long nip) {
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

    public Client(String first_name, String last_name, Long pesel, Long nip, String email, String phone_number, String password, Address address, Date register_date, Set<Reservation> reservations, Set<Hotel> hotels) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

 /*   public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Hotel> getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(Set<Hotel> id_hotel) {
        this.id_hotel = id_hotel;
    }

    public void setReservations(Reservation reservations) {
        this.reservations.add(reservations);
    }

    public void setId_hotel(Hotel id_hotel) {
        this.id_hotel.add(id_hotel);
    }
*/

}
