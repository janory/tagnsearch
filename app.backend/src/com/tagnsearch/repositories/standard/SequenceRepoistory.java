package com.tagnsearch.repositories.standard;

import com.tagnsearch.entities.Sequence;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by JS on 8/20/16.
 */

public interface SequenceRepoistory extends ElasticsearchRepository<Sequence, String>{
}
