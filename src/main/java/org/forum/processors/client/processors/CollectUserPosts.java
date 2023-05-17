package org.forum.processors.client.processors;

import org.forum.processors.client.ResponseProcessor;

import java.io.IOException;
import java.net.Socket;

import static org.forum.processors.vars.RequestCodes.REQUEST_USER_POSTS;

public class CollectUserPosts implements ResponseProcessor {
    @Override
    public void process(int code, String msg, Socket sock) throws IOException {
        if (code != REQUEST_USER_POSTS) return;

        System.out.println(msg);
    }
}
