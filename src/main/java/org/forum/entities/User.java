package org.forum.entities;

import org.forum.internal.Permissions;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class User {
    private String username;
    private String SESSION_TOKEN;
    private HashMap<String, Boolean> PERMISSIONS;

    public User(String username, String SESSION_TOKEN, String PERMISSIONS) throws IOException {
        this.username = username;
        this.SESSION_TOKEN = SESSION_TOKEN;
        this.PERMISSIONS = Permissions.getDefaults();
        if (PERMISSIONS.isBlank()) setNonDefaultPermissions(PERMISSIONS);
    }
    private void setNonDefaultPermissions(String permissions) {
        String[][] perms = Arrays.stream(permissions.split(","))
                .map(g -> g.split("="))
                .toArray(String[][]::new);
        for (String[] perm : perms) PERMISSIONS.replace(perm[0], Boolean.valueOf(perm[1]));
    }

    public String SESSION_TOKEN() {
        return SESSION_TOKEN;
    }

    public HashMap<String, Boolean> PERMISSIONS() {
        return PERMISSIONS;
    }
}
