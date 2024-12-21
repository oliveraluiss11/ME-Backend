package com.jayway.market_express.reniec.dni;

import com.jayway.market_express.common.exception.GenericClientException;
import com.jayway.market_express.common.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.jayway.market_express.common.constant.ErrorCodeConstant.DOCUMENT_NUMBER_NOT_FOUND;
import static com.jayway.market_express.common.constant.MessageConstant.NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentNumberService{
    private final DocumentNumberReniecRepository documentNumberReniecRepository;
    @Override
    public DocumentNumberResponse getDocumentNumberInformation(String documentNumber) {
        DocumentNumberRequest documentNumberRequest = DocumentNumberRequest.create(documentNumber);
        DocumentNumberResponse documentNumberResponse = documentNumberReniecRepository.getDocumentNumberInformation(documentNumberRequest);
        return Optional.ofNullable(documentNumberResponse)
                .filter(DocumentNumberResponse::getSuccess)
                .orElseThrow(() -> {
                    String message = StringUtil.buildConstantMessageFromText(documentNumber, NOT_FOUND_MESSAGE);
                    return GenericClientException.create(DOCUMENT_NUMBER_NOT_FOUND, message, HttpStatus.UNPROCESSABLE_ENTITY);
                });
    }
}
