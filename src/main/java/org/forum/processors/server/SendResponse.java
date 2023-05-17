package org.forum.processors.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SendResponse {
    public SendResponse(DataOutputStream dos, DataInputStream dis, int code, int status, String msg) throws IOException {
        System.out.printf("Sending response %s with status %s and message \"%s\"%n", code, status, msg);
        dos.writeInt(code);
        dos.writeInt(status);
        dos.writeUTF(msg);
    }
}
