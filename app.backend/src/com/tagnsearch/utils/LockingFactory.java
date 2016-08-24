package com.tagnsearch.utils;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by JS on 8/24/16.
 */
public class LockingFactory {
    private final ReentrantLock lockSynchronizer;
    private final ReentrantLock unlockSynchronizer;
    private final Map<Object, Queue<Object>> lockedObjects;


    public LockingFactory() {
        lockSynchronizer = new ReentrantLock(true);
        unlockSynchronizer = new ReentrantLock(true);
        lockedObjects = new HashMap<>();
    }

    public final void lock(Object id) {
        lockSynchronizer.lock();
        try {
            final boolean isAlreadyLocked = lockedObjects.containsKey(id);
            if (isAlreadyLocked) {
                try {
                    final Queue<Object> threads = lockedObjects.get(id);
                    final Object lockedThread = new Object();
                    threads.add(lockedThread);
                    lockSynchronizer.unlock();
                    synchronized (lockedThread) {
                        lockedThread.wait();
                    }
                } catch (InterruptedException e) {
                    throw new InternalError(e);
                }
            } else {
                lockedObjects.put(id, new LinkedList<>(Collections.singletonList(new Object())));
            }
        } finally {
            if ( lockSynchronizer.isLocked() ) {
                lockSynchronizer.unlock();
            }
        }
    }

    public final void unlock(Object id) {
        unlockSynchronizer.lock();
        try {
            final Queue<Object> threads = lockedObjects.get(id);
            threads.poll();
            if (!threads.isEmpty()) {
                final Object threadToRelease = threads.peek();
                synchronized (threadToRelease) {
                    threadToRelease.notifyAll();
                }
            }
        } finally {
            unlockSynchronizer.unlock();
        }
    }

}
