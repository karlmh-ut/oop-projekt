package org.forum.processors.client;

import org.forum.processors.client.processors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseProcessorLoader {
    public static List<ResponseProcessor> load() {
        return Arrays.asList(
                new CollectEcho(),
                new CollectForumList(),
                new CollectEditPost(),
                new CollectUserPosts(),
                new CollectAuthenticate(),
                new CollectGetPosts(),
                new CollectPostContent()
        );
    }
}
