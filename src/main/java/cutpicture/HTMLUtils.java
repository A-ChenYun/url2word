package cutpicture;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeComponent;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;

public class HTMLUtils extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 行分隔符
	final static public String LS = System.getProperty("line.separator", "\n");

	// 文件分割符
	final static public String FS = System.getProperty("file.separator", "\\");

	// 以javascript脚本获得网页全屏后大小
	final static StringBuffer jsScreemSize;

	static {
		jsScreemSize = new StringBuffer();
		jsScreemSize.append("var width = 0;").append(LS);
		jsScreemSize.append("var height = 0;").append(LS);
		jsScreemSize.append("if(document.documentElement) {").append(LS);
		jsScreemSize.append("  width = Math.max(width, document.documentElement.scrollWidth);")
				.append(LS);
		jsScreemSize.append("  height = Math.max(height, document.documentElement.scrollHeight);")
				.append(LS);
		jsScreemSize.append("}").append(LS);
		jsScreemSize.append("if(self.innerWidth) {").append(LS);
		jsScreemSize.append("  width = Math.max(width, self.innerWidth);")
				.append(LS);
		jsScreemSize.append("  height = Math.max(height, self.innerHeight);")
				.append(LS);
		jsScreemSize.append("}").append(LS);
		jsScreemSize.append("if(document.body.scrollWidth) {").append(LS);
		jsScreemSize.append("  width = Math.max(width, document.body.scrollWidth);")
				.append(LS);
		jsScreemSize.append("  height = Math.max(height, document.body.scrollHeight);")
				.append(LS);
		jsScreemSize.append("}").append(LS);
		jsScreemSize.append("return width + ':' + height;");
	}

	public HTMLUtils(final String url, final int maxWidth, final int maxHeight) {
		super(new BorderLayout());
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		final String fileName = System.currentTimeMillis() + ".jpg";
		final JWebBrowser webBrowser = new JWebBrowser(null);
		webBrowser.setBarsVisible(false);
		webBrowser.navigate(url);
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		add(webBrowserPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));

		webBrowser.addWebBrowserListener(new WebBrowserAdapter() {

			// 监听加载进度
			public void loadingProgressChanged(WebBrowserEvent e) {

				// 当加载完毕时
				if (e.getWebBrowser().getLoadingProgress() == 100) {
					String result = (String) webBrowser
							.executeJavascriptWithResult(jsScreemSize.toString());

					int index = result == null ? -1 : result.indexOf(":");
					NativeComponent nativeComponent = webBrowser
							.getNativeComponent();
					Dimension originalSize = nativeComponent.getSize();
					Dimension imageSize = new Dimension(Integer.parseInt(result
							.substring(0, index)), Integer.parseInt(result
							.substring(index + 1)));
					imageSize.width = Math.max(originalSize.width,
							imageSize.width + 50);
					imageSize.height = Math.max(originalSize.height,
							imageSize.height + 50);
					nativeComponent.setSize(imageSize);
					BufferedImage image = new BufferedImage(imageSize.width,
							imageSize.height, BufferedImage.TYPE_INT_RGB);
					nativeComponent.paintComponent(image);
					nativeComponent.setSize(originalSize);
					// 当网页超出目标大小时 截取图片一部分，如果不执行，则截取整个网页页面
					// if (imageSize.width > maxWidth || imageSize.height > maxHeight) {
					// 截图部分图形
					// image = image.getSubimage(0, 0, maxWidth, maxHeight);
					/*
					 * 此部分为使用缩略图 int width = image.getWidth(), height = image
					 * .getHeight(); AffineTransform tx = new AffineTransform();
					 * tx.scale((double) maxWidth / width, (double) maxHeight /
					 * height); AffineTransformOp op = new AffineTransformOp(tx,
					 * AffineTransformOp.TYPE_NEAREST_NEIGHBOR); //缩小 image =
					 * op.filter(image, null);
					 */
					// }
					try {
						// 输出图像
						System.out.println(fileName);
						ImageIO.write(image, "jpg", new File(fileName));
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					// 退出操作
					System.exit(0);
				}
			}
		}

		);
		add(panel, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// SWT组件转Swing组件，不初始化父窗体将无法启动webBrowser
				JFrame frame = new JFrame("以DJ组件保存指定网页截图");
				// 加载google，最大保存为640x480的截图
				frame.getContentPane().add(new HTMLUtils("http://www.baidu.com/s?ie=utf-8&bs=swt-3.6M3-win32-win32-x86.jar&f=8&rsv_bp=1&rsv_spt=3&wd=swt-win64.jar&inputT=5935", 640, 480), BorderLayout.CENTER);
				frame.setSize(1024, 1024);
				// 仅初始化，但不显示
				frame.invalidate();
				frame.pack();
				frame.setVisible(false);
			}
		});
		NativeInterface.runEventPump();
	}

}
