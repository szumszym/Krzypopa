package pl.bookingsystem.db.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: thx-
 * Date: 12.12.13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "address")

public class Address implements Serializable , BaseEntity{

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
    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building_no")
    private String building_no;

    @Column(name = "apartment_no")
    private Integer apartment_no;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "country")
    private String country;


    public Address(String city, String street, String building_no, String postcode, String country) {
        this.city = city;
        this.street = street;
        this.building_no = building_no;
        this.postcode = postcode;
        this.country = country;
    }

    public Address(String city, String street, String building_no, Integer apartment_no, String postcode, String country) {
        this.city = city;
        this.street = street;
        this.building_no = building_no;
        this.apartment_no = apartment_no;
        this.postcode = postcode;
        this.country = country;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding_no() {
        return building_no;
    }

    public void setBuilding_no(String building_no) {
        this.building_no = building_no;
    }

    public Integer getApartment_no() {
        return apartment_no;
    }

    public void setApartment_no(Integer apartment_no) {
        this.apartment_no = apartment_no;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String poscode) {
        this.postcode = poscode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
