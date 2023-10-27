package com.project.InstaBackend.service;

import com.project.InstaBackend.model.Post;
import com.project.InstaBackend.model.User;
import com.project.InstaBackend.repo.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    IPostRepo postRepo;

    public void createPost(Post post) {
        postRepo.save(post);

    }

    public Post findPost(Integer postId) {
       return postRepo.findById(postId).orElseThrow();
    }

    public void removePost(Post post) {
        postRepo.delete(post);
    }
}
