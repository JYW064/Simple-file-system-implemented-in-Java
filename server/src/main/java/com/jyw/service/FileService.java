package com.jyw.service;

import com.jyw.entity.FilesData;

public interface FileService {
    void uploadFile(FilesData filesData);
    FilesData getFile(String uuid);
}
