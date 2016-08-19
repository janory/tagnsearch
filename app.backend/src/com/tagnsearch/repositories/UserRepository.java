package com.tagnsearch.repositories;


import com.tagnsearch.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

/**
 * Created by JS on 8/19/16.
 */

@Service
public interface UserRepository extends ElasticsearchRepository<User, String> {
}
