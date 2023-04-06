package org.forum.requests;

import org.forum.requestprocessor.RequestProcessor;

import java.net.Socket;

public class RequestProcessorFactory {

    public static final int TYPE_END = 0;
    public static final int TYPE_ECHO = 1;
    public static final int TYPE_AUTH = 2;


    public static RequestProcessor createRequestProcessor(int requestType, Socket sock) {
        switch (requestType) {
            case TYPE_END -> { return new EndProcessor(sock); }
            case TYPE_ECHO -> { return new EchoProcessor(); }
            case TYPE_AUTH -> { return new AuthProcessor(); }
            default -> { System.out.println("Ebasobiv sõnumi tüüp: " + requestType); return null; }
        }
    }
}
