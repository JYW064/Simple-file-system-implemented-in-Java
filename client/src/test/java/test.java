import org.junit.Test;

import java.io.IOException;
public class test {
    /**
    *@Description: 上传文件单元测试
    *@Param:
    *@return:
    *@Author: ji yi wei
    *@date: 2022/3/4
    */
    @Test
    public void upload() throws Exception {

        Upload.upload("C:\\Users\\will\\Desktop\\学习资料\\简历\\简历.pdf");
        //文件不存在的情况
        //Upload.upload("C:\\Users\\will\\Desktop\\学习资料\\简历\\null.pdf");
    }

    /**
    *@Description: 下载文件单元测试
    *@Param:
    *@return:
    *@Author: ji yi wei
    *@date: 2022/3/4
    */
    @Test
    public void download() throws Exception {
        //uuid不存在的情况
        // Download.download("uuid-not-found");
        Download.download("61731a56-56bb-4d16-a1e9-4071e581ceaa");
    }



    /**
    *@Description: 获得元数据单元测试
    *@Param:
    *@return:
    *@Author: ji yi wei
    *@date: 2022/3/4
    */
    @Test
    public void getData() throws Exception {

        //String str = GetData.getData("61731a56-56bb-4d16-a1e9-4071e581ceaa");
        //uuid不存在的情况
        String str = GetData.getData("uuid-not-found");
        if(!str.equals("")){
            System.out.println("data="+str);
        }
    }



}
