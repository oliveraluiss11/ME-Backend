package com.jayway.market_express.firebase.storage;


import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.jayway.market_express.common.util.EnvironmentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
@RequiredArgsConstructor
public class FirebaseStorageRepositoryImpl implements FirebaseStorageRepository{
    private final EnvironmentUtil environmentUtil;
    @Override
    public FirebaseStorageDTO uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.create(fileName, file.getInputStream(), file.getContentType());
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        String fileUrl = String.format("https://storage.googleapis.com/%s/%s", bucket.getName(), fileName);
        return FirebaseStorageDTO.create(fileName, fileUrl, blob.getContentType());
    }
}
