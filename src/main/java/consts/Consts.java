package consts;

import pic2word.AuthService;
import timer.Time;

import java.util.Date;

/**
 * 保存常量
 * @Author AChen
 * @Data: 2020/4/11 9:18 下午
 */
public class Consts {
    public static String CUT_URL = "https://ico.org.uk/";//websit
    public static String API_Key = "YZC2KYViS3w5m7XZvfZOnImz";
    public static String Secret_Key = "PTBkPTTHSG515bYzSICwNSHkDf4SKy7y";
    public static String POST_URL = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic?access_token=" + AuthService.getAuth();
    //cut图片位置
    //构造图片名称
    static Time time = new Time();
    public static String PIC_NAME = time.randomFileName();
    public static String CUT_PIC_location = "src/main/resources/img";
    //这里设定png格式出了问题，可以加以改动，也可以不改，有时间还是改改，但是今天我累了，改好了
    public static String CUT_PIC_location_NAME = CUT_PIC_location+PIC_NAME+".png";




}
