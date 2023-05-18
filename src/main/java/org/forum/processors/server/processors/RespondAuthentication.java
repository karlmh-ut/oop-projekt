package org.forum.processors.server.processors;

import jakarta.persistence.EntityManager;
import org.forum.entities.Users;
import org.forum.processors.server.RequestProcessor;
import org.forum.processors.server.SendResponse;
import org.forum.processors.server.Transaction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import static org.forum.processors.vars.RequestCodes.REQUEST_AUTHENTICATE;

public class RespondAuthentication implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos, Socket sock) throws Exception {
        if (requestType != REQUEST_AUTHENTICATE) return;
        System.out.println("Authenticating...");
        System.out.println(sock.hashCode());
        Transaction.runInTransaction(entityManager, () -> {
            String username = dis.readUTF();
            List<Users> users = entityManager.createQuery("SELECT u FROM Users u", Users.class).getResultList();
            Users result = users.stream().filter(u -> u.getUsername().equals(username)).toArray(Users[]::new)[0];
            result.setSessionToken(sock.hashCode());

            new SendResponse(dos, dis, REQUEST_AUTHENTICATE, 0, "Connected successfully!");
        });
    }
}
