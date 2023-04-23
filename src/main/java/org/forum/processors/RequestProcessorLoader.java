package org.forum.processors;

import java.util.Arrays;
import java.util.List;

public class RequestProcessorLoader {
    public static List<RequestProcessor> load() {
        return Arrays.asList(
                new Echo(),
                new EditPost(),
                new UserPosts()
        );
    }
}
