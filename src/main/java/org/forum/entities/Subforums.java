package org.forum.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

// Lisasin id välja ning seost Thread tabeliga.
@Entity
public class Subforums {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany
    Set<Threads> threads = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Threads> getThreads() {
        return threads;
    }

    public void setThreads(Set<Threads> threads) {
        this.threads = threads;
    }
}
