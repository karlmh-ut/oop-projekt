package org.forum.processors;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EndProcessor implements RequestProcessor {
    private final Socket sock;
    public EndProcessor(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void processRequest(DataInputStream dis, DataOutputStream dos) throws IOException {
        System.out.println("Lõpetatud ühendus: " + sock);
        sock.close();
    }
}
