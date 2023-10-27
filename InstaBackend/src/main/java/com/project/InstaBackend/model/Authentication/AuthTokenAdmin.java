package com.project.InstaBackend.model.Authentication;

import com.project.InstaBackend.model.Admin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthTokenAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDateTime;
    @OneToOne
    @JoinColumn(name = "fk_adminId")
    private Admin admin;

    public AuthTokenAdmin(Admin admin) {
        this.admin=admin;
        this.tokenCreationDateTime=LocalDateTime.now();
        this.tokenValue= UUID.randomUUID().toString();
    }
}
