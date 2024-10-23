package com.jayway.market_express.firebase.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FirebaseStorageRepository {
    FirebaseStorageDTO uploadFile(MultipartFile file) throws IOException;
}
