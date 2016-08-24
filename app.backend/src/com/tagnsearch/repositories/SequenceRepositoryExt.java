package com.tagnsearch.repositories;

import com.tagnsearch.entities.Sequence;
import com.tagnsearch.repositories.standard.SequenceRepoistory;
import com.tagnsearch.repositories.lockable.AbstractLockingRespository;
import com.tagnsearch.repositories.lockable.LockingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by JS on 8/24/16.
 */

@Repository
public class SequenceRepositoryExt extends AbstractLockingRespository implements LockingRepository<Sequence, String> {

    @Autowired
    private SequenceRepoistory sequenceRepoistory;

    public Sequence lockById(final String id) {
        lock(id);
        return sequenceRepoistory.findOne(id);
    }

    public void unlockById(final String name) {
       unlock(name);
    }

    public Sequence save(Sequence entity) {
        return sequenceRepoistory.save(entity);
    }
}
