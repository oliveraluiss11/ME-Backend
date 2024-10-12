package com.jayway.market_express.login;

import com.jayway.market_express.common.enums.EntityStatusType;
import com.jayway.market_express.common.util.JwtPayload;
import com.jayway.market_express.common.util.JwtUtil;
import com.jayway.market_express.otp.OtpService;
import com.jayway.market_express.user.UserEntity;
import com.jayway.market_express.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.jayway.market_express.common.constant.MessageConstant.EMPTY;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final OtpService otpService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public LoginOtpResponse loginWithOtp(LoginOtpRequest request) {
        otpService.ensureValidOtp(request.getCellphone(), request.getOtp());
        UserEntity userFounded = userRepository.findByCellphoneAndStatus(request.getCellphone(), EntityStatusType.ACTIVE.getCode())
                .orElseThrow(RuntimeException::new);

        JwtPayload jwtPayload = JwtPayload.create(userFounded.getCellphone(), userFounded.getRole());
        String token = jwtUtil.generateToken(jwtPayload);
        return LoginOtpResponse.create(userFounded.getFullName(), EMPTY, userFounded.getRole(), token, token);
    }
}
