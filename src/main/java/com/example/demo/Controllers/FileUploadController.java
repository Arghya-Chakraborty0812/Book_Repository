package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.helper.FileUploadHelper;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper; 

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

        try{
        //validation
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
        }

        //file upload code..
        boolean f = fileUploadHelper.uploadFile(file);
        if(f){
            return ResponseEntity.ok("File Uplaoded Sucessfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong ! Try again");
    }catch(Exception e){
        e.printStackTrace();
    }


        return ResponseEntity.ok("working");

    }
}
