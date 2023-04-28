package org.forum.processors.server.processors;

import jakarta.persistence.EntityManager;
import org.forum.entities.Posts;
import org.forum.processors.server.SendResponse;
import org.forum.processors.server.Transaction;
import org.forum.processors.server.RequestProcessor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.time.LocalDateTime;

import static org.forum.processors.vars.RequestCodes.REQUEST_EDIT_POST;
import static org.forum.processors.vars.ResponseCodes.*;

public class RespondEditPost implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos) throws Exception {
        if (requestType != REQUEST_EDIT_POST) {
            return;
        }

        Transaction.runInTransaction(entityManager, () -> {
            String[] response = dis.readUTF().split(" ");
            Long postId = Long.valueOf(response[0]);
            Posts posts = entityManager.find(Posts.class, postId);
            posts.setContent(response[1]);
            posts.setEditedPostTime(LocalDateTime.now());
            new SendResponse(dos, REQUEST_EDIT_POST, RESPONSE_OK, "Post updated successfully!");
        });
    }
}
