package com.jayway.market_express.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jayway.market_express.common.constant.PathConstant.USER_PATH;

@RestController
@RequestMapping(USER_PATH)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> registerUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse createUserResponse = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponse);
    }
}
