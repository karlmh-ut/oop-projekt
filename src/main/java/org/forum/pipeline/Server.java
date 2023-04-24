package org.forum.pipeline;

import org.h2.tools.RunScript;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    public static final int PORTNUM = 1337;

    private static Connection connectToDatabase(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }

    private static InputStreamReader readFromClasspath() throws UnsupportedEncodingException {
        ClassLoader cl = Server.class.getClassLoader();
        //noinspection DataFlowIssue
        return new InputStreamReader(cl.getResourceAsStream("setup.sql"), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        try (Connection connection = connectToDatabase("jdbc:h2:mem:")) {
            try (Reader setupSql = readFromClasspath()) {
                RunScript.execute(connection, setupSql);
            }

            // administrate the in-memory DB through a built-in web UI
            // new thread as startWebServer() blocks otherwise
            new Thread(() -> {
                try {
                    org.h2.tools.Server.startWebServer(connection);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            try (ServerSocket ss = new ServerSocket(Server.PORTNUM)) {
                System.out.println("Ootan ühendusi");
                while (true) {
                    Socket socket = ss.accept();
                    System.out.println("Uus ühendus: " + socket);
                    Thread t = new Thread(new Handler(socket));
                    t.start();
                }
            }
        }
    }
}
