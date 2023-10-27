package com.project.InstaBackend.model.Authentication;

import com.project.InstaBackend.model.Admin;
import com.project.InstaBackend.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthTokenUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDateTime;
    @OneToOne
    @JoinColumn(name = "fk_userId")
    private User user;
    public AuthTokenUser(User user) {
        this.user=user;
        this.tokenCreationDateTime=LocalDateTime.now();
        this.tokenValue= UUID.randomUUID().toString();
    }
}
