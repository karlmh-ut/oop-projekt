package org.forum.entities;

import java.util.ArrayList;
import java.util.List;

public class ThreadPost extends Post {
    private String title;
    private final ArrayList<ForumPost> forumPosts;

    public ThreadPost(String title, String content, User author) {
        super(content, author);
        this.title = title;
        this.forumPosts = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    /**
     * Update the title of the post and call updateEditedtime()
     */
    public void updateTitle(String newtitle) {
        updateEditedtime();
        this.title = newtitle;
    }

    public void addComment(ForumPost forumpost) {
        this.forumPosts.add(forumpost);
    }

    /**
     * Delete comment if the acting user is admin OR if the acting user is the author of the comment
     */
    public void removeComment(ForumPost forumpost, User user) {
        if (user.isAdmin() || forumpost.getAuthor() == user) {
            this.forumPosts.remove(forumpost);
        }
    }

    public List<ForumPost> getComments() {
        return forumPosts;
    }

    @Override
    public String toString() {
        return "ForumPost{" +
                "title='" + title + '\'' +
                ", comments=" + forumPosts +
                ", super{" + super.toString() +
                '}';
    }
}
