package com.jayway.market_express.user.service;

import com.jayway.market_express.user.dto.CreateUserRequest;
import com.jayway.market_express.user.dto.CreateUserResponse;

public interface UserService {
    CreateUserResponse registerUser(CreateUserRequest request);
}
