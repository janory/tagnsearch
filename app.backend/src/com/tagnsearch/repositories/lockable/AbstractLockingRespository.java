package com.tagnsearch.repositories.lockable;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by JS on 8/24/16.
 */
public abstract class AbstractLockingRespository {

    private final ReentrantLock lockSynchronizer;
    private final ReentrantLock unlockSynchronizer;
    private final Map<Object, Queue<Object>> lockedObjects;

    public AbstractLockingRespository() {
        lockSynchronizer = new ReentrantLock(true);
        unlockSynchronizer = new ReentrantLock(true);
        lockedObjects = new HashMap<>();
    }

    protected final void lock(Object id) {
        lockSynchronizer.lock();
        try {
            Queue<Object> threads = lockedObjects.get(id);
            if (threads != null && !threads.isEmpty()) {
                try {
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

    protected final void unlock(Object id) {
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
