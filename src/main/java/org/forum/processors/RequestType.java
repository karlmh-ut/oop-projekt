package org.forum.processors;

public record RequestType() {
    //region Basic requests
    public static final int REQUEST_END = 0;
    public static final int REQUEST_PING = 1;
    public static final int REQUEST_AUTHENTICATE = 2;
    //endregion

    //region Content requests
    //region Get content
    public static final int REQUEST_FORUM_LIST = 100;
    public static final int REQUEST_FORUM_CONTENT = 101;
    public static final int REQUEST_POST_CONTENT = 102;
    //endregion
    //region Likes
    public static final int REQUEST_ADD_LIKE = 200;
    public static final int REQUEST_REMOVE_LIKE = 201;
    //endregion
    //region Comments
    public static final int REQUEST_ADD_COMMENT = 600;
    public static final int REQUEST_REMOVE_COMMENT = 601;
    public static final int REQUEST_EDIT_COMMENT = 602;
    //endregion
    //region Posts
    public static final int REQUEST_ADD_POST = 300;
    public static final int REQUEST_REMOVE_POST = 301;
    public static final int REQUEST_EDIT_POST = 302;
    //endregion
    //region Users
    public static final int REQUEST_USER_DATA = 400;
    //endregion
    //endregion

    //region Administrative requests
    public static final int REQUEST_MUTE_USER = 500;
    public static final int REQUEST_BAN_USER = 501;
    //endregion
}
