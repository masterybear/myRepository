package org.example.treeCase.service;

import org.example.treeCase.model.User;

public interface UserService {

    User queryEmail(String email);
    User signIn(String email, String password);
    User queryName(String name);
    void signUp(User user);

    String queryNameByEmail(String email);
}
