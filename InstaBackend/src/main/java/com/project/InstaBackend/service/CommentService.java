package com.project.InstaBackend.service;

import com.project.InstaBackend.model.Comment;
import com.project.InstaBackend.repo.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    ICommentRepo commentRepo;

    public void doComment(Comment comment) {
        commentRepo.save(comment);
    }
}
