package com.fitness.accountservice.models.payload;

import com.fitness.accountservice.models.Role;
import com.fitness.accountservice.models.UserProfile;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class SignupRequest {
    private String userId;
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @Valid
    private String email;
    @NotEmpty(message = "Please, enter password")
    private String password;
    private String lessonId;
    private List<Role> roles;
    private UserProfile profile;
}
