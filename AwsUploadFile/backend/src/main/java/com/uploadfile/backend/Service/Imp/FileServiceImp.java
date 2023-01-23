package com.uploadfile.backend.Service.Imp;


import com.uploadfile.backend.Entity.FileData;
import com.uploadfile.backend.Repository.FileRepository;
import com.uploadfile.backend.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImp implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public void save(FileData file) {
        fileRepository.save(file);
    }
}
