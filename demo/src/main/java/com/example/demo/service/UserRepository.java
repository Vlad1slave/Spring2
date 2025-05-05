package com.example.demo.service;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    // Имитация базы данных пользователей
    private final Map<String, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
        users.put("admin", new User("admin", "admin123",
                Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE)));
        users.put("user", new User("user", "user123",
                Arrays.asList(Authorities.READ)));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        User foundUser = users.get(user);
        if (foundUser != null && foundUser.getPassword().equals(password)) {
            return foundUser.getAuthorities();
        }
        return Collections.emptyList();
    }

    private static class User {
        private final String username;
        private final String password;
        private final List<Authorities> authorities;

        public User(String username, String password, List<Authorities> authorities) {
            this.username = username;
            this.password = password;
            this.authorities = authorities;
        }

        public String getPassword() {
            return password;
        }

        public List<Authorities> getAuthorities() {
            return authorities;
        }
    }
}