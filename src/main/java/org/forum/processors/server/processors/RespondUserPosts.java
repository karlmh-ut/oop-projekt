package org.forum.processors.server.processors;

import jakarta.persistence.EntityManager;
import org.forum.entities.Posts;
import org.forum.processors.server.SendResponse;
import org.forum.processors.server.Transaction;
import org.forum.processors.server.RequestProcessor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import static org.forum.processors.vars.RequestCodes.REQUEST_USER_POSTS;
import static org.forum.processors.vars.ResponseCodes.*;

public class RespondUserPosts implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos, Socket sock) throws Exception {
        if (requestType != REQUEST_USER_POSTS) {
            return;
        }

        Transaction.runInTransaction(entityManager, () -> {
            dos.writeInt(REQUEST_USER_POSTS);
            Long userId = Long.valueOf(dis.readUTF());
            List<Posts> posts = entityManager.createQuery("FROM Posts p WHERE p.author.id = :userId", Posts.class)
                    .setParameter("userId", userId)
                    .getResultList();
            if (posts.isEmpty()) {
                new SendResponse(dos, dis, REQUEST_USER_POSTS, RESPONSE_SOFTFAIL, "User hasn't posted anything yet!");
                return;
            }
            StringBuilder postsOut = new StringBuilder();
            for (Posts post : posts) {
                postsOut.append(post).append("/#/");
                dos.writeUTF(String.valueOf(post));
            }
            new SendResponse(dos, dis, REQUEST_USER_POSTS, RESPONSE_OK, postsOut.toString());
        });
    }
}
