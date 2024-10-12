package com.jayway.market_express.login;

import com.jayway.market_express.common.enums.EntityStatusType;
import com.jayway.market_express.common.exception.GenericClientException;
import com.jayway.market_express.common.util.JwtPayload;
import com.jayway.market_express.common.util.JwtUtil;
import com.jayway.market_express.common.util.StringUtil;
import com.jayway.market_express.otp.OtpService;
import com.jayway.market_express.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.jayway.market_express.common.constant.MessageConstant.EMPTY;
import static com.jayway.market_express.common.constant.MessageConstant.NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final OtpService otpService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public LoginOtpResponse loginWithOtp(LoginOtpRequest request) {
        otpService.ensureValidOtp(request.getCellphone(), request.getOtp());
        return userRepository.findByCellphoneAndStatus(request.getCellphone(), EntityStatusType.ACTIVE.getCode())
                .map(user -> {
                    JwtPayload jwtPayload = JwtPayload.create(user.getCellphone(), user.getRole());
                    String token = jwtUtil.generateToken(jwtPayload);
                    return LoginOtpResponse.create(user.getFullName(), EMPTY, user.getRole(), token, token);
                })
                .orElseThrow( () -> {
                    String message = StringUtil.buildConstantMessageFromText(request.getCellphone(), NOT_FOUND_MESSAGE);
                    return GenericClientException.create(message, HttpStatus.UNPROCESSABLE_ENTITY);
                });
    }
}
