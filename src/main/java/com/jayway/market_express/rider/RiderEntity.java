package com.jayway.market_express.rider;

import com.jayway.market_express.common.enums.EntityStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.jayway.market_express.common.constant.CollectionConstant.RIDER_COLLECTION;
import static com.jayway.market_express.common.util.DateUtil.getLocalDateTime;

@Document(collection = RIDER_COLLECTION)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RiderEntity {
    @Id
    private String riderId;
    private RiderUser user;
    private RiderVehicle vehicle;
    private RiderOrder orderInformation;
    private String status;
    private LocalDateTime registrationDate;
    private LocalDateTime updateDate;

    public static RiderEntity create(RiderUser user, RiderVehicle vehicle) {
        RiderOrder riderOrder = RiderOrder.create();
        return new RiderEntity(null, user, vehicle, riderOrder, EntityStatusType.ACTIVE.getCode(), getLocalDateTime(), null);
    }
}
