package org.forum.pipeline;

import jdk.dynalink.linker.support.CompositeTypeBasedGuardingDynamicLinker;
import org.forum.pipeline.Server;
import org.forum.processors.client.Receiver;
import org.h2.engine.User;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

import static org.forum.processors.vars.RequestCodes.*;

public class Client {
    protected long SESSION_TOKEN;
    private static Receiver receiver;

    // TODO: Revamp the whole communication between server and client, so initial request would be of type int and work forward from there

    public static void main(String[] args) throws Exception {
        try (Socket sock = new Socket("localhost", Server.PORTNUM);
             DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
             DataInputStream dis = new DataInputStream(sock.getInputStream())) {
            receiver = new Receiver();
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
        System.out.printf("Sending request %s%n", requestCode);
        dos.writeInt(requestCode);
        console.useDelimiter("\\n");
        String msg = console.next();
        msg = String.join(" ", Arrays.copyOfRange(msg.split(" "), 1, msg.split(" ").length));
        System.out.println(msg);
        dos.writeUTF(msg);
        console.reset();

    }

    private static void recieve(DataInputStream dis) throws IOException {
        int code = dis.readInt();
        if (code >= 400 && code < 500) handleError(code, dis);

        int status = dis.readInt();
        String msg = dis.readUTF();
        receiver.handleResponse(code, status, msg);
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
            case "listforums"   -> { return REQUEST_FORUM_LIST; }
            case "editpost"     -> { return REQUEST_EDIT_POST; }
            case "listposts"    -> { return REQUEST_FORUM_CONTENT; }
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

    public void SetSessionToken(long SESSION_TOKEN) {
        this.SESSION_TOKEN = SESSION_TOKEN;
    }
}
