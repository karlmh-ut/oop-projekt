package org.forum.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

// Lisasin id välja ning seost Thread tabeliga.
@Entity
public class Subforum {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany
    Set<Thread> threads = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Thread> getThreads() {
        return threads;
    }

    public void setThreads(Set<Thread> threads) {
        this.threads = threads;
    }
}
