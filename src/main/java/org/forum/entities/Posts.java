package org.forum.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// Comments-tabeli adaptatsioon. On olemas foreign-keys postituse autoritele ning
// threadidele, kuhu nemad kuuluvad. Võib kaaluda likedBy välja lisamist.
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Threads threads;

    private LocalDateTime initial_post_time;

    private LocalDateTime edited_post_time;

    @ManyToOne
    private Users author;

    private String content;

    public Threads getThread() {
        return threads;
    }

    public void setThread(Threads threads) {
        this.threads = threads;
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

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", threads=" + threads +
                ", initial_post_time=" + initial_post_time +
                ", edited_post_time=" + edited_post_time +
                ", author=" + author +
                ", content='" + content + '\'' +
                '}';
    }
}
