package org.forum.processors;

import java.net.Socket;
import org.forum.processors.RequestType;

public class RequestProcessorFactory {

    public static RequestProcessor createRequestProcessor(int requestType, Socket sock) {
        switch (requestType) {
            case RequestType.REQUEST_END -> { return new EndProcessor(sock); }
            case RequestType.REQUEST_PING -> { return new EchoProcessor(); }
            case RequestType.REQUEST_AUTHENTICATE -> { return new AuthProcessor(); }
            default -> { System.out.println("Ebasobiv sõnumi tüüp: " + requestType); return null; }
        }
    }
}
