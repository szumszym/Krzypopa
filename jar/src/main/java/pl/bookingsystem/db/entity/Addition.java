package pl.bookingsystem.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "addition")

public class Addition implements Serializable {

    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "published")
    private Boolean published;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "additions")
    private Set<Room> rooms = new HashSet<Room>();

    public void addRoom(Room room) {
        room.getAdditions().add(this);
        this.rooms.add(room);
    }

    public Addition(String name, String description) {
        this.name = name;
        this.description = description;
        //this.publish =true;
    }


    public Addition(String name, String description, Set<Room> room) {
        this.name = name;
        this.description = description;
        this.rooms = room;
    }

    public Addition() {
    }

    public Long getId() {
        return id;
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

    public Set<Room> getRooms() { return rooms; }

    public void setRooms(Set<Room> rooms) { this.rooms = rooms; }

   /* public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }*/

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}

