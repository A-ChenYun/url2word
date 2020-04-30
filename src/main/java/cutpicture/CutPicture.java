package cutpicture;


/**
 * @Author AChen
 * @Data: 2020/4/11 7:06 下午
 */

import consts.Consts;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


public class CutPicture {
    public void cut() throws MalformedURLException,
            IOException, URISyntaxException, AWTException {
        // 此方法仅适用于JdK1.6及以上版本
        Desktop.getDesktop().browse(
                new URL(Consts.CUT_URL).toURI());
        Robot robot = new Robot();
        robot.delay(10000);
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        // 最大化浏览器
        robot.keyRelease(KeyEvent.VK_F11);
        robot.delay(5000);
        Image image = robot.createScreenCapture(new Rectangle(0, 0, width,
                height));
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        // 保存图片
        ImageIO.write((RenderedImage)image, "png",
                new File(Consts.CUT_PIC_location_NAME));
        //ImageIO.write(bi, "jpg", new File("/Users/yunchenouyang/Desktop/getPic" + System.currentTimeMillis() + ".jpg"));
    }
}