package org.forum.processors.client.processors;

import org.forum.processors.client.ResponseProcessor;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import static org.forum.processors.vars.RequestCodes.REQUEST_FORUM_LIST;

public class CollectForumList implements ResponseProcessor {
    @Override
    public void process(int code, String msg, Socket sock) throws IOException {
        if (code != REQUEST_FORUM_LIST) return;
        String[] forums = msg.split("/");
        System.out.println(Arrays.toString(forums));
    }
}
