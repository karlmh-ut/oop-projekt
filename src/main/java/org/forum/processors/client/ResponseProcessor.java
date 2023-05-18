package org.forum.processors.client;

import java.io.IOException;
import java.net.Socket;

public interface ResponseProcessor {
    void process(int code, String msg, Socket sock) throws IOException;
}
