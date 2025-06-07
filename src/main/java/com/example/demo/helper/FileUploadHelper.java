package com.example.demo.helper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
    public final String UPLOAD_DIR = "/Users/arghyachakraborty/Downloads/demo 2/src/main/resources/static/image";
    public boolean uploadFile(MultipartFile multipartFile){

        boolean f = false;
        try {
            InputStream is = multipartFile.getInputStream();
            byte data[] = new byte[is.available()];
            is.read(data);

            //write
            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+"/"+multipartFile.getOriginalFilename());
            fos.write(data);
            fos.close();
            f = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
