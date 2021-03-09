package com.fitness.accountservice.models.payload;

import com.fitness.accountservice.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SignupResponse {

    private String userId;
    private String token;
//    private String secretKey;
//    private List<Role> roles;

}
