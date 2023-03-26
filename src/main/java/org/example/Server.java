package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Teenindaja extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket sock;

    public Teenindaja(Socket sock) throws IOException {
        this.sock = sock;
        this.dis = new DataInputStream(this.sock.getInputStream());
        this.dos = new DataOutputStream(this.sock.getOutputStream());
    }

    public void processRequest() throws IOException{
        int requesttype = this.dis.readInt();
        switch (requesttype) {
            case (Server.TYPE_END) -> processEnd();
            case (Server.TYPE_ECHO) -> processEcho();
            default -> System.out.println("Ebasobiv sõnumi tüüp: " + requesttype);
        }
    }

    private void processEcho() throws IOException {
        String msg = this.dis.readUTF();
        this.dos.writeUTF(msg);
    }

    private void processEnd() throws IOException {
        System.out.println("Lõpetatud ühendus: " + this.sock);
        this.sock.close();
    }

    @Override
    public void run() {
        try (this.sock; this.dis; this.dos) {
            while (!this.sock.isClosed()) processRequest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

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
                Thread t = new Teenindaja(socket);
                t.start();
            }
        }
    }
}
