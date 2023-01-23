package com.uploadfile.backend.Controller;

import com.uploadfile.backend.Aws.AwsFolders;
import com.uploadfile.backend.Aws.S3Util;
import com.uploadfile.backend.Entity.FileData;
import com.uploadfile.backend.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

    @Autowired
    FileService fileService;


    @PostMapping(value={"/upload"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void addFile(@RequestPart("fileInfo") FileData file, @RequestPart("MyFile") MultipartFile multipart) throws IllegalAccessException {
            String fileName = multipart.getOriginalFilename();
            System.out.println("filename: " + fileName);
            System.out.println(file.getDescription());
            URL link = null;
            try {
                S3Util.uploadFile(fileName, multipart.getInputStream(), AwsFolders.IMAGES);
                link = S3Util.getObjectURL(fileName, AwsFolders.IMAGES);
                file.setFile_url(String.valueOf(link));
                file.setCreatedAt(LocalDate.now());
                fileService.save(file);
            } catch (Exception ex) {
                throw new IllegalAccessException("failed to upload image"+ex.getMessage());
            }
        }
    }