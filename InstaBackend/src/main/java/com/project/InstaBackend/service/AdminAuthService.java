package com.project.InstaBackend.service;

import com.project.InstaBackend.model.Authentication.AuthTokenAdmin;
import com.project.InstaBackend.repo.IAdminAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {
    @Autowired
    IAdminAuthRepo adminAuthRepo;

    public void createToken(AuthTokenAdmin tokenAdmin) {
        adminAuthRepo.save(tokenAdmin);
    }

    public void removeToken(String tokenValue) {
        AuthTokenAdmin token=adminAuthRepo.findByTokenValue(tokenValue);
        adminAuthRepo.delete(token);
    }

    public boolean authenticate(String email, String tokenValue) {
        AuthTokenAdmin token = adminAuthRepo.findByTokenValue(tokenValue);
        if (token!=null){
            return token.getAdmin().getAdminEmail().equals(email);
        }
        return false;
    }
}
