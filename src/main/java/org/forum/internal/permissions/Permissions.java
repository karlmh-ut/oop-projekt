package org.forum.internal.permissions;

import org.forum.entities.Users;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Contemplating going over to exceptions instead of simple booleans
// so making difference between problems with permissions and other exceptions would be smoother
public class Permissions {
    static final String path = "src/main/java/org/forum/internal/permissions/permissions.txt";

    /**
     * Gets default permissions.txt, which are also set for a new account
     * @return HashMap with permissions.txt as keys and booleans as if they are enabled or not
     * @throws IOException if something goes wrong
     */
    public static HashMap<String, Boolean> setDefaultPermissions() throws IOException {
        HashMap<String, Boolean> defaults = new HashMap<>();
        Files.readAllLines(Path.of(path)).stream()
                .filter(s -> !s.contains("#"))
                .forEach(s -> {
                    String[] ss = s.split("=");
                    defaults.put(ss[0], Boolean.valueOf(ss[1]));
                });
        return defaults;
    }

    public static Map<String, Boolean> setNonDefaultPermissions(Map<String, Boolean> defaultPermissions, String permissions) {
        String[][] perms = Arrays.stream(permissions.split(","))
                .map(g -> g.split("="))
                .toArray(String[][]::new);
        for (String[] perm : perms) defaultPermissions.replace(perm[0], Boolean.valueOf(perm[1]));
        return defaultPermissions;
    }

    /**
     * Checks if user has the required permission
     * @param users User whose permissions.txt are checked
     * @param permToCheck Permission for which to check for
     * @return true if user has the permission, false otherwise
     * @throws IllegalArgumentException if the permission key does not exist on the user's permissions.txt list
     */
    public static boolean checkForPermission(Users users, String permToCheck) {
        Map<String, Boolean> perms = users.getPermissions();
        if (perms.get(permToCheck) == null) throw new IllegalArgumentException("Permission is not valid permission");
        return perms.get(permToCheck);
    }

    /**
     * Checks if user has all required permissions.txt
     * @param users User whose permissions.txt are checked
     * @param permsToCheck Permissions for which to check for
     * @return true if user has all permissions.txt, false otherwise
     * @throws IllegalArgumentException if the permission key does not exist on the user's permissions.txt list
     */
    public static boolean checkForPermissionsAND(Users users, String... permsToCheck) {
        Map<String, Boolean> perms = users.getPermissions();
        for (String s : permsToCheck) {
            if (perms.get(s) == null) throw new IllegalArgumentException("Permission is not valid permission");
            if (!perms.get(s)) return false;
        }
        return true;
    }

    /**
     * Checks if user has at least one of the required permissions.txt
     * @param users  User whose permissions.txt are checked
     * @param permsToCheck Permissions for which to check for
     * @return true if user has at least one of the permissions.txt, false otherwise
     * @throws IllegalArgumentException if the permission key does not exist on the user's permissions.txt list
     */
    public static boolean checkForPermissionsOR(Users users, String... permsToCheck) {
        Map<String, Boolean> perms = users.getPermissions();
        for (String s : permsToCheck) {
            if (perms.get(s) == null) throw new IllegalArgumentException("Permission is not valid permission");
            if (perms.get(s)) return true;
        }
        return false;
    }
}
