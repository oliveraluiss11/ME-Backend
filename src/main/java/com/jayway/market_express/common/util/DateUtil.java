package com.jayway.market_express.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.jayway.market_express.common.constant.ZonedDateTimeConstant.LIMA_ZONED_DATETIME;

public class DateUtil {
    public static LocalDateTime getLocalDateTime() {
        return ZonedDateTime.now(ZoneId.of(LIMA_ZONED_DATETIME)).toLocalDateTime();
    }
}
