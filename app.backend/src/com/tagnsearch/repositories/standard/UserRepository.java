package com.tagnsearch.repositories.standard;


import com.tagnsearch.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by JS on 8/19/16.
 */

public interface UserRepository extends ElasticsearchRepository<User, Long> {
    User findByUsername(String username);
}
