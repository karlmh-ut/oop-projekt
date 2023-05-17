package org.forum.processors.server;

import org.forum.processors.server.processors.*;

import java.util.Arrays;
import java.util.List;

public class RequestProcessorLoader {
    public static List<RequestProcessor> load() {
        return Arrays.asList(
                new RespondEcho(),
                new RespondEditPost(),
                new RespondUserPosts(),
                new RespondForumList(),
                new RespondAuthentication(),
                new RespondGetPosts(),
                new RespondPostContent()
        );
    }
}
