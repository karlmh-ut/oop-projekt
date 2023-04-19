package org.forum.processors;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface RequestProcessor {
    void processRequest(DataInputStream dis, DataOutputStream dos) throws IOException;
}
