package com.tagnsearch.services;

import com.tagnsearch.entities.User;
import com.tagnsearch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JS on 8/20/16.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceService userSequenceService;

    public User create(User user) {
        if ( user.getId() != null ) {
            throw new IllegalArgumentException("ID must be always null here!");
        }
        return userRepository.save(user);
    }

    public User update(User user) {
        if ( user.getId() == null ) {
            throw new IllegalArgumentException("ID must be always set here!");
        }
        return userRepository.save(user);
    }


    public List<User> findAll() {
        final long nextSequence = userSequenceService.getNextSequence(User.class);
        final PageImpl<User> all = (PageImpl<User>) userRepository.findAll();
        return all.getContent();
    }

    public User findById(Long userId) {
        return userRepository.findOne(userId);
    }
}
