package com.jyw.entity;

import java.util.Date;

public class FilesData {
    //数据库中文件元数据id
    private Integer id;
    //文件名
    private String name;
    //文件类型
    private String type;
    //文件大小(字节）
    private Long size;
    //创建日期
    private Date time;
    //文件保存路径
    private String url;
    //文件重命名后的uuid
    private String uuid;

    public FilesData(){

    }

    public FilesData(String name, String type, Long size, Date time, String url, String uuid) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.time = time;
        this.url = url;
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", time=" + time +
                ", url='" + url + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}