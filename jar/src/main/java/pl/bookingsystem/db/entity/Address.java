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

public class Address implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building_no")
    private Integer building_no;

    @Column(name = "apartment_no")
    private Integer apartment_no;

    @Column(name = "postcode")
    private String poscode;

    @Column(name = "country")
    private String country;


    public Address(String city, String street, Integer building_no, String poscode, String country) {
        this.city = city;
        this.street = street;
        this.building_no = building_no;
        this.poscode = poscode;
        this.country = country;
    }

    public Address(String city, String street, Integer building_no, Integer apartment_no, String poscode, String country) {
        this.city = city;
        this.street = street;
        this.building_no = building_no;
        this.apartment_no = apartment_no;
        this.poscode = poscode;
        this.country = country;
    }

    public Address() {
    }

    public long getId() {
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

    public Integer getBuilding_no() {
        return building_no;
    }

    public void setBuilding_no(Integer building_no) {
        this.building_no = building_no;
    }

    public Integer getApartment_no() {
        return apartment_no;
    }

    public void setApartment_no(Integer apartment_no) {
        this.apartment_no = apartment_no;
    }

    public String getPoscode() {
        return poscode;
    }

    public void setPoscode(String poscode) {
        this.poscode = poscode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
