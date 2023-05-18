package org.forum.processors.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import static org.forum.processors.vars.ResponseCodes.*;

public class Receiver {
    List<ResponseProcessor> responseProcessors;
    DataInputStream dis;
    DataOutputStream dos;
    Socket sock;

    public Receiver() {
        this.responseProcessors = ResponseProcessorLoader.load();
    }

    public void handleResponse(int code, int status, String msg) throws IOException {
        System.out.println("Handling...");
        switch (status) { // Using switch here in case we want to implement more status codes
            case RESPONSE_FAILED, RESPONSE_RESTRICTED -> {
                String message =
                        status == RESPONSE_FAILED ?
                        "Request failed, please try again." :
                        "You don't have permission to do this.";
                System.out.println(message);
                System.out.printf("Error message: %s%n", msg);
                return;
            }
        }
        for (ResponseProcessor responseProcessor : responseProcessors) {
            responseProcessor.process(code, msg, sock);
        }
    }
}
