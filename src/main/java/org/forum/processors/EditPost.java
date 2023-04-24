package org.forum.processors;

import jakarta.persistence.EntityManager;
import org.forum.entities.Posts;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.time.LocalDateTime;

import static org.forum.pipeline.Client.INPUT_STRING;
import static org.forum.processors.RequestType.REQUEST_EDIT_POST;

public class EditPost implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos) throws Exception {
        if (requestType != REQUEST_EDIT_POST) {
            return;
        }

        Transaction.runInTransaction(entityManager, () -> {
            dos.writeInt(INPUT_STRING);
            String[] response = dis.readUTF().split(" ");
            Long postId = Long.valueOf(response[0]);
            Posts posts = entityManager.find(Posts.class, postId);
            posts.setContent(response[1]);
            posts.setEditedPostTime(LocalDateTime.now());
            dos.writeUTF("Post updated successfully!");
            dos.writeUTF("");
        });
    }
}
