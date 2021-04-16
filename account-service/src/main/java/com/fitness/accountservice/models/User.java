package com.fitness.accountservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User extends AbstractDocument<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String userId;
    @Indexed(unique = true)
    private String email;
    private String lessonId;
    private String hash;
    private String salt;
    private String secretKey;

    //    @DBRef(lazy = true)
    private List<Role> roles = new ArrayList<>();

    private UserProfile profile;

    public User(String userId, @NotNull String email, String hash, String salt, String secret, List<Role> roles, UserProfile profile) {
        this.userId = userId;
        this.email = email;
        this.hash = hash;
        this.salt = salt;
        this.secretKey = secret;
        this.roles = roles;
        this.profile = profile;
    }


    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public String getId() {
        return this.userId;
    }
}
