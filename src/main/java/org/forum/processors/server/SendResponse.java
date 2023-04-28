package org.forum.processors.server;

import java.io.DataOutputStream;
import java.io.IOException;

public class SendResponse {
    public SendResponse(DataOutputStream dos, int code, int status, String msg) throws IOException {
        dos.writeInt(code);
        dos.writeInt(status);
        dos.writeUTF(msg);
    }
}
