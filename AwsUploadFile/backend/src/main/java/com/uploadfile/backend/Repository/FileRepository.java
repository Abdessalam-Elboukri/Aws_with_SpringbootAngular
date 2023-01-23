package com.uploadfile.backend.Repository;

import com.uploadfile.backend.Entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileData,Long> {
}
