package org.forum.processors.client;

import java.io.DataInputStream;
import java.io.IOException;

public interface ResponseProcessor {
    void process(int code, DataInputStream dis) throws IOException;
}
