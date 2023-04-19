package org.forum.internal.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class PostsSQL {
    private PostsSQL() {}

    /**
     * @return Current time as "yyyy/mm/dd hh:mm"
     */
    public static String currentTime() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm").format(Calendar.getInstance()
                .getTime());
    }

    /**
     * @param id thread/comment id
     * @param type either "thread" or "comment"
     */
    public static void updateEditedtime(Connection connection, int id, String type) throws SQLException {
        if (!(type.equals("thread") || type.equals("comment"))) {
            System.out.println("Argument must be either \"thread\" or \"comment\"");
            // TODO: handle?
        }

        String table = "";
        if (type.equals("thread")) table = "threads";
        if (type.equals("comment")) table = "comments";

        try (PreparedStatement ps = connection.prepareStatement("UPDATE ? SET edited_post_time = ? WHERE id = ?;")) {
            ps.setString(1, table);
            ps.setString(2, currentTime());
            ps.setString(3, String.valueOf(id));
            ps.executeUpdate();
        }
    }

    /**
     * Update the content of the post and call updateEditedtime().
     * Ensures that the acting user is either an admin or the author of the thread/comment
     * @param id thread/comment id
     * @param type either "thread" or "comment"
     * @param newcontent new content
     * @param username acting user
     */
    public static void updateContent(Connection connection, int id, String type, String newcontent, String username) throws SQLException {
        // TODO: ensure that user is either admin or author

        if (!(type.equals("thread") || type.equals("comment"))) {
            System.out.println("Argument must be either \"thread\" or \"comment\"");
            // TODO: handle?
        }

        String table = "";
        if (type.equals("thread")) table = "threads";
        if (type.equals("comment")) table = "comments";

        try (PreparedStatement ps = connection.prepareStatement("UPDATE ? SET content = ? WHERE id = ?;")) {
            ps.setString(1, table);
            ps.setString(2, newcontent);
            ps.setString(3, String.valueOf(id));
            ps.executeUpdate();
        }

        updateEditedtime(connection, id, type);
    }

    /**
     * @param id thread/comment id
     * @param type either "thread" or "comment"
     * @return thread/comment content
     */
    public static String getPostContent(Connection connection, int id, String type) throws SQLException {
        if (!(type.equals("thread") || type.equals("comment"))) {
            System.out.println("Argument must be either \"thread\" or \"comment\"");
            // TODO: handle?
        }

        String table = "";
        if (type.equals("thread")) table = "threads";
        if (type.equals("comment")) table = "comments";

        PreparedStatement ps = connection.prepareStatement("SELECT content FROM ? WHERE id = ?;");
        ps.setString(1, table);
        ps.setString(2, String.valueOf(id));
        try (ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                return resultSet.getString("content");
            }
        }
        return null;
    }

    /**
     * @param id thread ID
     * @return List<Integer> of comment ids
     */
    public static List<Integer> getThreadComments(Connection connection, int id) throws SQLException {
        ArrayList<Integer> comments = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT id FROM comments WHERE thread = ?;");
        ps.setString(1, String.valueOf(id));
        try (ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                int comment_id = resultSet.getInt("id");
                comments.add(comment_id);
            }
        }
        return comments;
    }
}
