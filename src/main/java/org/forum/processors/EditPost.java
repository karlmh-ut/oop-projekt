package org.forum.processors;

import jakarta.persistence.EntityManager;
import org.forum.entities.Post;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static org.forum.pipeline.Client.INPUT_STRING;
import static org.forum.processors.RequestType.REQUEST_EDIT_POST;

public class EditPost extends RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos) throws Exception {
        if (requestType != REQUEST_EDIT_POST) {
            return;
        }

        Transaction.runInTransaction(entityManager, () -> {
            dos.writeInt(INPUT_STRING);
            String[] response = dis.readUTF().split(" ");
            Long postId = Long.valueOf(response[0]);
            Post post = entityManager.find(Post.class, postId);
            post.setContent(response[1]);
        });
    }
}
