package com.devops.authentication.user.service;

import com.devops.authentication.user.dto.request.RegisterRequest;
import com.devops.authentication.user.dto.response.UserResponse;

public interface UserService {

    UserResponse registerUser(RegisterRequest request);

    UserResponse getCurrentUser(String email);

}