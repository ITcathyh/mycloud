package hyh.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCodeGenerateUtil {
    /**
     * 生成图片验证码
     *
     * @param length         验证码字符长度，大于0的整数
     * @param width          图片宽度，大于0的整数
     * @param height         图片高度，大于0的整数
     * @param interLine      图片中干扰线的条数，非负整数
     * @param randomLocation 每个字符的高低位置是否随机
     * @param backColor      图片颜色，若为null，则采用随机颜色
     * @param foreColor      字体颜色，若为null，则采用随机颜色
     * @param lineColor      干扰线颜色，若为null，则采用随机颜色
     * @return 图片缓存对象
     */
    public static BufferedImage generateImageCode(String code, int length, int width, int height,
                                                  int interLine, boolean randomLocation, Color backColor,
                                                  Color foreColor, Color lineColor) {
        if (length <= 0 || width <= 0 ||
                height <= 0 || interLine < 0) {
            return null;
        }

        return generateImageCode(code, length, width, height, interLine,
                randomLocation, backColor, foreColor, lineColor);
    }

    public static BufferedImage generateImageCode(String code, int length, int width, int height,
                                                  int interLine) {
        return generateImageCode(code, length, width, height, interLine,
                true, null, null, null);
    }

    public static BufferedImage generateImageCode(String code, int length, int width, int height,
                                                  int interLine, boolean randomLocation) {
        return generateImageCode(code, length, width, height, interLine,
                randomLocation, null, null, null);
    }

    public static BufferedImage generateImageCode(String code, int length, int width, int height,
                                                  int interLine, boolean randomLocation, Color backColor) {
        return generateImageCode(code, length, width, height, interLine,
                randomLocation, backColor, null, null);
    }

    public static BufferedImage generateImageCode(String code, int length, int width, int height,
                                                  int interLine, boolean randomLocation, Color backColor,
                                                  Color foreColor) {
        return generateImageCode(code, length, width, height, interLine,
                randomLocation, backColor, foreColor, null);
    }

    private static BufferedImage generateImageCode(String code, String textCode, int width, int height, int interLine,
                                                   boolean randomLocation, Color backColor, Color foreColor, Color lineColor) {
        BufferedImage bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bim.getGraphics();

        // 画背景图
        g.setColor(backColor == null ? getRandomColor() : backColor);
        g.fillRect(0, 0, width, height);

        // 画干扰线
        Random r = new Random();
        if (interLine > 0) {
            int x = 0, y = 0, y1 = 0;

            for (int i = 0; i < interLine; i++) {
                g.setColor(lineColor == null ? getRandomColor() : lineColor);
                y = r.nextInt(height);
                y1 = r.nextInt(height);

                g.drawLine(x, y, width, y1);
            }
        }

        // 字体大小为图片高度的80%
        int fsize = (int) (height * 0.8);
        int fx = height - fsize;
        int fy = fsize;

        g.setFont(new Font("Default", Font.PLAIN, fsize));

        // 写验证码字符
        for (int i = 0; i < textCode.length(); i++) {
            fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height) : fy;// 每个字符高低是否随机
            g.setColor(foreColor == null ? getRandomColor() : foreColor);
            g.drawString(textCode.charAt(i) + "", fx, fy);
            fx += fsize * 0.9;
        }

        g.dispose();
        return bim;
    }

    /**
     * 产生随机颜色
     */
    private static Color getRandomColor() {
        Random r = new Random();
        return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }
}
