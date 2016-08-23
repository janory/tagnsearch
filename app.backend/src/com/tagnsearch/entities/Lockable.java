package com.tagnsearch.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by JS on 8/22/16.
 */

// TODO make these things sync when we are working on the stack, but not the whole methods, but by object
public abstract class Lockable<T> {

    private static final Map<String, Stack<Thread>> LOCKED_OBJECTS = new HashMap<>();

    public void lock(Object id) {
        checkIfIdNull(id);
        final Thread currentThread = Thread.currentThread();
        Stack<Thread> threads = LOCKED_OBJECTS.get(id.toString());
        if ( threads == null ) {
            threads = new Stack<Thread>();
        }
        threads.push(currentThread);
        if ( !threads.isEmpty() ) {
            try {
                currentThread.wait();
            } catch (InterruptedException e) {
                throw new InternalError(e);
            }
        }
    }

    public void unlock(Object id) {
        checkIfIdNull(id);
        final Thread currentThread = Thread.currentThread();
        Stack<Thread> threads = LOCKED_OBJECTS.get(id.toString());
        if ( threads == null ) {
            threads = new Stack<Thread>();
        }
        threads.remove(currentThread);
        final Thread nexThread = threads.firstElement();
        nexThread.notify();
    }

    public void isLocked(Object id) {}

    private void checkIfIdNull(final Object id) {
        if ( id == null ) {
            throw new IllegalArgumentException("ID must be set!");
        }
    }

}
