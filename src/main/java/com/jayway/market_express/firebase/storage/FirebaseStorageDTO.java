package com.jayway.market_express.firebase.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FirebaseStorageDTO {
    private String fileName;
    private String mediaLink;
    private String contentType;

    public static FirebaseStorageDTO create(String fileName, String mediaLink, String contentType){
        return new FirebaseStorageDTO(fileName, mediaLink, contentType);
    }
}
