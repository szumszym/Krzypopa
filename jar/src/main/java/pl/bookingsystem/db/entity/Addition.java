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
        private Double pirce;

        @Column(name = "publish")
        private Boolean publish;



    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "additions")
    private Set<Room> rooms = new HashSet<Room>();

    public void addRoom(Room room) {
        room.getAdditions().add(this);
        this.rooms.add(room);
    }

    public Addition(String name, String description) {
        this.name = name;
        this.description = description;
        this.publish =true;
    }

    public Addition(String name, String description, boolean publish) {
        this.name = name;
        this.description = description;
        this.publish = publish;
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


    public Double getPirce() { return pirce; }

    public void setPirce(Double pirce) { this.pirce = pirce; }


    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

}

