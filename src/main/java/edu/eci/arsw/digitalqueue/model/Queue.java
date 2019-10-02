package edu.eci.arsw.digitalqueue.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Queue {

    private @Id @GeneratedValue Long id;
    private String name;
    @OneToMany(mappedBy = "queue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turn> turns;

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
}
