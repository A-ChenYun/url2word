import consts.Consts;
import cutpicture.CutPicture;
import lombok.extern.slf4j.Slf4j;
import pic2word.Check;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 主程序
 * @Author AChen
 * @Data: 2020/4/11 9:39 下午
 */
@Slf4j
public class main {
    public static void main(String[] args) throws IOException, URISyntaxException, AWTException, InterruptedException {
        //先截图
        CutPicture cutPicture = new CutPicture();
        cutPicture.cut();
        //Thread.sleep(30000);
        String msg = Check.checkFile(Consts.CUT_PIC_location_NAME);
        log.info("1111"+msg+"22222");
        //图片位置
        //String path = Consts.CUT_PIC_location;
        /*String msg = Check.checkFile(path);
        log.info("1111"+msg+"22222");*/
        /*try {
            String msg = Check.checkFile(path);
            log.info("1111"+msg+"22222");
            //pic2word.Check.checkFile(path);
            //long now = System.currentTimeMillis();
            // checkUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=08c05c0e8444ebf8797c6c6db890bc4f/fc1f4134970a304e46bfc5f7d2c8a786c9175c19.jpg");
            //System.out.println("耗时：" + (System.currentTimeMillis() - now) / 1000 + "s");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }*/
    }
}
