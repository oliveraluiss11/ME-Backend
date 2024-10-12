package com.jayway.market_express.reniec.dni;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentNumberRequest {
    private String dni;

    public static DocumentNumberRequest create(String dni) {
        return new DocumentNumberRequest(dni);
    }
}
