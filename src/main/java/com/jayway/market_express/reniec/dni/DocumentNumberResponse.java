package com.jayway.market_express.reniec.dni;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DocumentNumberResponse {
    private Boolean success;
    private DocumentNumberData data;
}
