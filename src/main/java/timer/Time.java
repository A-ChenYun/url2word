package timer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成当时时间，用作构造生成图片名称，使得名称不唯一
 * @Author AChen
 * @Data: 2020/4/11 11:36 下午
 */
public class Time {
    public static String randomFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = simpleDateFormat.format(date);
        return fileName;
    }

   /* public String setName(){
        Date data= new Date();
        String data_str = data.toString();
        return data_str;
    }*/
}
