package com.jyw.service.Impl;

import com.jyw.entity.FilesData;
import com.jyw.mapper.FileMapper;
import com.jyw.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileMapper fileMapper;

    @Override
    public void uploadFile(FilesData filesData) {
        fileMapper.insert(filesData);
    }

    @Override
    public FilesData getFile(String uuid) {
       return fileMapper.selectByUUID(uuid);
    }

}
