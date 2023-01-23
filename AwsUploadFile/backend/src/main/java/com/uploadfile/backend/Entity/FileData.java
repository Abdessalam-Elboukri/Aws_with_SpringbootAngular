package com.uploadfile.backend.Entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String description;
    @Column(unique = true)
    private String file_url;
    private LocalDate createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile_url() {
        return file_url; 
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
