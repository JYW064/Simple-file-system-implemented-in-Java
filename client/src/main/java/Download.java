import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * 客户端下载类
 */
public class Download {
    public static void download(String uuid) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/file/download").openConnection();
        connection.setRequestMethod("GET");
        // 设置字符编码
        connection.setRequestProperty("Charset", "UTF-8");

        //设置参数
        connection.setRequestProperty("uuid",uuid);
        //连接
        connection.connect();
        if(connection.getResponseCode()!=200){
            System.out.println("文件下载失败");
            System.out.println("错误码："+connection.getResponseCode());
            return;
        }

        //定义http输入流
        InputStream is = connection.getInputStream();
        //从请求头得到文件类型
        String fileType= connection.getHeaderField("filetype");

        File dir =new File("C:\\Users\\will\\Desktop\\download");
        if(!dir.exists()){
            dir.mkdir();
            System.out.println("新建下载文件夹...");
        }

        //定义输出文件
        File file = new File("C:\\Users\\will\\Desktop\\download"+"\\"+uuid+fileType);
        //输出文件连接到输出流
        FileOutputStream fos = new FileOutputStream(file);

        //每次从http输入流读取1024个字节，写入到文件输出流
        int bufferSize=1024;
        byte[] buffer=new byte[bufferSize];
        int len=-1;
        while ((len=is.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }
        System.out.println("文件下载成功!");
    }
}
