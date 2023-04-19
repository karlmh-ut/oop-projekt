package org.forum.internal.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UsersSQL {
    private UsersSQL() {}

    /**
     * @return ArrayList<String> of currently existing users
     */
    public static List<String> getUsers(Connection connection) throws SQLException {
        ArrayList<String> users = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT username FROM users;");
        try (ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                users.add(username);
            }
        }
        return users;
    }

    /**
     * @param id user id
     * @return int[] of the ids of all threads made by the user
     */
    public static int[] getUserThreads(Connection connection, int id) throws SQLException {
        // TODO: implement
        throw new UnsupportedOperationException("Unimplemented");
    }

    /**
     * @param id user id
     * @return int[] of the ids of all comments made by the user
     */
    public static int[] getUserComments(Connection connection, int id) throws SQLException {
        // TODO: implement
        throw new UnsupportedOperationException("Unimplemented");
    }
}
