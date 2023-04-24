package org.forum.pipeline;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    public static final int PORTNUM = 1337;

    private static Connection connectToDatabase(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }

    private static InputStreamReader readFromClasspath(String name) throws UnsupportedEncodingException {
        ClassLoader cl = Server.class.getClassLoader();
        return new InputStreamReader(cl.getResourceAsStream(name), "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("forum"); // Initialize database
            try (ServerSocket ss = new ServerSocket(Server.PORTNUM)) { // Start server
                System.out.println("Ootan ühendusi");
                while (true) {
                    Socket socket = ss.accept();
                    System.out.println("Uus ühendus: " + socket);
                    Thread t = new Thread(new Handler(socket, entityManagerFactory));
                    t.start();
                } // Accept connections
            } finally {
                entityManagerFactory.close();
            }
    }
}
