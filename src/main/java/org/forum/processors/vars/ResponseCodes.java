package org.forum.processors.vars;

public record ResponseCodes() {
    public static final int RESPONSE_PING = 2003;

    public static final int RESPONSE_OK = 0;
    public static final int RESPONSE_FAILED = 1;
    public static final int RESPONSE_RESTRICTED = 2;
    public static final int RESPONSE_SOFTFAIL = 3;
}
