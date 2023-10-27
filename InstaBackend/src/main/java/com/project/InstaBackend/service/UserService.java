package com.project.InstaBackend.service;

import com.project.InstaBackend.model.Authentication.AuthTokenUser;
import com.project.InstaBackend.model.Comment;
import com.project.InstaBackend.model.Like;
import com.project.InstaBackend.model.Post;
import com.project.InstaBackend.model.User;
import com.project.InstaBackend.repo.IAuthUserRepo;
import com.project.InstaBackend.repo.IUserRepo;
import com.project.InstaBackend.service.passwordEncryptor.PasswordEncyptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    CommentService commentService;
    
    @Autowired
    IUserRepo userRepo;

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    public String signUpUser(User user) {
        String email= user.getUserEmail();
        User user1=userRepo.findFirstByUserEmail(email);
        if(user1!=null){
            return "user with the email already exists";
        }
        String password= user.getUserPassword();
        try {
            String encPass= PasswordEncyptor.encrypt(password);
            user.setUserPassword(encPass);
            userRepo.save(user);
            return "signup successfully! please login";
        }catch (NoSuchAlgorithmException ex){
            return "Internal Server Error ! Please Try After sometime";
        }
    }

    public String signInUser(String email, String password) {
        User user = userRepo.findFirstByUserEmail(email);
        if (user==null){
            return "Not a valid email";
        }
        String pass=user.getUserPassword();
        try{
            String encpass= PasswordEncyptor.encrypt(password);
            if (encpass.equals(pass)){
                AuthTokenUser tokenUser = new AuthTokenUser(user);
               userAuthService.createToken(tokenUser);
                return "Login completed and this is token: "+ tokenUser;
            }
            return "Invalid credentials";

        }catch (NoSuchAlgorithmException ex){
            return " Failed Try again latter";
        }
    }

    public String userSignOut(String email, String tokenValue) {
        if (userAuthService.authenticate(email,tokenValue)){
            userAuthService.removeToken(tokenValue);
            return "signout successfuly";
        }
        return "Unauthenticated access";
    }

    public String createPost(String email, String token, Post post) {
        if (userAuthService.authenticate(email,token)){
            User user = userRepo.findFirstByUserEmail(email);
            post.setPostOwner(user);
            post.setPostCreatedTimeStamp(LocalDateTime.now());
            postService.createPost(post);
            return "Posted";
        }
        return "Unauthenticated access";
    }

    public String likePost(String email, String tokenValue, Integer postId) {
        if (userAuthService.authenticate(email,tokenValue)){
            User user = userRepo.findFirstByUserEmail(email);
            Post post= postService.findPost(postId);
            if(likeService.notAlreadyLiked(user,post)){
                Like like = new Like(null,post,user);
                likeService.likePost(like);

            }
            return "Liked";

        }
        return "Can't like the post";
    }

    public String removeLike(String email, String tokenValue, Integer postId) {
        if(userAuthService.authenticate(email,tokenValue)){
            Post post = postService.findPost(postId);
            User user= userRepo.findFirstByUserEmail(email);

          return   likeService.removeLikes(post,user);

        }
        return "Unauthenticated access";
    }

    public String deletePost(String email, String tokenValue, Integer postId) {
        if(userAuthService.authenticate(email, tokenValue)){
            Post post = postService.findPost(postId);

            if(post.getPostOwner().getUserEmail().equals(email)){
                postService.removePost(post);
                return "Deleted";
            }
            return "Unauthorised access";
        }
        return "Unauthenticated access";
    }

    public String commentPost(Integer postId, String commentBody, String email, String tolemValue) {
        if(userAuthService.authenticate(email,tolemValue)){
            Post post = postService.findPost(postId);
            User user= userRepo.findFirstByUserEmail(email);
            Comment comment = new Comment(null,commentBody,LocalDateTime.now(),post,user);
            commentService.doComment(comment);
            return "commented";
        }
        return "Unauthenticated access";
    }
}
