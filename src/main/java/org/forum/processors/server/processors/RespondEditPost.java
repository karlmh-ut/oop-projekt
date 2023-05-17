package org.forum.processors.server.processors;

import jakarta.persistence.EntityManager;
import jdk.swing.interop.SwingInterOpUtils;
import org.forum.entities.Posts;
import org.forum.entities.Users;
import org.forum.processors.server.SendResponse;
import org.forum.processors.server.Transaction;
import org.forum.processors.server.RequestProcessor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.forum.internal.permissions.PermissionKeywords.ADMIN_FULL_PRIVILEGES;
import static org.forum.processors.vars.RequestCodes.REQUEST_EDIT_POST;
import static org.forum.processors.vars.ResponseCodes.*;

public class RespondEditPost implements RequestProcessor {
    @Override
    public void process(EntityManager entityManager, int requestType, DataInputStream dis, DataOutputStream dos, Socket sock) throws Exception {
        if (requestType != REQUEST_EDIT_POST) return;

        Transaction.runInTransaction(entityManager, () -> {
            String[] requestContent = dis.readUTF().split(" ");
            System.out.println(Arrays.toString(requestContent));
            Long postId = Long.parseLong(requestContent[0]);
            String content = String.join(" ", Arrays.copyOfRange(requestContent, 1, requestContent.length));

            // Get post
            Posts post = entityManager.find(Posts.class, postId);
            List<Users> users = entityManager.createQuery("SELECT u FROM Users u", Users.class).getResultList();
            Users author = post.getAuthor();

            Users[] usersArray = users.stream().filter(u -> u.getSessionToken() != null && u.getSessionToken().equals(sock.hashCode())).toArray(Users[]::new);
            if (usersArray.length == 0) {
                new SendResponse(dos, dis, REQUEST_EDIT_POST, RESPONSE_RESTRICTED, "You have not authenticated.");
            }

            Users requester = usersArray[0];

            Boolean adminPrivs = requester.getPermissions().get(ADMIN_FULL_PRIVILEGES);

            // Check if user is allowed to edit the post
            if (!author.equals(requester) && adminPrivs != null && !adminPrivs) {
                new SendResponse(dos, dis, REQUEST_EDIT_POST, RESPONSE_RESTRICTED, "You are not the author of this post.");
                return;
            }

            // Set new content
            post.setContent(content);
            post.setEditedPostTime(LocalDateTime.now());

            // Respond
            new SendResponse(dos, dis, REQUEST_EDIT_POST, RESPONSE_OK, "Post updated successfully!");
        });
    }
}
