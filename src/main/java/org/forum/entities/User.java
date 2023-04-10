package org.forum.entities;

public class User {
    private Long id;
    private String login;
    private String password;
    private boolean admin;

    public User(Long id, String login, String password, boolean admin) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }

    public boolean isAdmin() {
        return admin;
    }
}
