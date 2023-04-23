package org.forum.processors;

import jakarta.persistence.EntityManager;
import org.forum.entities.Posts;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.time.LocalDateTime;
import java.util.List;

import static org.forum.pipeline.Client.INPUT_STRING;
import static org.forum.processors.RequestType.REQUEST_USER_POSTS;

public class UserPosts implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos) throws Exception {
        if (requestType != REQUEST_USER_POSTS) {
            return;
        }

        Transaction.runInTransaction(entityManager, () -> {
            dos.writeInt(INPUT_STRING);
            Long userId = Long.valueOf(dis.readUTF());
            List<Posts> posts = entityManager.createQuery("FROM Posts p WHERE p.author.id = :userId", Posts.class)
                    .setParameter("userId", userId)
                    .getResultList();
            if (posts.isEmpty()) {
                dos.writeUTF("User haven't posted anything yet!");
                dos.writeUTF("");
                return;
            }
            for (Posts post : posts) {
                dos.writeUTF(String.valueOf(post));
            }
            dos.writeUTF("");
        });
    }
}
