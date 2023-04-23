package org.forum.processors;

import jakarta.persistence.EntityManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static org.forum.pipeline.Client.INPUT_STRING;
import static org.forum.processors.RequestType.REQUEST_ECHO;

public class Echo implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos) throws IOException {
        if (requestType != REQUEST_ECHO) {
            return;
        }

        dos.writeInt(INPUT_STRING);
        String msg = dis.readUTF();
        dos.writeUTF(msg);
        dos.writeUTF("");
    }
}
