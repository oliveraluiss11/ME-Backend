package com.jayway.market_express.firebase.controller;

import com.jayway.market_express.firebase.storage.FirebaseStorageDTO;
import com.jayway.market_express.firebase.storage.FirebaseStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/firebase")
@RequiredArgsConstructor
public class FirebaseController {
    private final FirebaseStorageRepository firebaseStorageRepository;
    @PostMapping("/storage/upload")
    public ResponseEntity<FirebaseStorageDTO> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        FirebaseStorageDTO firebaseStorage = firebaseStorageRepository.uploadFile(file);
        return new ResponseEntity<>(firebaseStorage, HttpStatus.OK);
    }
}
