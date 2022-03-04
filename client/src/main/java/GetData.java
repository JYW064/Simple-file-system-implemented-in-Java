import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * 客户端获得元数据类
 */
public class GetData {
    public static String getData(String uuid) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/file/getData?uuid="+uuid).openConnection();
        connection.setRequestMethod("GET");
        // 设置字符编码
        connection.setRequestProperty("Charset", "UTF-8");

        //定义输入流
        InputStream is = connection.getInputStream();
        //定义输出流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //每次从输入流读1024个字节到输出流
        byte [] buffer=new byte[1024];
        int len;
        while((len=is.read(buffer))!=-1){
            os.write(buffer, 0, len);
        }


        String jsonString = os.toString();
        if(!jsonString.equals("")){
            System.out.println("获得元数据成功！");
        }else{
            System.out.println("获取元数据失败！");
        }

        os.close();
        is.close();

        return jsonString;
    }
}
