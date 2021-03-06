package com.tagnsearch.utils;

import com.tagnsearch.entities.User;

/**
 * Created by JS on 8/21/16.
 */
public final class UserUtils {

    public static boolean checkIfUserExists(final User user) {
        return user == null;
    }

    public static boolean checkIfPasswordCorrect(final User user, final String password) {
        return password.equals(user.getPassword());
    }

    public UserUtils() {
        throw new AssertionError();
    }
}
