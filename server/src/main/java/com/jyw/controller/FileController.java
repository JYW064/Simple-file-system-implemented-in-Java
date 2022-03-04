package com.jyw.controller;

import com.alibaba.fastjson.JSON;
import com.jyw.entity.FilesData;
import com.jyw.mapper.FileMapper;
import com.jyw.service.FileService;
import com.jyw.service.Impl.FileServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    FileServiceImpl fileService;



    /**
    *@Description: 文件上传
    *@Param: [request, response]
    *@return: java.lang.String
    *@Author: ji yi wei
    *@date: 2022/3/4
    */
    @PostMapping("/upload")
    public String upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义输出文件路径
        String path="C:\\Users\\will\\Desktop\\upload";
        //定义uuid
        String uuid = UUID.randomUUID().toString();
        //得到输入流
        InputStream is = request.getInputStream();
        //是否已经存在该日期对应的文件夹，不存在则创建
        File dir =new File(path+"\\"+request.getHeader("time"));
        if(!dir.exists()){
            dir.mkdir();
            log.info("新建上传文件夹...");
        }

        //定义输出文件，并用uuid重命名
        String filePath = path+"\\"+request.getHeader("time")+"\\"+uuid+(request.getHeader("filetype"));
        File file = new File(filePath);
        //输出流连接到输出文件
        FileOutputStream fos=new FileOutputStream(file);
        //每次从http输入流读取1024个字节，写入到输出文件
        byte[] buffer = new byte[1024];
        int len;
        while ((len=is.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }

        //从请求头得到文件data
        Long size=Long.valueOf(request.getHeader("fileSize"));
        String type=request.getHeader("fileType");
        String name= URLDecoder.decode(request.getHeader("fileName"),"UTF-8");
        Date time=new Date(System.currentTimeMillis());
        String url=path+"\\"+request.getHeader("time")+"\\"+uuid+(request.getHeader("fileType"));

        //打印日志
        log.info("已接受文件：{}",name);
        log.info("文件重命名：{}",uuid);


        //封装为元数据
        FilesData filesData =new FilesData(name,type,size,time,url,uuid);

        fileService.uploadFile(filesData);
        is.close();
        fos.close();
        return uuid;
    }



    /**
    *@Description: 文件下载
    *@Param: [request, response]
    *@return: void
    *@Author: ji yi wei
    *@date: 2022/3/4
    */
    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = request.getHeader("uuid");
        //根据uuid从数据库查找该文件
        FilesData filesData = fileService.getFile(uuid);

        if(filesData==null){
            log.info("文件：{}",uuid+" 不存在!");
            response.setStatus(401);
            return;
        }

        //在响应头中加入文件类型，用于下载
        response.setHeader("fileType",filesData.getType());

        //定义输入文件
        File file = new File(filesData.getUrl());
        //输入文件连接到输入流
        FileInputStream fis = new FileInputStream(file);
        //定义http输出流
        OutputStream os = new DataOutputStream(response.getOutputStream());
        //每次从文件输入流读取1024个字节，写入到http输出流
        byte[] buffer = new byte[1024];
        int len;
        while ((len=fis.read(buffer))!=-1){
            os.write(buffer,0,len);
        }


        log.info("已下载文件：{}",filesData.getName());
        fis.close();
        os.close();
    }


    /**
    *@Description: 获得元数据
    *@Param: [uuid]
    *@return: java.lang.String
    *@Author: ji yi wei
    *@date: 2022/3/4
    */
    @GetMapping("/getData")
    public String getData(String uuid){
        FilesData filesData = fileService.getFile(uuid);
        if(filesData==null){
            log.info("文件元数据不存在");
            return null;
        }
        log.info("得到元数据：{}",filesData);
        //把java对象转为json字符串
        return JSON.toJSONString(filesData);
    }
}
