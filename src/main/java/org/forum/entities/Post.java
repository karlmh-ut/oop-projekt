package org.forum.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

// Comments-tabeli adaptatsioon. On olemas foreign-keys postituse autoritele ning
// threadidele, kuhu nemad kuuluvad. Võib kaaluda likedBy välja lisamist.
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Thread thread;

    private LocalDate initial_post_time;

    private LocalDate edited_post_time;

    @ManyToOne
    private User author;

    private String content;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
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
