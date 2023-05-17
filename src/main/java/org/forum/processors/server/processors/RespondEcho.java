package org.forum.processors.server.processors;

import jakarta.persistence.EntityManager;
import org.forum.processors.server.RequestProcessor;
import org.forum.processors.server.SendResponse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.forum.processors.vars.RequestCodes.REQUEST_ECHO;
import static org.forum.processors.vars.ResponseCodes.*;

public class RespondEcho implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos, Socket sock) throws IOException {
        if (requestType != REQUEST_ECHO) return;
        try {
            String msg = dis.readUTF();
            new SendResponse(dos, dis, REQUEST_ECHO, RESPONSE_OK, msg);
        } catch (Exception e) {
            new SendResponse(dos, dis, REQUEST_ECHO, RESPONSE_FAILED, "");
        }
    }
}
