package com.project.InstaBackend.service;

import com.project.InstaBackend.model.Admin;
import com.project.InstaBackend.model.Authentication.AuthTokenAdmin;
import com.project.InstaBackend.repo.IAdminRepo;
import com.project.InstaBackend.service.passwordEncryptor.PasswordEncyptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {
    @Autowired
    IAdminRepo adminRepo;
    @Autowired
    AdminAuthService adminAuthService;



    public String signUpAdmin(Admin admin) {
        String email= admin.getAdminEmail();
        Admin admin1=adminRepo.findFirstByAdminEmail(email);
        if(admin1!=null){
            return "Admin with the email already exists";
        }
        String password= admin.getAdminPassword();
        try {
            String encPass= PasswordEncyptor.encrypt(password);
            admin.setAdminPassword(encPass);
            adminRepo.save(admin);
            return "signup successfully! please login";
        }catch (NoSuchAlgorithmException ex){
            return "Internal Server Error ! Please Try After sometime";
        }
    }

    public String signInAdmin(String email, String password) {
        Admin admin = adminRepo.findFirstByAdminEmail(email);
    if (admin==null){
        return "Not a valid email";
    }
        String pass=admin.getAdminPassword();
    try{
        String encpass= PasswordEncyptor.encrypt(password);
        if (encpass.equals(pass)){
            AuthTokenAdmin tokenAdmin = new AuthTokenAdmin(admin);
            adminAuthService.createToken(tokenAdmin);
            return "Login completed";
        }
        return "Invalid credentials";

    }catch (NoSuchAlgorithmException ex){
        return " Failed Try again latter";
    }
    }

    public String adminSignOut(String email, String tokenValue) {
        if (adminAuthService.authenticate(email,tokenValue)){
            adminAuthService.removeToken(tokenValue);
            return "signout successfuly";
        }
        return "Unauthenticated access";
    }
}
