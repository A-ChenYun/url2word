/*
package cutpicture;

import java.io.IOException;
import org.openimaj.*;

*/
/**
 * @Author AChen
 * @Data: 2020/4/13 3:26 下午
 *//*

public class ImplementsCmd {
    public static void  implcmd(String cmd){//在java中调用执行cmd命令
        Process p;

        System.out.println(cmd);
        try {
            p = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void  reload(String url,String picname,String jsname){
        //这里面的路径都是相对路径
        String content = "";
        //netToPicMoban.js这个phantomjs 的一个js模版，修改相应的参数就可以实现我们要的功能
        String str = FileToolsUtil.file2string("src/phantomjs/NetToPicMoban.js", "utf-8");
        String content1 = str.replace("url", "'"+url+"'");
        content = content1.replace("savename", "'"+picname+"'");
        FileToolsUtil.string2file("src/phantomjs/", jsname, content);
        //这个函数是我自己封装好的，也就是实现将String转成文件，第一个参数是路径，第二个参数是文件名，第三个参数是内容
    }
}
*/


