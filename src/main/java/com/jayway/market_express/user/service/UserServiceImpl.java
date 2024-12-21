package com.jayway.market_express.user.service;

import com.jayway.market_express.common.enums.EntityStatusType;
import com.jayway.market_express.common.enums.RoleType;
import com.jayway.market_express.common.exception.GenericClientException;
import com.jayway.market_express.common.util.JwtPayload;
import com.jayway.market_express.common.util.JwtUtil;
import com.jayway.market_express.common.util.StringUtil;
import com.jayway.market_express.otp.OtpService;
import com.jayway.market_express.reniec.dni.DocumentNumberReniecRepository;
import com.jayway.market_express.reniec.dni.DocumentNumberResponse;
import com.jayway.market_express.reniec.dni.DocumentNumberService;
import com.jayway.market_express.user.document.UserAddressDto;
import com.jayway.market_express.user.document.UserEntity;
import com.jayway.market_express.user.dto.CreateUserRequest;
import com.jayway.market_express.user.dto.CreateUserResponse;
import com.jayway.market_express.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.jayway.market_express.common.constant.MessageConstant.ALREADY_EXISTS_MESSAGE;
import static com.jayway.market_express.common.constant.MessageConstant.EMPTY;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OtpService otpService;
    private final JwtUtil jwtUtil;
    private final DocumentNumberReniecRepository documentNumberReniecRepository;
    private final DocumentNumberService documentNumberService;

    @Override
    public CreateUserResponse registerUser(CreateUserRequest request) {
        otpService.ensureValidOtp(request.getCellphone(), request.getOtp());
        DocumentNumberResponse documentNumberResponse = documentNumberService.getDocumentNumberInformation(request.getDocumentNumber());
        String fullName = documentNumberResponse.getData().getFullName().replace(",","");
        fullName = StringUtil.toTitleCase(fullName);
        userRepository.findByCellphoneAndStatus(request.getCellphone(), EntityStatusType.ACTIVE.getCode())
                .ifPresent(user -> {
                    String message = StringUtil.buildConstantMessageFromText(user.getCellphone(), ALREADY_EXISTS_MESSAGE);
                    throw GenericClientException.create(message, HttpStatus.UNPROCESSABLE_ENTITY);
                });
        UserAddressDto address = UserAddressDto.create(request.getAddress(), request.getDistrict(), request.getProvince());
        UserEntity createUser = UserEntity.create(fullName,
                request.getCellphone(),
                request.getDocumentNumber(),
                address,
                request.getBirthDate(),
                RoleType.CUSTOMER.getCode());
        UserEntity userCreated = userRepository.save(createUser);

        JwtPayload jwtPayload = JwtPayload.create(userCreated.getCellphone(), userCreated.getRole());
        String token = jwtUtil.generateToken(jwtPayload);
        return CreateUserResponse.create(userCreated.getFullName(), EMPTY, userCreated.getRole(), token, token);
    }
}
