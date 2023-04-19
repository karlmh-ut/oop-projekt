package org.forum.entities;

import java.util.ArrayList;
import java.util.List;

public class Subforum {
    private String name;
    private final List<ThreadPost> posts;

    public Subforum(String name) {
        this.name = name;
        this.posts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Change the name of the subforum if the given user is admin
     */
    public void editName(String newname, User user) {
        //if (user.isAdmin()) this.name = newname;
        return;
        //TODO: else?
    }

    public List<ThreadPost> getPosts() {
        return posts;
    }

    /**
     * Delete post if the acting user is admin OR if the acting user is the author of the post
     */
    public void deletePost(ThreadPost threadPost, User user) {
//        if (user.isAdmin() || threadPost.getAuthor() == user) {
//            this.posts.remove(threadPost);
//        }
        return;
    }

    private boolean hasThreadPostByName(String name) {
        for (ThreadPost s : this.posts) {
            if (s.getTitle().equals(name)) return true;
        }
        return false;
    }

    /**
     * Add a post to the subforum's internal list,
     * making sure the given title does not match that of any existing posts
     */
    public void addPost(ThreadPost post) {
        if (!hasThreadPostByName(post.getTitle())) this.posts.add(post);
        //TODO: else?
    }
}
