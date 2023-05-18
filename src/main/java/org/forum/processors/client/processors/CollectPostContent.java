package org.forum.processors.client.processors;

import org.forum.processors.client.ResponseProcessor;

import java.io.IOException;
import java.net.Socket;

import static org.forum.processors.vars.RequestCodes.REQUEST_POST_CONTENT;

public class CollectPostContent implements ResponseProcessor {
    @Override
    public void process(int code, String msg, Socket sock) throws IOException {
        if (code != REQUEST_POST_CONTENT) return;
        System.out.println("Printing post content...");
        System.out.println(msg);

    }
}
