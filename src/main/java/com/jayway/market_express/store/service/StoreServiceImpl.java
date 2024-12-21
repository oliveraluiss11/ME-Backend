package com.jayway.market_express.store.service;

import com.jayway.market_express.common.enums.EntityStatusType;
import com.jayway.market_express.common.enums.RoleType;
import com.jayway.market_express.common.exception.GenericClientException;
import com.jayway.market_express.common.util.StringUtil;
import com.jayway.market_express.reniec.dni.DocumentNumberResponse;
import com.jayway.market_express.reniec.dni.DocumentNumberService;
import com.jayway.market_express.store.dto.CreateStoreRequest;
import com.jayway.market_express.store.entity.StoreEntity;
import com.jayway.market_express.store.repository.StoreRepository;
import com.jayway.market_express.user.document.UserAddressDto;
import com.jayway.market_express.user.document.UserEntity;
import com.jayway.market_express.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.jayway.market_express.common.constant.MessageConstant.ALREADY_EXISTS_MESSAGE;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;
    private final DocumentNumberService documentNumberService;
    private final UserRepository userRepository;
    @Override
    public void registerStore(CreateStoreRequest request) {
        userRepository.findByCellphoneAndStatus(request.getCellphone(), EntityStatusType.ACTIVE.getCode())
                .ifPresent(user -> {
                    String message = StringUtil.buildConstantMessageFromText(user.getCellphone(), ALREADY_EXISTS_MESSAGE);
                    throw GenericClientException.create(message, HttpStatus.UNPROCESSABLE_ENTITY);
                });
        StoreEntity storeEntity = StoreEntity.from(request);
        StoreEntity storeCreated = storeRepository.save(storeEntity);

        DocumentNumberResponse documentNumberResponse = documentNumberService.getDocumentNumberInformation(request.getDocumentNumber());
        String fullName = documentNumberResponse.getData().getFullName().replace(",","");
        fullName = StringUtil.toTitleCase(fullName);

        UserAddressDto address = UserAddressDto.create(request.getLocation(), request.getDistrict(), request.getProvince());
        UserEntity createUser = UserEntity.create(fullName,
                request.getCellphone(),
                request.getDocumentNumber(),
                address,
                request.getBirthDate(),
                storeCreated.getStoreId(),
                RoleType.STORE_OWNER.getCode(),
                BigDecimal.ZERO);
        UserEntity userCreated = userRepository.save(createUser);
    }
}
