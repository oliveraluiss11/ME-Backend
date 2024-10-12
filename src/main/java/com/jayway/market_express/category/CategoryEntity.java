package com.jayway.market_express.category;

import com.jayway.market_express.common.enums.EntityStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.jayway.market_express.common.constant.CollectionConstant.CATEGORY_COLLECTION;
import static com.jayway.market_express.common.util.DateUtil.getLocalDateTime;

@Document(collection = CATEGORY_COLLECTION)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryEntity {
    @Id
    private String categoryId;
    private String code;
    private String name;
    private String status;
    private LocalDateTime registrationDate;
    private LocalDateTime updatedDate;

    public static CategoryEntity create(String code, String name) {
        return new CategoryEntity(null, code, name, EntityStatusType.ACTIVE.getCode(), getLocalDateTime(), null);
    }
}
