package com.tagnsearch.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by JS on 8/19/16.
 */

@Document(indexName = "tagnsearch", type = "users", indexStoreType = "memory", shards = 1, replicas = 0, refreshInterval = "-1")
public class User {

    public enum RolesEnum {ADMIN, USER}

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = RolesEnum.valueOf(role).toString();
    }
}
