package com.jayway.market_express.common.client;

import java.util.Map;

public interface RestClient<T> {
    T get(String url, Class<T> responseType);

    T get(String url, Class<T> responseType, Map<String, String> uriVariables);

    T getWithQueryParams(String url, Class<T> responseType, Map<String, String> queryParams);

    T get(String url, Class<T> responseType, Map<String, String> uriVariables, Map<String, String> queryParams);

    T post(String baseUrl, Object requestBody, Class<T> responseType, Map<String, String> headersMap);
}

