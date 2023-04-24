package org.forum.processors.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import static org.forum.processors.vars.ResponseCodes.*;

public class Reciever {
    List<ResponseProcessor> responseProcessors;
    DataInputStream dis;
    DataOutputStream dos;
    Socket sock;

    public Reciever(DataInputStream dis, DataOutputStream dos, Socket sock) {
        this.dis = dis;
        this.dos = dos;
        this.sock = sock;
        this.responseProcessors = ResponseProcessorLoader.load();
    }

    public void handleResponse(int code, int status) throws IOException {
        switch (status) { // Using switch here in case we want to implement more status codes
            case RESPONSE_FAILED, RESPONSE_RESTRICTED -> {
                String message = status == RESPONSE_FAILED ?
                        "Request failed, please try again." :
                        "You don't have permission to do this.";
                System.out.println(message);
                return;
            }
        }
        for (ResponseProcessor responseProcessor : responseProcessors) {
            responseProcessor.process(code, dis);
        }
    }
}
