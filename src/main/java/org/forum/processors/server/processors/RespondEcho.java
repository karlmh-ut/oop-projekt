package org.forum.processors.server.processors;

import jakarta.persistence.EntityManager;
import org.forum.processors.server.RequestProcessor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static org.forum.processors.vars.RequestCodes.REQUEST_ECHO;
import static org.forum.processors.vars.ResponseCodes.*;

public class RespondEcho implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos) throws IOException {
        if (requestType != REQUEST_ECHO) return;

        dos.writeInt(REQUEST_ECHO);
        try {
            String msg = dis.readUTF();
            dos.writeInt(RESPONSE_OK);
            dos.writeUTF(msg);
        } catch (Exception e) {
            dos.writeInt(RESPONSE_FAILED);
        }
    }
}
