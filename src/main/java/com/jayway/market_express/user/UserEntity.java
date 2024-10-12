package com.jayway.market_express.user;

import com.jayway.market_express.common.enums.EntityStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.jayway.market_express.common.constant.CollectionConstant.USER_COLLECTION;
import static com.jayway.market_express.common.util.DateUtil.getLocalDateTime;

@Document(collection = USER_COLLECTION)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserEntity {
    @Id
    private String userId;
    private String fullName;
    private String cellphone;
    private String documentNumber;
    private LocalDate birthDate;
    private String role;
    private String status;
    private LocalDateTime registrationDate;
    private LocalDateTime updatedDate;

    public static UserEntity create(String fullName, String cellphone, String documentNumber, LocalDate birthDate, String role) {
        return new UserEntity(null, fullName, cellphone, documentNumber, birthDate, role, EntityStatusType.ACTIVE.getCode(), getLocalDateTime(), null);
    }
}
