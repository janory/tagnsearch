package com.tagnsearch.services;

import com.tagnsearch.entities.Sequence;
import com.tagnsearch.repositories.SequenceRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

/**
 * Created by JS on 8/20/16.
 */

@Service
public class SequenceService {

    @Autowired
    private SequenceRepoistory sequenceRepoistory;

    // TODO make this thread safe
    public long getNextSequence(final Class<?> requiredType) {
        if ( !checkIfElasticDocument(requiredType) ) {
            throw new IllegalArgumentException(requiredType.getName() + " class is not an Elastic Document");
        }
        final String sequenceName = requiredType.getSimpleName().toLowerCase();
        final Sequence sequence = sequenceRepoistory.findOne(sequenceName);
        if ( sequence == null ) {
            return createNewSequence(sequenceName);
        }
        final long prevValue = sequence.getValue();
        sequence.setValue(prevValue+1);
        return sequenceRepoistory.save(sequence).getValue();
    }

    private boolean checkIfElasticDocument(final Class<?> requiredType) {
        final Annotation[] declaredAnnotations = requiredType.getDeclaredAnnotations();
        for (Annotation eachAnnotation : declaredAnnotations) {
            if ("Document".equals(eachAnnotation.annotationType().getSimpleName()) ) {
                return true;
            }
        }
        return false;
    }

    private long createNewSequence(final String sequenceName) {
        final Sequence newSequence = new Sequence(sequenceName, 1);
        final Sequence savedSequence = sequenceRepoistory.save(newSequence);
        return savedSequence.getValue();
    }
}
