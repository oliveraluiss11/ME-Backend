package com.jayway.market_express.store.entity;

import com.jayway.market_express.common.enums.EntityStatusType;
import com.jayway.market_express.store.dto.CreateStoreRequest;
import com.jayway.market_express.store.dto.DailyHours;
import com.jayway.market_express.store.dto.StoreAddressDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

import static com.jayway.market_express.common.constant.CollectionConstant.STORE_COLLECTION;
import static com.jayway.market_express.common.util.DateUtil.getLocalDateTime;

@Document(collection = STORE_COLLECTION)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreEntity {
    @Id
    private String storeId;
    private String name;
    private String cellphone;
    private String email;
    private String ruc;
    private StoreAddressDto address;
    private Map<String, DailyHours> openingHours;
    private String category;
    private String status;
    private LocalDateTime registrationDate;
    private LocalDateTime updatedDate;
    public static StoreEntity from(CreateStoreRequest request){
        StoreAddressDto address = StoreAddressDto.from(request);
        return new StoreEntity(null, request.getName(), request.getCellphone(), request.getEmail(), request.getRuc(), address, request.getOpeningHours(), request.getCategory(),  EntityStatusType.ACTIVE.getCode(), getLocalDateTime(), null);
    }
}
