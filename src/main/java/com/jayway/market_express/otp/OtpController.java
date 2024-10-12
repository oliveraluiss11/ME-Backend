package com.jayway.market_express.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jayway.market_express.common.constant.PathConstant.OTP_PATH;

@RestController
@RequestMapping(OTP_PATH)
@RequiredArgsConstructor
public class OtpController {
    private final OtpService otpService;

    @PostMapping
    public ResponseEntity<Void> generateOtp(@RequestBody OtpRequest request) {
        otpService.generateOtp(request.getCellphone());
        return ResponseEntity.ok().build();
    }
}
