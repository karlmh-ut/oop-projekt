package org.forum.processors.server.processors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.forum.entities.Posts;
import org.forum.entities.Threads;
import org.forum.processors.server.RequestProcessor;
import org.forum.processors.server.SendResponse;
import org.forum.processors.server.Transaction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import static org.forum.processors.vars.RequestCodes.REQUEST_POST_CONTENT;
import static org.forum.processors.vars.ResponseCodes.RESPONSE_OK;

public class RespondPostContent implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos, Socket sock) throws Exception {
        if (requestType != REQUEST_POST_CONTENT) return;

        Transaction.runInTransaction(entityManager, () -> {
            long postId = Long.parseLong(dis.readUTF());
            System.out.println(postId);
            Threads thread = entityManager.find(Threads.class, postId);

            String nl = System.getProperty("line.separator");

            String author = "Author: " + thread.getAuthor().getUsername() + nl;
            // Wrap content
            StringBuilder sb = new StringBuilder(thread.getTitle());
            int i = 0;
            while ((i = sb.indexOf(" ", i + 60)) != -1) {
                sb.replace(i, i + 1, nl);
            }
            String title = sb + nl + "-".repeat(sb.length());
            String content = thread.getContent() + nl;
            String postTime = "Post time: " + thread.getInitialPostTime();
            String editTime = "Last edited: " + thread.getEditedPostTime() + nl;


            Query query = entityManager.createQuery("SELECT p from Posts p where threads = :thread", Posts.class);
            query.setParameter("thread", thread);
            @SuppressWarnings("unchecked")
            List<Posts> resultList = query.getResultList();
            String comments = String.join(nl, resultList.stream().map(c ->
                            "Author: " + c.getAuthor().getUsername() + nl +
                                    c.getContent() + nl +
                                    "Post time: " + c.getInitialPostTime() + nl +
                                    "Edit time: " + c.getEditedPostTime() + nl)
                    .toList());

            String msg = author + nl +
                    title + nl +
                    content + nl +
                    postTime + nl +
                    editTime + nl + nl +
                    comments;

            new SendResponse(dos, dis, REQUEST_POST_CONTENT, RESPONSE_OK, msg);
        });
    }
}
