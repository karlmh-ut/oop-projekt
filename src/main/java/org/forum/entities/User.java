package org.forum.entities;

import jakarta.persistence.*;
import org.forum.internal.permissions.Permissions;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


// Kasutajate tabelis on nüüd seos loodud/likeitud postitustega/threadidega. Muutsin mõned
// muutujate nimed (capsiga kirjutatakse tavaliselt konstandid, seega permissions on nüüd
// väikeste tähtedega. Muutsin mõned meetodite nimetused.
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @Column(unique = true)
    private String SESSION_TOKEN;
    @ElementCollection
    private HashMap<String, Boolean> permissions;

    @OneToMany
    private Set<Post> likes = new HashSet<>();

    @OneToMany
    private Set<Post> posts = new HashSet<>();

    @OneToMany
    private Set<Thread> threads = new HashSet<>();

    private void setNonDefaultPermissions(String permissions) {
        String[][] perms = Arrays.stream(permissions.split(","))
                .map(g -> g.split("="))
                .toArray(String[][]::new);
        for (String[] perm : perms) this.permissions.replace(perm[0], Boolean.valueOf(perm[1]));
    }

    public String getSessionToken() {
        return SESSION_TOKEN;
    }

    public HashMap<String, Boolean> getPermissions() {
        return permissions;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSessionToken(String SESSION_TOKEN) {
        this.SESSION_TOKEN = SESSION_TOKEN;
    }

    public void setPermissions(String permissions) throws IOException {
        this.permissions = Permissions.getDefaults();
        if (permissions.isBlank()) setNonDefaultPermissions(permissions);
    }
}
