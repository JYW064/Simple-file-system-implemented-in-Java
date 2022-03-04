package com.jyw.mapper;

import com.jyw.entity.FilesData;

public interface FileMapper {

    int insert(FilesData record);

    FilesData selectByUUID(String uuid);
}