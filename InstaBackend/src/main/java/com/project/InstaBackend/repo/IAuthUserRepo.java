package com.project.InstaBackend.repo;

import com.project.InstaBackend.model.Authentication.AuthTokenUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthUserRepo extends JpaRepository<AuthTokenUser, Integer> {

    AuthTokenUser findByTokenValue(String tokenValue);
}
