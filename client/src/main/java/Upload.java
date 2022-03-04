import java.io.*;
import java.net.*;
import java.time.LocalDate;

/**
 * 客户端上传类
 */
public class Upload {
    public static void upload(String path) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/file/upload").openConnection();
        connection.setRequestMethod("POST");
        //设置url可用于输出
        connection.setDoOutput(true);
        // 设置字符编码
        connection.setRequestProperty("Charset", "UTF-8");

        //找到路径中.的索引，得到.之后的字符串
        int index = path.indexOf(".");
        String fileType = path.substring(index);

        //检查要上传的文件是否存在
        File file=new File(path);
        if(!file.exists()){
            System.out.println("找不到指定文件");
            return;
        }
        //将文件信息放入请求头中
        connection.setRequestProperty("fileSize", String.valueOf(file.length()));
        connection.setRequestProperty("fileType",fileType);
        connection.setRequestProperty("fileName", URLEncoder.encode(file.getName(), "UTF-8"));
        LocalDate now = LocalDate.now();
        connection.setRequestProperty("time",now.toString());


        //定义http输出流
        OutputStream os = new DataOutputStream(connection.getOutputStream());
        //输入流连接到输入文件
        FileInputStream fis =new FileInputStream(file);
        //每次从输入文件读1024个字节，写入到http输出流
        int bufferSize=1024;
        byte[] buffer=new byte[bufferSize];
        int length=-1;
        while ((length=fis.read(buffer))!=-1){
            os.write(buffer,0,length);
        }
        fis.close();
        os.flush();

        //得到响应请求
        InputStream is=connection.getInputStream();
        int ch;
        StringBuffer str = new StringBuffer();
        while ((ch=is.read())!=-1){
            str.append((char)ch);
        }

        System.out.println("文件:"+file.getName()+" 上传成功");
        System.out.println("uuid: "+str);
        os.close();
    }
}
