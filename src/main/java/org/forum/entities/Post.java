package org.forum.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Post {
    private String content;
    private final String datetime;
    private String editedtime;
    private final User author;
    private final ArrayList<User> likedBy;

    // So SQL request generates this?
    protected Post(String content, User author) {
        this.content = content; // Should be contained in file, text, images, formatting etc
        this.datetime = currentTime();
        this.editedtime = "";
        this.author = author;
        this.likedBy = new ArrayList<>(); // Replaceable with a SQL request, I'd replace it with an int of likedCount
    }

    /**
     * @return Current time as "yyyy/mm/dd hh:mm"
     */
    private String currentTime() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm").format(Calendar.getInstance()
                .getTime());
    }

    protected void updateEditedtime() {
        this.editedtime = currentTime();
    }

    /**
     * @return timestring, appended by "(edited yyyy/mm/dd hh:mm)" if appropriate
     */
    public String getPostTime() {
        String timestring = datetime;
        if (!(editedtime.equals(""))) timestring += " (edited " + editedtime + ")";
        return timestring;
    }

    //
    public String getContent() {
        return content;
    }

    /**
     * Update the content of the post and call updateEditedtime()
     */
    public void updateContent(String newcontent) { // We need a method for checking permissions for edits n shit
        updateEditedtime();
        this.content = newcontent;
    }

    public User getAuthor() {
        return author;
    }

    /**
     * Add the given user to the list of users that have liked the post,
     * ensuring that the same user is not added more than once.
     */
    public void addLike(User user) {
        if (!(this.likedBy.contains(user))) this.likedBy.add(user);
    } // SQL code should be better

    public void removeLike(User user) {
        this.likedBy.remove(user);
    } // SQL code

    public List<User> getLikedBy() {
        return likedBy;
    } // SQL code

    @Override
    public String toString() {
        return "Post{" +
                "content='" + content + '\'' +
                ", datetime='" + datetime + '\'' +
                ", editedtime='" + editedtime + '\'' +
                ", author=" + author +
                ", likedBy=" + likedBy +
                '}';
    }
}
