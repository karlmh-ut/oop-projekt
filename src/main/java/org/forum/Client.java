package org.forum;

import org.forum.requests.RequestProcessorFactory;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final int INPUT_INT = 0;
    public static final int INPUT_STRING = 1;

    private static void sendEcho(DataOutputStream dos, DataInputStream dis, String msg) throws IOException {
        dos.writeInt(RequestProcessorFactory.TYPE_ECHO);
        dos.writeUTF(msg);
        System.out.println("[Echo] Saatsin: " + msg);
        String resp = dis.readUTF();
        System.out.println("[Echo] Server vastas: " + resp);
    }

    public static void authenticate(DataOutputStream dos) throws IOException {
        dos.writeInt(RequestProcessorFactory.TYPE_AUTH);
        System.out.println("Are you an existing user or do you want to create a user profile?");
        System.out.println("0 - Log In\t1 - Sign In");
    }

    private static void sendCustom(DataOutputStream dos, DataInputStream dis) throws IOException {
        int responseType = dis.readInt();
        Scanner in = new Scanner(System.in);
        if (responseType == INPUT_INT) {
            dos.writeInt(in.nextInt());
        } else if (responseType == INPUT_STRING) {
            dos.writeUTF(in.nextLine());
        } else {
            throw new IOException("invalid response type");
        }
    }

    public static void main(String[] args) throws Exception {
        try (Socket sock = new Socket("localhost", Server.PORTNUM);
             DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
             DataInputStream dis = new DataInputStream(sock.getInputStream())) {

            authenticate(dos);
            while (true) {
                sendCustom(dos, dis);
            }
//            sendEcho(dos, dis, "sample text, tere");
//            dos.writeInt(RequestProcessorFactory.TYPE_END);
        }
    }
}
