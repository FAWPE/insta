package com.project.InstaBackend.service;

import com.project.InstaBackend.model.Like;
import com.project.InstaBackend.model.Post;
import com.project.InstaBackend.model.User;
import com.project.InstaBackend.repo.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    ILikeRepo likeRepo;

    public void likePost(Like like) {
        likeRepo.save(like);
    }


    public boolean notAlreadyLiked(User user, Post post) {
        List<Like> likes = likeRepo.findByInstaPostAndLiker(post,user);
        return likes==null || likes.size()==0;
    }

    public String removeLikes(Post post, User user) {
        List<Like> likes = likeRepo.findByInstaPostAndLiker(post,user);
        if(likes.size()!=0) {
            likeRepo.deleteAll(likes);
            return "unliked";
        }
        return "Not liked yet";
    }
}
