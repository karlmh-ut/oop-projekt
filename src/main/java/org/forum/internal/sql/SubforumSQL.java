package org.forum.internal.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SubforumSQL {
    private SubforumSQL() {}

    /**
     * Change the name of the given subforum
     * @param oldname old (current) name
     * @param newname new name
     * @param username acting user
     */
    public static void editName(Connection connection, String oldname, String newname, String username) {
        // TODO: check if acting user is admin
        // TODO: handle response for insufficient permissions
        // TODO: make sure newname doesn't match any existing subforum
        throw new UnsupportedOperationException("Unimplemented");
    }

    /**
     * delete thread with given id and drop all associated comments
     * @param id thread id
     * @param username acting user
     */
    public static void deleteThread(Connection connection, int id, String username) {
        // TODO: check if acting user is admin or post author
        // TODO: handle response for insufficient permissions
        // TODO: drop all associated comments
        throw new UnsupportedOperationException("Unimplemented");
    }

    /**
     * @param name new subforum name
     * @param user acting user
     */
    public static void addSubforum(Connection connection, String name, String user) {
        // TODO: check if acting user is admin
        // TODO: ensure that a subforum by the given name does not already exist
        // TODO: handle response for insufficient permissions
        throw new UnsupportedOperationException("Unimplemented");
    }

    /**
     * @return HashMap<Integer, String> of the given subforum's thread IDs and their respective overviews
     */
    public static Map<Integer, String> getThreadPreviews(Connection connection, String subforum) throws SQLException {
        HashMap<Integer, String> hm = new HashMap<>();
        PreparedStatement ps = connection.prepareStatement("SELECT id, title, initial_post_time, author FROM threads;");
        try (ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String preview = resultSet.getString("title") + " (" + resultSet.getString("author") + ", " + resultSet.getString("initial_post_time") + ")";
                hm.put(id, preview);
            }
        }
        return hm;
    }

    /**
     * @return ArrayList<String> of currently existing subforums
     */
    public static List<String> getSubforums(Connection connection) throws SQLException {
        ArrayList<String> subforums = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT name FROM subforums;");
        try (ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                subforums.add(name);
            }
        }
        return subforums;
    }
}
