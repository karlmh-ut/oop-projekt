package org.forum.processors;

import jakarta.persistence.EntityManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static org.forum.processors.RequestType.REQUEST_ECHO;

public class Echo extends RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos) throws IOException {
        if (requestType != REQUEST_ECHO) {
            return;
        }

        String msg = dis.readUTF();
        dos.writeUTF(msg);
    }
}
