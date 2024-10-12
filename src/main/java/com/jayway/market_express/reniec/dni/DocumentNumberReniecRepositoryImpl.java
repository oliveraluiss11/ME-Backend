package com.jayway.market_express.reniec.dni;

import com.jayway.market_express.common.client.RestClient;
import com.jayway.market_express.common.util.EnvironmentUtil;
import com.jayway.market_express.common.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.market_express.common.constant.ReniecPathConstant.FIND_DNI;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Repository
@RequiredArgsConstructor
public class DocumentNumberReniecRepositoryImpl implements DocumentNumberReniecRepository {
    private final RestClient<DocumentNumberResponse> restClient;
    private final EnvironmentUtil environmentUtil;

    @Override
    public DocumentNumberResponse getDocumentNumberInformation(DocumentNumberRequest request) {
        String url = environmentUtil.getReniecUrl() + FIND_DNI;
        String token = StringUtil.buildBearerToken(environmentUtil.getReniecToken());
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTHORIZATION, token);
        headers.put(ACCEPT, APPLICATION_JSON_VALUE);
        headers.put(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        return restClient.post(url, request, DocumentNumberResponse.class, headers);
    }
}
