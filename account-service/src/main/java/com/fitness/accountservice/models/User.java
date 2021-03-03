package com.fitness.accountservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String userId;
    private String email;
    private String lessonId;
    private String hash;
    private String salt;
    private String secretKey;

    //    @DBRef(lazy = true)
    private List<Role> roles;

    public User(String userId, String email, String hash, String salt, String secret, List<Role> roles) {
        this.userId = userId;
        this.email = email;
        this.hash = hash;
        this.salt = salt;
        this.secretKey = secret;
        this.roles = roles;
    }


    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
