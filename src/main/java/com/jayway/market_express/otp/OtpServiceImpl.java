package com.jayway.market_express.otp;

import com.jayway.market_express.common.enums.EntityStatusType;
import com.jayway.market_express.common.exception.GenericClientException;
import com.jayway.market_express.common.util.StringUtil;
import com.jayway.market_express.twilio.TwilioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static com.jayway.market_express.common.constant.ErrorCodeConstant.*;
import static com.jayway.market_express.common.constant.MessageConstant.*;
import static com.jayway.market_express.common.util.DateUtil.getLocalDateTime;
import static com.jayway.market_express.otp.OtpConstant.EXPIRATION_TIME;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final OtpRepository otpRepository;
    private final TwilioRepository twilioRepository;

    @Override
    public void generateOtp(String cellphone) {
        Optional<OtpEntity> otpFounded = otpRepository.findByCellphoneAndStatus(cellphone, EntityStatusType.ACTIVE.getCode());
        otpFounded.filter(otp -> ChronoUnit.MINUTES.between(otp.getRegistrationDate(), getLocalDateTime()) <= EXPIRATION_TIME)
                .ifPresent(otp -> {
                    throw GenericClientException.create(OTP_NOT_EXPIRED, OTP_NOT_EXPIRED_MESSAGE, HttpStatus.CONFLICT);
                });
        otpFounded.filter(otp -> ChronoUnit.MINUTES.between(otp.getRegistrationDate(), getLocalDateTime()) > EXPIRATION_TIME)
                .ifPresent(otp -> {
                    otp = otp.updateStatus(EntityStatusType.EXPIRED.getCode());
                    otpRepository.save(otp);
                });
        // TODO: Cuando sea pase a marcha blanca se activará
        // String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        String otp = "111111";
        OtpEntity otpEntity = OtpEntity.create(cellphone, otp);
        otpRepository.save(otpEntity);
        String otpMessage = StringUtil.buildConstantMessageFromText(otp, OTP_MESSAGE);
        // TODO: Cuando sea pase a marcha blanca se activará
        //twilioRepository.sendSms(cellphone, otpMessage);
    }

    @Override
    public void ensureValidOtp(String cellphone, String code) {
        OtpEntity otpFounded = otpRepository.findByCellphoneAndCodeAndStatus(cellphone, code, EntityStatusType.ACTIVE.getCode())
                .orElseThrow(() -> {
                    String message = StringUtil.buildConstantMessageFromText(code, NOT_FOUND_MESSAGE);
                    return GenericClientException.create(OTP_NOT_FOUND, message, HttpStatus.CONFLICT);
                });
        Optional<OtpEntity> otpEntityOptional = Optional.of(otpFounded);
        otpEntityOptional.filter(otp -> ChronoUnit.MINUTES.between(otp.getRegistrationDate(), getLocalDateTime()) > EXPIRATION_TIME)
                .ifPresent(otp -> {
                    throw GenericClientException.create(OTP_EXPIRED, OTP_EXPIRED_MESSAGE, HttpStatus.UNAUTHORIZED);
                });
        OtpEntity otpUsed = otpEntityOptional
                .map(otp -> otp.updateStatus(EntityStatusType.USED.getCode()))
                .orElseThrow(() -> {
                    String message = StringUtil.buildConstantMessageFromText(code, USED_EXISTS_MESSAGE);
                    return GenericClientException.create(OTP_USED, message, HttpStatus.CONFLICT);
                });
        otpRepository.save(otpUsed);
    }
}
