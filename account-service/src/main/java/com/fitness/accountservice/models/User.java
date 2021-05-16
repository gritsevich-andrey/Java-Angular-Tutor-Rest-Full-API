package com.fitness.accountservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(hash, user.hash) && Objects.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, hash, salt);
    }
}
