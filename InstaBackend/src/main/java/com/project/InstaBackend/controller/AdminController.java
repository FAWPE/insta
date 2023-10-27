package com.project.InstaBackend.controller;

import com.project.InstaBackend.model.Admin;
import com.project.InstaBackend.service.AdminService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class AdminController {
    @Autowired
    AdminService adminService;


    //SIGNUP
    @PostMapping("signup/admin/tokenid/{id}")
    public String signUpAdmin(@RequestBody Admin admin, @PathVariable String id){
        if(id.equals("adminIsreal")){
             return  adminService.signUpAdmin(admin);
        }
        return "Not a valid token for signup";
    }
    //signin
    @PostMapping("admin/signin")
    public String signInAdmin(@RequestParam String email, @RequestParam String password){
        return adminService.signInAdmin(email,password);
    }

    //signOut
    @DeleteMapping("admin/signout")
    public String adminSignOut(@RequestParam String email, @RequestParam String tokenValue){
        return adminService.adminSignOut(email,tokenValue);

    }
}
