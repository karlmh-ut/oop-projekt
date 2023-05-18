package org.forum.processors.server.processors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.forum.entities.Subforums;
import org.forum.entities.Threads;
import org.forum.processors.server.RequestProcessor;
import org.forum.processors.server.SendResponse;
import org.forum.processors.server.Transaction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

import static org.forum.processors.vars.RequestCodes.REQUEST_FORUM_CONTENT;
import static org.forum.processors.vars.ResponseCodes.RESPONSE_OK;

public class RespondGetPosts implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos, Socket sock) throws Exception {
        if (requestType != REQUEST_FORUM_CONTENT) return;

        Transaction.runInTransaction(entityManager, () -> {
            Query query = entityManager.createQuery("SELECT t FROM Threads t WHERE t.subforums = :subforum", Threads.class);
            long subforumId = Long.parseLong(dis.readUTF());
            System.out.println(subforumId);
            Subforums subforums = entityManager.find(Subforums.class, subforumId);
            query.setParameter("subforum", subforums);

            @SuppressWarnings("unchecked")
            List<Threads> threads = query.getResultList();

            List<String> threadHeads = threads.stream().map(Threads::getTitle).toList();

            String msg = String.join("/", threadHeads);

            new SendResponse(dos, dis, REQUEST_FORUM_CONTENT, RESPONSE_OK, msg);
        });
    }
}
