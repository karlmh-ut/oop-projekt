package org.forum.pipeline;

import org.forum.processors.RequestType;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final int INPUT_INT = 0;
    public static final int INPUT_STRING = 1;

    private static void sendEcho(DataOutputStream dos, DataInputStream dis, String msg) throws IOException {
        dos.writeInt(RequestType.REQUEST_PING);
        dos.writeUTF(msg);
        System.out.println("[Echo] Saatsin: " + msg);
        String resp = dis.readUTF();
        System.out.println("[Echo] Server vastas: " + resp);
    }

    private static void sendMessage(DataOutputStream dos, DataInputStream dis, Scanner console) throws IOException {
        int responseType = dis.readInt();
        if (responseType == INPUT_INT) {
            dos.writeInt(console.nextInt());
        } else if (responseType == INPUT_STRING) {
            dos.writeUTF(console.nextLine());
        } else {
            throw new IOException("invalid response type");
        }
    }

    public static void main(String[] args) throws Exception {
        try (Socket sock = new Socket("localhost", Server.PORTNUM);
             DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
             DataInputStream dis = new DataInputStream(sock.getInputStream())) {

            try (Scanner console = new Scanner(System.in)) {
                while (true) {
                    sendMessage(dos, dis, console);
                }
            }
//            sendEcho(dos, dis, "sample text, tere");
//            dos.writeInt(RequestProcessorFactory.TYPE_END);
        }
    }
}
