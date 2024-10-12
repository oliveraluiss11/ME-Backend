package com.jayway.market_express.user;

import com.jayway.market_express.common.enums.EntityStatusType;
import com.jayway.market_express.common.exception.GenericClientException;
import com.jayway.market_express.common.util.JwtPayload;
import com.jayway.market_express.common.util.JwtUtil;
import com.jayway.market_express.common.util.StringUtil;
import com.jayway.market_express.otp.OtpService;
import com.jayway.market_express.reniec.dni.DocumentNumberReniecRepository;
import com.jayway.market_express.reniec.dni.DocumentNumberRequest;
import com.jayway.market_express.reniec.dni.DocumentNumberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.jayway.market_express.common.constant.ErrorCodeConstant.DOCUMENT_NUMBER_NOT_FOUND;
import static com.jayway.market_express.common.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OtpService otpService;
    private final JwtUtil jwtUtil;
    private final DocumentNumberReniecRepository documentNumberReniecRepository;

    @Override
    public CreateUserResponse registerUser(CreateUserRequest request) {
        //otpService.ensureValidOtp(request.getCellphone(), request.getOtp());
        DocumentNumberRequest documentNumberRequest = DocumentNumberRequest.create(request.getDocumentNumber());
        DocumentNumberResponse documentNumberResponse = documentNumberReniecRepository.getDocumentNumberInformation(documentNumberRequest);
        Optional.ofNullable(documentNumberResponse)
                .filter(DocumentNumberResponse::getSuccess)
                .orElseThrow(() -> {
                    String message = StringUtil.buildConstantMessageFromText(request.getDocumentNumber(), NOT_FOUND_MESSAGE);
                    return GenericClientException.create(DOCUMENT_NUMBER_NOT_FOUND, message, HttpStatus.UNPROCESSABLE_ENTITY);
                });
        userRepository.findByCellphoneAndStatus(request.getCellphone(), EntityStatusType.ACTIVE.getCode())
                .ifPresent(user -> {
                    String message = StringUtil.buildConstantMessageFromText(user.getCellphone(), ALREADY_EXISTS_MESSAGE);
                    throw GenericClientException.create(message, HttpStatus.UNPROCESSABLE_ENTITY);
                });

        UserEntity createUser = UserEntity.create(request.getFullName(),
                request.getCellphone(),
                request.getDocumentNumber(),
                request.getBirthDate(),
                RoleType.CUSTOMER.getCode());
        UserEntity userCreated = userRepository.save(createUser);

        JwtPayload jwtPayload = JwtPayload.create(userCreated.getCellphone(), userCreated.getRole());
        String token = jwtUtil.generateToken(jwtPayload);
        return CreateUserResponse.create(userCreated.getFullName(), EMPTY, userCreated.getRole(), token, token);
    }
}
