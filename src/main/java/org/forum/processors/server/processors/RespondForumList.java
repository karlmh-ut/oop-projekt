package org.forum.processors.server.processors;

import jakarta.persistence.EntityManager;
import org.forum.entities.Subforums;
import org.forum.processors.server.RequestProcessor;
import org.forum.processors.server.SendResponse;
import org.forum.processors.server.Transaction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import static org.forum.processors.vars.RequestCodes.REQUEST_FORUM_LIST;
import static org.forum.processors.vars.ResponseCodes.*;

public class RespondForumList implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos, Socket sock) throws Exception {
        if (requestType != REQUEST_FORUM_LIST) return;
        dis.readUTF(); // Read message to clear out inputstream
        try {
            Transaction.runInTransaction(entityManager, () -> {
                List<Subforums> resultList = entityManager.createQuery("SELECT s FROM Subforums s", Subforums.class).getResultList();
                String[] subforums = resultList.stream().map(Subforums::getName).toArray(String[]::new);

                String msg = String.join("/", subforums);

                new SendResponse(dos, dis, REQUEST_FORUM_LIST, RESPONSE_OK, msg);
            });
        } catch (Exception e) {
            new SendResponse(dos, dis, REQUEST_FORUM_LIST, RESPONSE_FAILED, e.getLocalizedMessage());
        }
    }
}
