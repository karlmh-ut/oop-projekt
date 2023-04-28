package org.forum.processors.server;

import org.forum.processors.server.processors.RespondEcho;
import org.forum.processors.server.processors.RespondEditPost;
import org.forum.processors.server.processors.RespondUserPosts;

import java.util.Arrays;
import java.util.List;

public class RequestProcessorLoader {
    public static List<RequestProcessor> load() {
        return Arrays.asList(
                new RespondEcho(),
                new RespondEditPost(),
                new RespondUserPosts()
        );
    }
}
