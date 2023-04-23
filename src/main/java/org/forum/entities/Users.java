package org.forum.entities;

import jakarta.persistence.*;
import org.forum.internal.permissions.Permissions;

import java.io.IOException;
import java.util.*;


// Kasutajate tabelis on nüüd seos loodud/likeitud postitustega/threadidega. Muutsin mõned
// muutujate nimed (capsiga kirjutatakse tavaliselt konstandid, seega permissions on nüüd
// väikeste tähtedega. Muutsin mõned meetodite nimetused.
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String SESSION_TOKEN;
    @ElementCollection
    private Map<String, Boolean> permissions;

    @OneToMany
    private Set<Posts> likes = new HashSet<>();

    @OneToMany
    private Set<Posts> posts = new HashSet<>();

    @OneToMany
    private Set<Threads> threads = new HashSet<>();

    public String getSessionToken() {
        return SESSION_TOKEN;
    }

    public Map<String, Boolean> getPermissions() {
        return permissions;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSessionToken(String SESSION_TOKEN) {
        this.SESSION_TOKEN = SESSION_TOKEN;
    }

    public void setPermissions(String permissions) throws IOException {
        this.permissions = Permissions.setDefaultPermissions();
        if (!permissions.isBlank()) Permissions.setNonDefaultPermissions(this.permissions, permissions);
    }
}
