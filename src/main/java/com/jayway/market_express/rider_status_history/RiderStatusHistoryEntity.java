package com.jayway.market_express.rider_status_history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.jayway.market_express.common.constant.CollectionConstant.RIDER_STATUS_HISTORY_COLLECTION;
import static com.jayway.market_express.common.util.DateUtil.getLocalDateTime;

@Document(collection = RIDER_STATUS_HISTORY_COLLECTION)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RiderStatusHistoryEntity {
    @Id
    private String historyId;
    private String riderId;
    private String orderId;
    private String status;
    private LocalDateTime registrationDate;

    public static RiderStatusHistoryEntity create(String riderId, String orderId, String status) {
        return new RiderStatusHistoryEntity(null, riderId, orderId, status, getLocalDateTime());
    }
}
