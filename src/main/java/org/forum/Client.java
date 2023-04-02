package org.forum;

import org.forum.requests.RequestProcessorFactory;

import java.io.*;
import java.net.Socket;

public class Client {

    private static void sendEcho(DataOutputStream dos, DataInputStream dis, String msg) throws IOException {
        dos.writeInt(RequestProcessorFactory.TYPE_ECHO);
        dos.writeUTF(msg);
        System.out.println("[Echo] Saatsin: " + msg);
        String resp = dis.readUTF();
        System.out.println("[Echo] Server vastas: " + resp);
    }

    public static void main(String[] args) throws Exception {
        try (Socket sock = new Socket("localhost", Server.PORTNUM);
             DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
             DataInputStream dis = new DataInputStream(sock.getInputStream())) {

            sendEcho(dos, dis, "sample text, tere");
            dos.writeInt(RequestProcessorFactory.TYPE_END);
        }
    }
}
