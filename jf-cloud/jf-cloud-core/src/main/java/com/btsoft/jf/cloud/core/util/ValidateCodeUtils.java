package com.btsoft.jf.cloud.core.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author jeo_cb
 * @desc 验证码工具类
 * @date 2019/10/24 15:36
 **/
public class ValidateCodeUtils {
    /** 图片宽度*/
    private final static int IMAGE_WIDTH=160;
    /** 图片高度*/
    private final static int IMAGE_HEIGHT=40;
    /** 干扰线数量*/
    private final static int LINE_NUMBER=30;
    /** 验证码个数*/
    private final static int CODE_NUMBER=4;
    /** session键前缀*/
    private final static String SESSION_PREFIX="validateCode_";

    public static void generateValidateCode(HttpServletRequest req, HttpServletResponse resp){
        BufferedImage image=new BufferedImage(IMAGE_WIDTH,IMAGE_HEIGHT,BufferedImage.TYPE_INT_BGR);
        Graphics g=image.getGraphics();
        //设置画布大小
        g.fillRect(0,0,IMAGE_WIDTH,IMAGE_HEIGHT);
        //设置画布背景色
        g.setColor(getRandomColor());
        //设置画布字体
        g.setFont(getFont());
        //绘制干扰线
        drawLine(g);

        // 添加噪点
        float yawpRate = 0.05f;
        Random random = new Random();
        int area = (int) (yawpRate * IMAGE_WIDTH * IMAGE_HEIGHT);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(IMAGE_WIDTH);
            int y = random.nextInt(IMAGE_HEIGHT);
            image.setRGB(x, y, 12332);
        }
        //绘制验证码
        drawCode(g,"18Ac");
        g.dispose();
        try {
            ImageIO.write(image,"PNG",resp.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * 获取随机颜色
     * @author jeo_cb
     * @date 2019/10/24
     * @return 颜色
     */
    private static Color getRandomColor(){
        Random random=new Random();
        int r=random.nextInt(256);
        int g=random.nextInt(256);
        int b=random.nextInt(256);
        return new Color(r,g,b);
    }

    /**
     * 获取字体
     * @author jeo_cb
     * @date 2019/10/24
     * @return 字体
     */
    private static Font getFont(){
        return new Font("Times New Roman", Font.PLAIN, 40);
    }

    /**
     * 获取字体
     * @author jeo_cb
     * @date 2019/10/24
     * @return 字体
     */
    private static void drawLine(Graphics g){
        Random random=new Random();
        for (int i = 0; i <100 ; i++) {
            int x1 = random.nextInt(IMAGE_WIDTH);
            int y1 = random.nextInt(IMAGE_HEIGHT);
            int x2 = random.nextInt(20);
            int y2 = random.nextInt(10);
            g.setFont(getFont());
            g.setColor(getRandomColor());
            g.drawLine(x1,y1,x1+x2,y1+y2);
        }
    }

    /**
     * 获取字体
     * @author jeo_cb
     * @date 2019/10/24
     * @return 字体
     */
    private static void drawCode(Graphics g,String code){
        Random random=new Random();
        for (int i = 0; i <code.length() ; i++) {
            g.setFont(getFont());
            g.setColor(getRandomColor());
            g.translate(random.nextInt(6), random.nextInt(6));
            g.drawString(String.valueOf(code.charAt(i)),40*i+10,25);
        }
    }
}
