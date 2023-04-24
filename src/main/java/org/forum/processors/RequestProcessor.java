package org.forum.processors;

import jakarta.persistence.EntityManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface RequestProcessor {
    public abstract void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos) throws Exception;
}
