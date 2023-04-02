package org.forum;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int TYPE_END = 0;
    static final int TYPE_ECHO = 1;
    static final int PORTNUM = 1337;

    public static void main(String[] args) throws Exception {
        try (ServerSocket ss = new ServerSocket(Server.PORTNUM)) {
            System.out.println("Ootan ühendusi");
            while (true) {
                Socket socket = ss.accept();
                System.out.println("Uus ühendus: " + socket);
                Thread t = new Thread(new Teenindaja(socket));
                t.start();
            }
        }
    }
}
