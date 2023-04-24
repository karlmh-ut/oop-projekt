package org.forum.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// Struktuur sama kui andmebaasis oli. Lisasin foreign-id, mis seob iga Threadi koos tema autoriga.
@Entity
public class Threads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Subforums subforums;

    private String title;

    private LocalDateTime initial_post_time;

    private LocalDateTime edited_post_time;

    @ManyToOne
    private Users author;

    private String content;

    public Subforums getSubforum() {
        return subforums;
    }

    public void setSubforum(Subforums subforums) {
        this.subforums = subforums;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getInitialPostTime() {
        return initial_post_time;
    }

    public void setInitialPostTime(LocalDateTime initial_post_time) {
        this.initial_post_time = initial_post_time;
    }

    public LocalDateTime getEditedPostTime() {
        return edited_post_time;
    }

    public void setEditedPostTime(LocalDateTime edited_post_time) {
        this.edited_post_time = edited_post_time;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
