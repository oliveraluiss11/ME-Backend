package com.jayway.market_express.reniec.dni;

public interface DocumentNumberReniecRepository {
    DocumentNumberResponse getDocumentNumberInformation(DocumentNumberRequest request);
}
