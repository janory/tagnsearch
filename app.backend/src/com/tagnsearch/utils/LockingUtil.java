package com.tagnsearch.utils;

import java.util.*;

/**
 * Created by JS on 8/22/16.
 */

public final class LockingUtil {

    private static final Map<String, Queue<Object>> LOCKED_OBJECTS = new HashMap<>();

    public static void lock(final Object id, final Class type) {
        checkIfIdOrClassNull(id, type);
        Queue<Object> threads = LOCKED_OBJECTS.get(createUniqueId(id, type));
        if (threads == null) {
            threads = new LinkedList<Object>();
            LOCKED_OBJECTS.put(createUniqueId(id, type), threads);
        }
        synchronized (threads) {
            if (!threads.isEmpty()) {
                try {
                    final Object lockedThread = new Object();
                    threads.add(lockedThread);
                    synchronized (lockedThread) {
                        lockedThread.wait();
                    }
                } catch (InterruptedException e) {
                    throw new InternalError(e);
                }
            } else {
                threads.add(new Object());
            }
        }
    }

    public static void unlock(final Object id, final Class type) {
        checkIfIdOrClassNull(id, type);
        Queue<Object> threads = LOCKED_OBJECTS.get(createUniqueId(id, type));
        synchronized (threads) {
            threads.poll();
            if (!threads.isEmpty()) {
                final Object threadToRelease = threads.peek();
                synchronized (threadToRelease) {
                    threadToRelease.notifyAll();
                }
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
