package com.project.InstaBackend.service;

import com.project.InstaBackend.model.Authentication.AuthTokenUser;
import com.project.InstaBackend.model.Post;
import com.project.InstaBackend.model.User;
import com.project.InstaBackend.repo.IAuthUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    @Autowired
    IAuthUserRepo authUserRepo;
    public void createToken(AuthTokenUser tokenUser) {
        authUserRepo.save(tokenUser);
    }

    public boolean authenticate(String email, String tokenValue) {
        AuthTokenUser token = authUserRepo.findByTokenValue(tokenValue);
        if(token!=null){
            return token.getUser().getUserEmail().equals(email);
        }
        return false;
    }

    public void removeToken(String tokenValue) {
        AuthTokenUser token = authUserRepo.findByTokenValue(tokenValue);
        authUserRepo.delete(token);
    }

    public boolean authenticatePost(String email, Post post) {
        return post.getPostOwner().getUserEmail().equals(email);
    }
}
