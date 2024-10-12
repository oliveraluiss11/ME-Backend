package com.jayway.market_express.otp;

import com.jayway.market_express.common.enums.EntityStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.jayway.market_express.common.constant.CollectionConstant.OTP_COLLECTION;
import static com.jayway.market_express.common.util.DateUtil.getLocalDateTime;

@Document(collection = OTP_COLLECTION)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OtpEntity {
    @Id
    private String otpId;
    private String cellphone;
    private String code;
    private String status;
    private LocalDateTime registrationDate;
    private LocalDateTime updatedDate;

    public static OtpEntity create(String cellphone, String code) {
        return new OtpEntity(null, cellphone, code, EntityStatusType.ACTIVE.getCode(), getLocalDateTime(), null);
    }

    public OtpEntity updateStatus(String status) {
        this.status = status;
        this.updatedDate = getLocalDateTime();
        return this;
    }
}
