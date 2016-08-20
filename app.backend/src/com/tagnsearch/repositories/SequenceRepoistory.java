package com.tagnsearch.repositories;

import com.tagnsearch.entities.Sequence;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by JS on 8/20/16.
 */

@Repository
public interface SequenceRepoistory extends ElasticsearchRepository<Sequence, String> {
}
