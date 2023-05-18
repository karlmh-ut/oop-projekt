package org.forum.processors.client.processors;

import org.forum.processors.client.ResponseProcessor;

import java.io.IOException;
import java.net.Socket;

import static org.forum.processors.vars.RequestCodes.REQUEST_FORUM_CONTENT;

public class CollectGetPosts implements ResponseProcessor {
    @Override
    public void process(int code, String msg, Socket sock) throws IOException {
        if (code != REQUEST_FORUM_CONTENT) return;

        String[] split = msg.split("/");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
