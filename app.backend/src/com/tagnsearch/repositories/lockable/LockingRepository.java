package com.tagnsearch.repositories.lockable;

/**
 * Created by JS on 8/24/16.
 */
public interface LockingRepository<T, ID> {
    T lockById(ID id);
    void unlockById(String id);
}
