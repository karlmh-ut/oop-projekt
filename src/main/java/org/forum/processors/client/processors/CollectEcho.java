package org.forum.processors.client.processors;

import org.forum.processors.client.ResponseProcessor;

import java.io.DataInputStream;
import java.io.IOException;

import static org.forum.processors.vars.RequestCodes.REQUEST_ECHO;

public class CollectEcho implements ResponseProcessor {
    @Override
    public void process(int code, DataInputStream dis) throws IOException {
        if (code != REQUEST_ECHO) return;

        System.out.println(dis.readUTF());
    }
}
