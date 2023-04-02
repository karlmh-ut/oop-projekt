package org.forum.requests;

import org.forum.requestprocessor.RequestProcessor;

import java.net.Socket;

public class RequestProcessorFactory {

    public static final int TYPE_END = 0;
    public static final int TYPE_ECHO = 1;
    public static final int TYPE_AUTH = 2;


    public static RequestProcessor createRequestProcessor(int requestType, Socket sock) {
        if (requestType == TYPE_END) {
            return new EndProcessor(sock);
        } else if (requestType == TYPE_ECHO) {
            return new EchoProcessor();
        } else if (requestType == TYPE_AUTH) {
            return new AuthProcessor();
        } else {
            System.out.println("Ebasobiv sõnumi tüüp: " + requestType);
            return null;
        }
    }
}
