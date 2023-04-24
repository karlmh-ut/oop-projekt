package org.forum.pipeline.client;

import org.forum.pipeline.Server;
import org.forum.processors.client.Reciever;
import org.h2.engine.User;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

import static org.forum.processors.vars.RequestCodes.*;

public class Client {
    protected User userObject;
    private static Reciever reciever;

    // TODO: Revamp the whole communication between server and client, so initial request would be of type int and work forward from there

    public static void main(String[] args) throws Exception {
        try (Socket sock = new Socket("localhost", Server.PORTNUM);
             DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
             DataInputStream dis = new DataInputStream(sock.getInputStream())) {
            reciever = new Reciever(dis, dos, sock);
            try (Scanner console = new Scanner(System.in)) {
                while (true) {
                    System.out.print("> "); // A prefix to see what is console input
                    request(dos, console);
                    recieve(dis);
                }
            }
        }
    }

    /**
     * Sends a request to server
     * @param dos DataOutputStream where to write
     * @param console console from where to read commands
     */
    private static void request(DataOutputStream dos, Scanner console) throws IOException {
        String command = console.next();
        int requestCode = convertStringIntoRequestCode(command);
        dos.writeInt(requestCode);
        String msg = console.next();
        dos.writeUTF(msg);
    }

    private static void recieve(DataInputStream dis) throws IOException {
        int code = dis.readInt();
        if (code >= 400 && code < 500) handleError(code, dis);

        int status = dis.readInt();

        reciever.handleResponse(code, status);
    }

    // Temporary function to work with console
    // add phrases to switch to return correct codes as required

    /**
     * Converts console commands into server processable request codes
     * @param phrase phrase to convert
     * @return server request code
     */
    private static int convertStringIntoRequestCode(String phrase) {
        switch (phrase) {
            case "ping"         -> { return REQUEST_PING; }
            case "authenticate" -> { return REQUEST_AUTHENTICATE; }
            case "getpost"      -> { return REQUEST_POST_CONTENT; }
            case "comment"      -> { return REQUEST_ADD_COMMENT; }
            case "echo"         -> { return REQUEST_ECHO; }
            default             -> { return REQUEST_EMPTY; }
        }
    }

    //noinspection
    /**
     * Outputs error code and message to the console
     * @param code Error code
     * @param dis error message
     */
    private static void handleError(int code, DataInputStream dis) throws IOException {
        System.out.println(code);
        System.out.println(dis.readUTF());
    }
}
