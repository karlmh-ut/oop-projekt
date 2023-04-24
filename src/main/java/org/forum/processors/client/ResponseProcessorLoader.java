package org.forum.processors.client;

import org.forum.processors.client.processors.CollectEcho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseProcessorLoader {
    public static List<ResponseProcessor> load() {
        return Arrays.asList(
                new CollectEcho()
        );
    }
}
