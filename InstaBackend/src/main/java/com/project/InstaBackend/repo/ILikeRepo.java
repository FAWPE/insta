package com.project.InstaBackend.repo;

import com.project.InstaBackend.model.Like;
import com.project.InstaBackend.model.Post;
import com.project.InstaBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILikeRepo extends JpaRepository<Like, Integer> {

    List<Like> findByInstaPostAndLiker(Post post, User user);
}
