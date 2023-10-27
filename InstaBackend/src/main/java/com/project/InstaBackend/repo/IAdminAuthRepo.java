package com.project.InstaBackend.repo;

import com.project.InstaBackend.model.Authentication.AuthTokenAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminAuthRepo extends JpaRepository<AuthTokenAdmin,Integer> {
    AuthTokenAdmin findByTokenValue(String tokenValue);
}
