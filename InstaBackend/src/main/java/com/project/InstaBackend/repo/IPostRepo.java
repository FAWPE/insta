package com.project.InstaBackend.repo;

import com.project.InstaBackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Integer> {

}
