package org.forum.processors.server;

import jakarta.persistence.EntityManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public interface RequestProcessor {
    void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos, Socket sock) throws Exception;
        // DataOutputStream format:
        // 1. REQUEST_CODE / ERROR CODE
        // 2. STATUS_CODE
        // 3. CONTENT
}
