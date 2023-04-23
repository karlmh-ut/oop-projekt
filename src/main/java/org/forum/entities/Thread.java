package org.forum.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

// Struktuur sama kui andmebaasis oli. Lisasin foreign-id, mis seob iga Threadi koos tema autoriga.
@Entity
public class Thread {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Subforum subforum;

    private String title;

    private LocalDate initial_post_time;

    private LocalDate edited_post_time;

    @ManyToOne
    private User author;

    private String content;

    public Subforum getSubforum() {
        return subforum;
    }

    public void setSubforum(Subforum subforum) {
        this.subforum = subforum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getInitial_post_time() {
        return initial_post_time;
    }

    public void setInitial_post_time(LocalDate initial_post_time) {
        this.initial_post_time = initial_post_time;
    }

    public LocalDate getEdited_post_time() {
        return edited_post_time;
    }

    public void setEdited_post_time(LocalDate edited_post_time) {
        this.edited_post_time = edited_post_time;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
