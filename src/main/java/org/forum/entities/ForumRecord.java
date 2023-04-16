package org.forum.entities;

import org.forum.internal.permissions.PermissionKeywords;
import org.forum.internal.permissions.Permissions;

import java.util.List;

// Do we want to use subforums or not? Because I personally dont see the point in having subforums
public record ForumRecord(List<Subforum> subforums) {

    private boolean hasSubforumByName(String name) {
        for (Subforum s : subforums) {
            if (s.getName().equals(name)) return true;
        }
        return false;
    }

    /**
     * Add new subforum if the acting user is admin,
     * ensuring that the name does not match that of an existing subforum
     */
    public void addSubforum(String name, User user) {
        if (Permissions.checkForPermission(user, PermissionKeywords.USER_CREATE_FORUM)) {
            subforums.add(new Subforum(name));
        }
    }
}
