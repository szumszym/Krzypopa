package pl.bookingsystem.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")

public class User implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "pesel")
    private Long pesel;

    @Column(name = "nip")
    private Long nip;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "pass")
    private String password;

    public enum Type {
        ADMIN, OWNER, EMPLOYEE
    }

    @Column(name = "user_type", columnDefinition = "enum('ADMIN','OWNER','EMPLOYEE')")
    @Enumerated(EnumType.STRING)
    private Type type;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Hotel> hotels = new HashSet<Hotel>();

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


    public User() {
    }

    public User(String first_name, String last_name, Long pesel, String email, String phone_number, String password, Type type, Address address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.type = type;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getType() {
        return type.name();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
