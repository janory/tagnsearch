package com.tagnsearch.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by JS on 8/22/16.
 */

// TODO make these things sync when we are working on the stack, but not the whole methods, but by object
public final class LockingUtil {

    private static final Map<String, Stack<Object>> LOCKED_OBJECTS = new HashMap<>();

    public static void lock(final Object id, final Class type) {
        checkIfIdOrClassNull(id, type);
        Stack<Object> threads = LOCKED_OBJECTS.get(createUniqueId(id, type));
        if ( threads == null ) {
            threads = new Stack<Object>();
            LOCKED_OBJECTS.put(createUniqueId(id, type), threads);
        }
        if ( !threads.isEmpty() ) {
            try {
                final Object lockedThread = new Object();
                threads.push(lockedThread);
                synchronized (lockedThread) {
                    lockedThread.wait();
                }
            } catch (InterruptedException e) {
                throw new InternalError(e);
            }
        } else {
            threads.push(new Object());
        }
    }

    public static void unlock(final Object id, final Class type) {
        checkIfIdOrClassNull(id, type);
        Stack<Object> threads = LOCKED_OBJECTS.get(createUniqueId(id, type));
        threads.remove(threads.firstElement());
        if ( !threads.isEmpty() ) {
            final Object threadToRelease = threads.firstElement();
            synchronized (threadToRelease) {
                threadToRelease.notifyAll();
            }
        }
    }

    public boolean isLocked(final Object id, final Class type) {
        return LOCKED_OBJECTS.get(createUniqueId(id, type)) != null;
    }

    private static String createUniqueId(final Object id, final Class type) {
        final String lockType = type.getSimpleName().toLowerCase();
        return lockType + "#" + id.toString();
    }

    private static void checkIfIdOrClassNull(final Object id, final Class type) {
        if ( id == null ) {
            throw new IllegalArgumentException("ID must be set!");
        }
        if ( type == null ) {
            throw new IllegalArgumentException("Class must be set!");
        }
    }

    private LockingUtil() {
        throw new AssertionError();
    }
}
