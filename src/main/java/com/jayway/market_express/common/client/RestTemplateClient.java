package com.jayway.market_express.common.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RestTemplateClient<T> implements RestClient<T> {

    private final RestTemplate restTemplate;

    @Override
    public T get(String url, Class<T> responseType) {
        ResponseEntity<T> response = restTemplate.getForEntity(url, responseType);
        return response.getBody();
    }

    @Override
    public T get(String url, Class<T> responseType, Map<String, String> uriVariables) {
        ResponseEntity<T> response = restTemplate.getForEntity(url, responseType, uriVariables);
        return response.getBody();
    }

    @Override
    public T getWithQueryParams(String url, Class<T> responseType, Map<String, String> queryParams) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            uriBuilder.queryParam(entry.getKey(), entry.getValue());
        }
        String finalUrl = uriBuilder.toUriString();
        ResponseEntity<T> response = restTemplate.getForEntity(finalUrl, responseType);
        return response.getBody();
    }

    @Override
    public T get(String url, Class<T> responseType, Map<String, String> uriVariables, Map<String, String> queryParams) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            uriBuilder.queryParam(entry.getKey(), entry.getValue());
        }
        String finalUrl = uriBuilder.buildAndExpand(uriVariables).toUriString();
        ResponseEntity<T> response = restTemplate.getForEntity(finalUrl, responseType);
        return response.getBody();
    }

    public T post(String baseUrl, Object requestBody, Class<T> responseType, Map<String, String> headersMap) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        if (headersMap != null) {
            headersMap.forEach(headers::set);
        }
        RequestEntity<Object> requestEntity = RequestEntity
                .post(uri)
                .headers(headers)
                .body(requestBody);
        ResponseEntity<T> response = restTemplate.exchange(requestEntity, responseType);
        return response.getBody();
    }
}

