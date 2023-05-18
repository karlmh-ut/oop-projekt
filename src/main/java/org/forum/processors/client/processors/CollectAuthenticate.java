package org.forum.processors.client.processors;

import org.forum.processors.client.ResponseProcessor;
import org.forum.processors.server.processors.RespondAuthentication;

import java.io.IOException;
import java.net.Socket;

import static org.forum.processors.vars.RequestCodes.REQUEST_AUTHENTICATE;

public class CollectAuthenticate implements ResponseProcessor {
    @Override
    public void process(int code, String msg, Socket sock) throws IOException {
        if (code != REQUEST_AUTHENTICATE) return;

        System.out.println(msg);
    }
}
