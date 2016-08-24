package com.tagnsearch.services;

import com.tagnsearch.entities.User;
import com.tagnsearch.repositories.standard.UserRepository;
import com.tagnsearch.utils.AuthUtils;
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
    private SequenceService sequenceService;

    public User create(User user) {
        if ( !checkIfUserIdEmtpy(user) ) {
            throw new IllegalArgumentException("ID must be always null here!");
        }
        if ( !checkIfUsernameAlreadyExist(user) ) {
            throw new IllegalArgumentException("Username must be unique");
        }
        user.setPassword(AuthUtils.encryptPassword(user.getPassword()));
        user.setId(sequenceService.getNextSequence(User.class));
        return userRepository.save(user);
    }

    public User update(User user) {
        if ( checkIfUserIdEmtpy(user) ) {
            throw new IllegalArgumentException("ID must be always set here!");
        }
        return userRepository.save(user);
    }

    public List<User> findAll() {
        final PageImpl<User> all = (PageImpl<User>) userRepository.findAll();
        return all.getContent();
    }

    public User findById(Long userId) {
        return userRepository.findOne(userId);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private boolean checkIfUsernameAlreadyExist(final User user) {
        return findByUsername(user.getUsername()) == null;
    }

    private boolean checkIfUserIdEmtpy(final User user) {
        return user.getId() == null;
    }
}
