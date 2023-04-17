package org.forum.processors;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class EchoProcessor implements RequestProcessor {
    @Override
    public void processRequest(DataInputStream dis, DataOutputStream dos) throws IOException {
        String msg = dis.readUTF();
        dos.writeUTF(msg);
    }
}
