package com.w2m.w2m_technical_test.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "spaceships")
@SequenceGenerator(name="spaceship_id_seq", initialValue=6, allocationSize=100)
public class SpaceShip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="spaceship_id_seq")
    private long id;

    @Column(name = "name")
    private String name;

    public SpaceShip() {
    }

    public SpaceShip(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SpaceShip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
