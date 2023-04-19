package org.forum.internal.permissions;

public record PermissionKeywords() {
    //region Admin Permissions
    public static final String ADMIN_FULL_PRIVILEGES = "fullAdminPrivileges";
    //TODO: kui aega ja jaksu üle jääb, siis ülejäänud lubade teema ka sisse.
    // hetkel ainult see, mis algses plaanis kirja sai ehk "on admin või ei ole?"
    //public static final String ADMIN_BAN_FORUM = "allowBanForum";
    //public static final String ADMIN_BAN_USER = "allowBanUser";
    //public static final String ADMIN_MODIFY_PERMISSIONS = "allowModifyUserPermissions";
    //public static final String ADMIN_MUTE = "allowMuteUser";
    //public static final String ADMIN_WARN = "allowWarnUser";
    //public static final String ADMIN_REMOVE_POSTS = "allowUniversalRemovePosts";
    //public static final String ADMIN_USE_EDIT = "allowUniversalEditPost";
    //endregion


    //region User Permissions

    //region Actions
    //public static final String USER_USE_JS = "isAllowedToUseJavascript";
    //public static final String USER_USE_HTML = "isAllowedToUseHTML";
    //public static final String USER_USE_FORMAT = "isAllowedToUseFormatting";
    //public static final String USER_USE_EDIT = "isAllowedToEditSelfPosts";
    //public static final String USER_USE_COMMENT = "isAllowedToComment";
    //public static final String USER_USE_VOTE = "isAllowedToVote";
    //public static final String USER_DELETE_POST = "isAllowedToDeleteSelfPosts";
    //endregion

    //region Posting
    //public static final String USER_POST_GENERIC = "isAllowedToPost";
    //public static final String USER_POST_EXE = "isAllowedToPostExecutables";
    //public static final String USER_POST_MEDIA = "isAllowedToPostMedia";
    //public static final String USER_POST_FILES = "isAllowedToPostFiles";
    //public static final String USER_POST_LINKS = "isAllowedToPostLinks";
    //endregion

    //region Miscellaneous
    //public static final String USER_CREATE_FORUM = "isAllowedToCreateForum";
    //public static final String USER_SEE_NSFW = "isAllowedToSeeNSFW";
    //endregion


    //endregion
}
