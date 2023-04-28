package org.forum.processors.server;

import jakarta.persistence.EntityManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface RequestProcessor {
    void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos) throws Exception;
}
