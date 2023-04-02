package org.forum;

import org.forum.entities.User;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int PORTNUM = 1337;
    private static final List<User> userList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
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
    public static List<User> getUserList() {
        return userList;
    }
}
