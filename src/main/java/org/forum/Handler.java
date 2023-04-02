package org.forum;

import org.forum.requestprocessor.RequestProcessor;
import org.forum.requests.RequestProcessorFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class Handler implements Runnable {
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final Socket sock;



    public Handler(Socket sock) throws IOException {
        this.sock = sock;
        this.dis = new DataInputStream(this.sock.getInputStream());
        this.dos = new DataOutputStream(this.sock.getOutputStream());
    }

    @Override
    public void run() {
        try (sock; dis; dos) {
            while (!sock.isClosed()) {
                int requestType = dis.readInt();
                RequestProcessor requestProcessor = RequestProcessorFactory.createRequestProcessor(requestType, sock);
                requestProcessor.processRequest(dis, dos);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
