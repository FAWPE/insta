package com.project.InstaBackend.controller;

import com.project.InstaBackend.model.Post;
import com.project.InstaBackend.model.User;

import com.project.InstaBackend.service.PostService;
import com.project.InstaBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    //SIGNUP
    @PostMapping("signup/user/")
    public String signUpuser(@RequestBody User user){
            return  userService.signUpUser(user);
    }
    //signin
    @PostMapping("user/signin")
    public String signInUser(@RequestParam String email, @RequestParam String password){
        return userService.signInUser(email,password);
    }

    //signOut
    @DeleteMapping("user/signout")
    public String userSignOut(@RequestParam String email, @RequestParam String tokenValue) {
        return userService.userSignOut(email, tokenValue);
    }

    @PostMapping("create/post")
    public String createPost(@RequestParam String email, @RequestParam String token, @RequestBody Post post){
        return userService.createPost(email,token,post);
    }
    @PostMapping("like/post")
    public String likePost( @RequestParam String email,@RequestParam String tokenValue, @RequestParam Integer postId){
        return userService.likePost(email,tokenValue,postId);
    }
    @DeleteMapping("remove/like/{postId}")
    public String removeLike(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId){
        return userService.removeLike(email,tokenValue,postId);
    }

    @DeleteMapping("delete/post")
    public String deletePost(@RequestParam String email, @RequestParam String tokenValue, @RequestParam Integer postId){
        return userService.deletePost(email,tokenValue,postId);
    }

    @PostMapping("comment/{postId}")
    public String commentPost(@PathVariable Integer postId, @RequestParam String commentBody, @RequestParam String email, @RequestParam String tolemValue){
        return userService.commentPost(postId,commentBody,email,tolemValue);
    }

    }
