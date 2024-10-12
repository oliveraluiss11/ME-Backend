package com.jayway.market_express.rider;

import com.jayway.market_express.common.enums.EntityStatusType;
import com.jayway.market_express.common.exception.GenericClientException;
import com.jayway.market_express.common.util.JwtPayload;
import com.jayway.market_express.common.util.JwtUtil;
import com.jayway.market_express.common.util.StringUtil;
import com.jayway.market_express.otp.OtpServiceImpl;
import com.jayway.market_express.rider_status_history.RiderStatusHistoryEntity;
import com.jayway.market_express.user.RoleType;
import com.jayway.market_express.user.UserEntity;
import com.jayway.market_express.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.jayway.market_express.common.constant.MessageConstant.ALREADY_EXISTS_MESSAGE;
import static com.jayway.market_express.common.constant.MessageConstant.EMPTY;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {
    private final UserRepository userRepository;
    private final RiderRepository riderRepository;
    private final OtpServiceImpl otpService;
    private final JwtUtil jwtUtil;

    @Override
    public CreateRiderResponse registerRider(CreateRiderRequest request) {
        otpService.ensureValidOtp(request.getCellphone(), request.getOtp());

        userRepository.findByCellphoneAndStatus(request.getCellphone(), EntityStatusType.ACTIVE.getCode())
                .ifPresent(user -> {
                    String message = StringUtil.buildConstantMessageFromText(user.getCellphone(), ALREADY_EXISTS_MESSAGE);
                    throw GenericClientException.create(message, HttpStatus.UNPROCESSABLE_ENTITY);
                });

        UserEntity createUser = UserEntity.create(request.getFullName(),
                request.getCellphone(),
                request.getDocumentNumber(),
                request.getBirthDate(),
                RoleType.RIDER.getCode());

        UserEntity createdUser = userRepository.save(createUser);

        RiderUser riderUser = RiderUser.create(createdUser.getUserId(), createdUser.getDocumentNumber(), createdUser.getCellphone());
        RiderEntity createRider = RiderEntity.create(riderUser, request.getVehicle());
        RiderEntity createdRider = riderRepository.save(createRider);

        RiderStatusHistoryEntity riderStatusHistoryEntity = RiderStatusHistoryEntity.create(createdRider.getRiderId(), null, RiderStatusType.AVAILABLE.getCode());
        JwtPayload jwtPayload = JwtPayload.create(createdUser.getCellphone(), createdUser.getRole());
        String token = jwtUtil.generateToken(jwtPayload);
        return CreateRiderResponse.create(createdUser.getFullName(), EMPTY, createdUser.getRole(), token, token);
    }
}
