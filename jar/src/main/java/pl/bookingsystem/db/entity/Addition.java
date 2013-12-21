package pl.bookingsystem.db.entity;

import javax.persistence.*;
import java.io.Serializable;


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

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Room room;

    public Addition(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Addition(String name, String description, Room room) {
        this.name = name;
        this.description = description;
        this.room = room;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

